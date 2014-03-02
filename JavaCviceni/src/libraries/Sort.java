/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libraries;

import java.util.Arrays;
import java.util.Random;
import java.lang.Comparable;

/**
 *
 * @author sveco
 */
public class Sort {

  static void heapSort(int[] a) {
    // heapify(a, a.length);
    for (int i = a.length / 2 - 1; i >= 0; i--) {
        repairTop(a, i, a.length);
    }

    // int end = a.length - 1;
    // while (end > 0) {
    //   swap(a, end, 0);
    //   end--;
    //   repairTop(a, 0, end);
    // }
    for (int i = a.length - 1; i > 0; i--) {
        swap(a, 0, i);
        repairTop(a, 0, i);
    }
  }

  private static void repairTop(int[] array, int topIndex, int bottom) {
    int tmp = array[topIndex];
    int succ = topIndex * 2 + 1;
    if (succ < bottom - 1 && array[succ] < array[succ + 1]) { succ++; }

    while (succ < bottom && tmp < array[succ]) {
      array[topIndex] = array[succ];
      topIndex = succ;
      succ = succ * 2 + 1;
      if (succ < bottom - 1 && array[succ] < array[succ + 1]) { succ++; }
    }
    array[topIndex] = tmp;
  }

  static void quickSort(int[] a){
    quicksort(a, 0, a.length - 1);
  }

  private static void quicksort(int[] a, int left, int right){
    if (left >= right) { return; }

    int i = left;
    int d = right;
    int p = a[left];

    while (i <= d) {
      while(a[i] < p) { i++; }
      while(a[d] > p) { d--; }

      if (i < d) { swap(a, i, d); }
      if (i <= d) { i++; d--; }
    }

    quicksort(a, left, d);
    quicksort(a, i, right);
  }

  static void swap(int[] a, int l, int r){
    int temp = a[l];
    a[l] = a[r];
    a[r] = temp;
  }

  public static void main(String[] args){
    testHeapSort();
    testQuickSort();
  }

  static int testCount = 100000000;
  static int testMax = 2000000000;

  static void testQuickSort(){
    int[] testAsc = {1,2,3,4,5,6,7,8,9,10};
    int[] testDesc = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    int[] testRand = new int[10];
    Random r = new Random();
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

    int[] testLong = new int[testCount];
    for (int i = 0; i < testLong.length; i++) {
        testLong[i] = r.nextInt(testMax);
    }
    System.out.println("Sorting long random");
    // System.out.println(Arrays.toString(testLong));
    long before = System.nanoTime();
    quickSort(testLong);
    long after = System.nanoTime();
    // System.out.println(Arrays.toString(testLong));
    System.out.println("Dobehlo za: " + (after - before) / 1000000000);
  }

  public static void testHeapSort(){
    int[] testAsc = {1,2,3,4,5,6,7,8,9,10};
    int[] testDesc = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    int[] testRand = new int[10];
    Random r = new Random();
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

    int[] testLong = new int[testCount];
    for (int i = 0; i < testLong.length; i++) {
        testLong[i] = r.nextInt(testMax);
    }
    System.out.println("Sorting long random");
    // System.out.println(Arrays.toString(testLong));
    long before = System.nanoTime();
    quickSort(testLong);
    long after = System.nanoTime();
    // System.out.println(Arrays.toString(testLong));
    System.out.println("Dobehlo za: " + (after - before) / 1000000000);
  }

}