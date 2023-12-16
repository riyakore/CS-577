import java.util.Scanner;

public class MergeSort {

    public static long mergeSort(int[] array) {
        int size = array.length;
        int[] temp = new int[size];
        long count = 0;

        for (int i = 1; i < size; i *= 2) {
            for (int left = 0; left < size - 1; left += 2 * i) {
                int mid = Math.min(left + i - 1, size - 1);
                int right = Math.min(left + 2 * i - 1, size - 1);

                count += merge(array, temp, left, mid, right);
            }
        }

        return count;
    }

    public static long merge(int[] array, int[] temp, int left, int mid, int right) {

        long count = 0;

        int l = left;
        int m = mid + 1;
        int r = left;

        while (l <= mid && m <= right) {
            if (array[l] <= array[m]) {
                temp[r++] = array[l++];
            } else {
                temp[r++] = array[m++];
                count += mid - l + 1;
            }
        }

        while (l <= mid) {
            temp[r++] = array[l++];
        }

        while (m <= right) {
            temp[r++] = array[m++];
        }

        for (r = left; r <= right; r++) {
            array[r] = temp[r];
        }

        return count;

    }

    public static long inversions(int[] array) {
        return mergeSort(array);
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int instances = scnr.nextInt();

        for (int i = 0; i < instances; i++) {
            int lengthOfElements = scnr.nextInt();
            int[] array = new int[lengthOfElements];

            for (int j = 0; j < lengthOfElements; j++) {
                array[j] = scnr.nextInt();
            }

            long count = inversions(array);
            System.out.println(count);
        }

        scnr.close();
    }

}
