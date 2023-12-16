import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class LineIntersections {

    //merging the two sets in a sorted order
    public static long mergeSort(int[] array, int[] temp, int left, int right) {

        long count = 0;

        if (left < right) {
            int middle = (left + right) / 2;
            count += mergeSort(array, temp, left, middle);
            count += mergeSort(array, temp, middle + 1, right);
            count += mergeCount(array, temp, left, middle, right);
        }

        return count;
    }

    //using merge sort to sort the values
    public static long mergeCount(int[] array, int[] temp, int left, int middle, int right) {

        int l = left;
        int m = middle + 1;
        int r = left;

        long inversions = 0;

        while (l <= middle && m <= right) {
            if (array[l] <= array[m]) {
                temp[r++] = array[l++];
            }
            else {
                temp[r++] = array[m++];
                inversions += (middle - l + 1);
            }
        }

        while (l <= middle) {
            temp[r++] = array[l++];
        }

        while (m <= right) {
            temp[r++] = array[m++];
        }

        System.arraycopy(temp, left, array, left,right - left + 1);

        return inversions;
    }

    //counting the number of intersections in the set of lines
    public static long numIntersections(int[] topPoints) {
        int[] temp = new int[topPoints.length];
        return mergeSort(topPoints, temp,0,topPoints.length - 1);
    }
    
    //sorting the individual sets of the whole set of lines
    public static void sortBasedOnBottom(int[] topPoints, int[] bottomPoints) {
        Integer[] lines = new Integer[topPoints.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = i;
        }

        Arrays.sort(lines, Comparator.comparing(index -> bottomPoints[index]));

        int[] topTemp = new int[topPoints.length];
        for (int i = 0; i < topPoints.length; i++) {
            topTemp[i] = topPoints[lines[i]];
        }

        System.arraycopy(topTemp,0,topPoints, 0, topPoints.length);
    }
    
    //printing the output for the given input
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int instances = scnr.nextInt();

        for (int i = 0; i < instances; i++) {

            int pairOfPoints = scnr.nextInt();
            int[] topPoints = new int[pairOfPoints];
            int[] bottomPoints = new int[pairOfPoints];

            for (int j = 0; j < pairOfPoints; j++) {
                topPoints[j] = scnr.nextInt();
            }

            for (int k = 0; k < pairOfPoints; k++) {
                bottomPoints[k] = scnr.nextInt();
            }

            sortBasedOnBottom(topPoints,bottomPoints);

            long intersections = numIntersections(topPoints);
            System.out.println(intersections);
        }
        scnr.close();
    }
}



