package org.bschlangaul.aufgaben.tech_info.merge_sort;

import java.util.Random;
import java.util.Arrays;
import org.junit.*;
import org.junit.Assert;

@Ignore
public class MergeSortTest {
  private static final Random RAND = new Random(42); // random number generator
  static int[] arr1, arr2, arr3, arr4, arr5;
  static MergeSortImpl x;

  @BeforeClass
  public static void setup() {
    x = new MergeSortImpl();
    arr1 = createRandomArray(8);
    arr2 = createRandomArray(1000);
    arr3 = createRandomArray(100000);
    arr4 = createRandomArray(2000001);
    arr5 = createRandomArray(20000001);

  }

  @Test
  public void seqMergeSort_8size() {
    x.seqMergeSort(arr1);
    Assert.assertTrue("sequentiell wurde nicht richtig sortiert, bei Array der Länge: 8", isSorted(arr1));
  }

  @Test
  public void seqMergeSort_1000size() {
    x.seqMergeSort(arr2);
    Assert.assertTrue("sequentiell wurde nicht richtig sortiert, bei Array der Länge: 1000", isSorted(arr2));
  }

  @Test
  public void seqMergeSort_100000size() {
    x.seqMergeSort(arr3);
    Assert.assertTrue("sequentiell wurde nicht richtig sortiert, bei Array der Länge: 100000", isSorted(arr3));
  }

  @Test
  public void seqMergeSort_2m1size() {
    x.seqMergeSort(arr4);
    Assert.assertTrue("sequentiell wurde nicht richtig sortiert, bei Array der Länge: 2000001", isSorted(arr4));
  }

  @Test
  public void parallel_8size_2threads() {
    x.parallelMergeSort(arr1, 2);
    Assert.assertTrue("parallel wurde nicht richtig sortiert, bei Array der Länge 8 und 2 Threads", isSorted(arr1));
  }

  @Test
  public void parallel_8size_8threads() {
    x.parallelMergeSort(arr1, 8);
    Assert.assertTrue("parallel wurde nicht richtig sortiert, bei Array der Länge 8 und 8 Threads", isSorted(arr1));
  }

  @Test
  public void parallel_1000size_2thread() {
    x.parallelMergeSort(arr2, 2);
    Assert.assertTrue("parallel wurde nicht richtig sortiert, bei Array der Länge 1000 und 2 Threads", isSorted(arr2));
  }

  @Test
  public void parallel_1000size_8thread() {
    x.parallelMergeSort(arr2, 8);
    Assert.assertTrue("parallel wurde nicht richtig sortiert, bei Array der Länge 100000 und 8 Threads",
        isSorted(arr2));
  }

  @Test
  public void parallel_2msize_2thread() {
    x.parallelMergeSort(arr4, 2);
    Assert.assertTrue("parallel wurde nicht richtig sortiert, bei Array der Länge 2000001 und 2 Threads",
        isSorted(arr4));
  }

  @Test
  public void parallel_20msize_32thread() {
    x.parallelMergeSort(arr5, 32);
    Assert.assertTrue("parallel wurde nicht richtig sortiert, bei Array der Länge 20000001 und 32 Threads",
        isSorted(arr5));
  }

  // Dieser Test vergleicht beide Implementierungen. Wenn die parallele Lösung
  // auch für große
  @Test
  public void vergleich() {
    long startTime1;
    long endTime1;
    long startTime2;
    long endTime2;
    long diff1 = 0;
    long diff2 = 0;

    int length = 1000;
    for (int i = 1; i <= 14; i++) {
      int[] a = createRandomArray(length);
      int[] b = Arrays.copyOf(a, a.length);

      startTime1 = System.currentTimeMillis();
      x.parallelMergeSort(a, 16);
      endTime1 = System.currentTimeMillis();
      diff1 = endTime1 - startTime1;

      System.out.printf("%10d elements  =>  %6d ms parallel ", length, diff1);

      startTime2 = System.currentTimeMillis();
      x.seqMergeSort(b);
      endTime2 = System.currentTimeMillis();
      diff2 = endTime2 - startTime2;
      System.out.printf(" vs. %6d ms sequentiell \n", diff2);
      length = length * 2;
    }
    Assert.assertTrue("Bei so großen Arrays sollte die parallele Variante schneller sein!", diff1 < diff2);
  }

  public static boolean isSorted(int[] a) {
    for (int i = 0; i < a.length - 1; i++) {
      if (a[i] > a[i + 1]) {
        return false;
      }
    }
    return true;
  }

  public static int[] createRandomArray(int length) {
    int[] a = new int[length];
    for (int i = 0; i < a.length; i++) {
      a[i] = RAND.nextInt(1000000);
    }
    return a;
  }
}
