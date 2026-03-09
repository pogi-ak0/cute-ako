import java.util.Arrays;

public class RadixSort {

    private static void countingSort(int[] arr, int exp, boolean ascending) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        if (ascending) {

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
        } else {

            for (int i = 8; i >= 0; i--) {
                count[i] += count[i + 1];
            }
        }


        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void radixSort(int[] arr, boolean ascending) {

        int max = Arrays.stream(arr).max().getAsInt();
        int pass = 1;

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp, ascending);
            System.out.println("PASS " + pass + ": " + Arrays.toString(arr));
            pass++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {141, 392, 749, 100, 10, 0, 1404};

        boolean ascending = false;
        

        System.out.println("Before sorting: " + Arrays.toString(arr));
        radixSort(arr, ascending);
        System.out.println("Final Result: " + Arrays.toString(arr));
    }
}
