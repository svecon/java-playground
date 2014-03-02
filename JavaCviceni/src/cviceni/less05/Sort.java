/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cviceni.less05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author sveco
 */
public class Sort {

    private static class MThread extends Thread {

        private int[] arr;
        private int low, high;

        public MThread(int[] arr, int low, int high) {
            this.arr = arr;
            this.low = low;
            this.high = high;
        }

        @Override
        public void run() {
            mergeSort(arr, new int[arr.length], low, high);
        }
    }

    public static void paraMergeSort(int[] array, int nThreads) {
        int x = array.length / nThreads;
        if (x <= 0) {
            return;
        }

        Thread[] threads = new Thread[nThreads];

        for (int i = 0; i < nThreads; i++) {
            int high = ((i + 1) * x) - 1;
            if ((high >= array.length) || (i == nThreads - 1 && high < array.length - 1)) {
                high = array.length - 1;
            }

            threads[i] = new MThread(array, i * x, high);
            threads[i].start();
        }

        for (Thread t : threads) {
            while (t.isAlive()) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                }
            }
        }

        paraMerge(array, nThreads);
    }

    static class Coord {

        public int x;
        public int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void paraMerge(int[] array, int nThreads) {
        int[] temp = new int[array.length];
        int x = array.length / nThreads;

        ArrayList<Coord> coo = new ArrayList<Coord>();

        for (int i = 0; i < nThreads; i++) {
            int high = ((i + 1) * x) - 1;
            if ((high >= array.length) || (i == nThreads - 1 && high < array.length - 1)) {
                high = array.length - 1;
            }

            coo.add(new Coord(i * x, high));
        }

        while (coo.size() > 1) {
            Coord f = coo.remove(0);
            Coord s = coo.remove(0);
            merge(array, temp, f.x, f.y+1, s.y);
            coo.add(new Coord(f.x, s.y));
        }
    }

    static void mergeSort(int[] a) {
        if (a.length == 1) {
            return;
        }

        int[] temp = new int[a.length];
        mergeSort(a, temp, 0, a.length - 1);
    }

    public static void mergeSort(int[] array, int[] temp, int left, int right) {
        if (left == right) {
            return;
        }
        int middleIndex = (left + right) / 2;

        mergeSort(array, temp, left, middleIndex);
        mergeSort(array, temp, middleIndex + 1, right);
        merge(array, temp, left, right, middleIndex);
    }

    private static void merge(int[] arr, int[] aux, int left, int right, int middleIndex) {
//        int middleIndex = (left + right) / 2;
        int leftIndex = left;
        int rightIndex = middleIndex + 1;
        int auxIndex = left;

//        int[] aux = new int[arr.length];

        while (leftIndex <= middleIndex && rightIndex <= right) {
            aux[auxIndex++] = arr[leftIndex] <= arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }

        while (leftIndex <= middleIndex) {
            aux[auxIndex++] = arr[leftIndex++];
        }

        while (rightIndex <= right) {
            aux[auxIndex++] = arr[rightIndex++];
        }

        for (int i = left; i <= right; i++) {
            arr[i] = aux[i];
        }

    }

    static void heapSort(int[] a) {
        heapify(a, a.length);
        int end = a.length - 1;
        while (end > 0) {
            swap(a, end, 0);
            end--;
            siftDown(a, 0, end);
        }
    }

    private static void heapify(int[] a, int length) {
        int start = (length / 2) - 1;
        while (start >= 0) {
            siftDown(a, start, length - 1);
            start--;
        }
    }

    private static void siftDown(int[] a, int start, int end) {
        int x = start;
        while (((x * 2) + 1) <= end) {
            int i = (x * 2) + 1;
            int j = x;
            if (a[j] < a[i]) {
                j = i;
            }
            if (((i + 1) <= end) && (a[j] < a[i + 1])) {
                j = i + 1;
            }
            if (j != x) {
                swap(a, x, j);
            } else {
                return;
            }
        }
    }

    static void quickSort(int[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static void quicksort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }

        int i = l;
        int j = r;
        int p = a[l];

        while (i <= j) {
            while (a[i] < p) {
                i++;
            }
            while (a[j] > p) {
                j--;
            }

            if (i < j) {
                swap(a, i, j);
            }
            if (i <= j) {
                i++;
                j--;
            }
        }

        quicksort(a, l, j);
        quicksort(a, i, r);
    }

    static void swap(int[] a, int l, int r) {
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }

    public static void main(String[] args) {
        int[] testLong = new int[testCount];
        Random r = new Random();
        for (int i = 0; i < testLong.length; i++) {
            testLong[i] = r.nextInt(testMax);
        }

//        testMergeSort(r, testLong.clone());
        testParaMergeSort(r, testLong.clone());
//        testHeapSort(r, testLong.clone());
//        testQuickSort(r, testLong.clone());
    }
    static int testCount = 1000;
    static int testMax = 2000000000;

    static void testQuickSort(Random r, int[] testLong) {
        int[] testAsc = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] testDesc = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] testRand = new int[10];

        for (int i = 0; i < testRand.length; i++) {
            testRand[i] = r.nextInt(100);
        }
        System.out.println("Testing QuickSort");
        System.out.println("= = = = = = = = =");

        System.out.println("Sorting descending");
        System.out.println(Arrays.toString(testDesc));
        quickSort(testDesc);
        System.out.println(Arrays.toString(testDesc));

        System.out.println("Sorting ascending");
        System.out.println(Arrays.toString(testAsc));
        quickSort(testAsc);
        System.out.println(Arrays.toString(testAsc));

        System.out.println("Sorting random");
        System.out.println(Arrays.toString(testRand));
        quickSort(testRand);
        System.out.println(Arrays.toString(testRand));


        System.out.println("Sorting long random");
        System.out.println(Arrays.toString(testLong));
        long before = System.nanoTime();
        quickSort(testLong);
        long after = System.nanoTime();
        System.out.println(Arrays.toString(testLong));
        System.out.println("Dobehlo za: " + (after - before) / 1000000000);
    }

    static void testHeapSort(Random r, int[] testLong) {
        int[] testAsc = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] testDesc = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] testRand = new int[10];

        for (int i = 0; i < testRand.length; i++) {
            testRand[i] = r.nextInt(100);
        }
        System.out.println("Testing HeapSort");
        System.out.println("= = = = = = = = =");

        System.out.println("Sorting descending");
        System.out.println(Arrays.toString(testDesc));
        heapSort(testDesc);
        System.out.println(Arrays.toString(testDesc));

        System.out.println("Sorting ascending");
        System.out.println(Arrays.toString(testAsc));
        heapSort(testAsc);
        System.out.println(Arrays.toString(testAsc));

        System.out.println("Sorting random");
        System.out.println(Arrays.toString(testRand));
        heapSort(testRand);
        System.out.println(Arrays.toString(testRand));

        System.out.println("Sorting long random");
        System.out.println(Arrays.toString(testLong));
        long before = System.nanoTime();
        heapSort(testLong);
        long after = System.nanoTime();
        System.out.println(Arrays.toString(testLong));
        System.out.println("Dobehlo za: " + (after - before) / 1000000000);
    }

    static void testMergeSort(Random r, int[] testLong) {
        int[] testAsc = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] testDesc = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] testRand = new int[10];

        for (int i = 0; i < testRand.length; i++) {
            testRand[i] = r.nextInt(100);
        }
        System.out.println("Testing MergeSort");
        System.out.println("= = = = = = = = =");

        System.out.println("Sorting descending");
        System.out.println(Arrays.toString(testDesc));
        mergeSort(testDesc);
        System.out.println(Arrays.toString(testDesc));

        System.out.println("Sorting ascending");
        System.out.println(Arrays.toString(testAsc));
        mergeSort(testAsc);
        System.out.println(Arrays.toString(testAsc));

        System.out.println("Sorting random");
        System.out.println(Arrays.toString(testRand));
        mergeSort(testRand);
        System.out.println(Arrays.toString(testRand));

        System.out.println("Sorting long random");
        System.out.println(Arrays.toString(testLong));
        long before = System.nanoTime();
        mergeSort(testLong);
        long after = System.nanoTime();
        System.out.println(Arrays.toString(testLong));
        System.out.println("Dobehlo za: " + (after - before) / 1000000000);
    }

    static void testParaMergeSort(Random r, int[] testLong) {
        int[] testAsc = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] testDesc = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] testRand = new int[10];

        for (int i = 0; i < testRand.length; i++) {
            testRand[i] = r.nextInt(100);
        }
        System.out.println("Testing ParaMergeSort");
        System.out.println("= = = = = = = = =");

        System.out.println("Sorting descending");
        System.out.println(Arrays.toString(testDesc));
        paraMergeSort(testDesc, Runtime.getRuntime().availableProcessors());
        System.out.println(Arrays.toString(testDesc));
        

//        System.out.println("Sorting ascending");
//        System.out.println(Arrays.toString(testAsc));
//        paraMergeSort(testAsc, Runtime.getRuntime().availableProcessors());
//        System.out.println(Arrays.toString(testAsc));
//
//        System.out.println("Sorting random");
//        System.out.println(Arrays.toString(testRand));
//        paraMergeSort(testRand, Runtime.getRuntime().availableProcessors());
//        System.out.println(Arrays.toString(testRand));
//
//        System.out.println("Sorting long random");
//        System.out.println(Arrays.toString(testLong));
//        long before = System.nanoTime();
//        paraMergeSort(testLong, Runtime.getRuntime().availableProcessors());
//        long after = System.nanoTime();
//        System.out.println(Arrays.toString(testLong));
//        System.out.println("Dobehlo za: " + (after - before) / 1000000000);
    }
}
