package org.bschlangaul.aufgaben.aud.ab_7.mergesort;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * https://www.studon.fau.de/file2617983_download.html
 */
public class MergesortTest {
  Mergesort m = new Mergesort();

  @Test
  public void existMethod_mergesort() {
    boolean exists_mergesort = false;
    try {
      m.mergesort((new int[1]));
      exists_mergesort = true;
    } catch (NoSuchMethodError e) {
      // ignore
    }
    assertEquals(true, exists_mergesort);
  }

  @Test
  public void existMethod_merge() {
    boolean exists_merge = false;
    try {
      m.merge((new int[1]), (new int[1]));
      exists_merge = true;
    } catch (NoSuchMethodError e) {
      // ignore
    }
    assertEquals(true, exists_merge);
  }

  @Test
  public void sortTest1() {
    // reverse sorted
    int[] testArray = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    int[] testArrayCorrect = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    assertArrayEquals(m.mergesort(testArray), testArrayCorrect);
  }

  @Test
  public void sortTest2() {
    // already sorted
    int[] testArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int[] testArrayCorrect = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    assertArrayEquals(m.mergesort(testArray), testArrayCorrect);
  }

  @Test
  public void sortTest3() {
    // random array
    int[] testArray = { 4, 7, 1, 10, 8, 3, 6, 2, 9, 5 };
    int[] testArrayCorrect = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    assertArrayEquals(m.mergesort(testArray), testArrayCorrect);
  }

  @Test
  public void sortTes4() {
    // empty array
    int[] testArray = {};
    int[] testArrayCorrect = {};
    assertArrayEquals(m.mergesort(testArray), testArrayCorrect);
  }

  @Test
  public void sortTest5() {
    // array with one entry
    int[] testArray = { 4 };
    int[] testArrayCorrect = { 4 };
    assertArrayEquals(m.mergesort(testArray), testArrayCorrect);
  }

  @Test
  public void sortTest6() {
    // random array with negativ numers
    int[] testArray = { -5, 5, -26, 42, 8, 78, -1, 0, -74 };
    int[] testArrayCorrect = { -74, -26, -5, -1, 0, 5, 8, 42, 78 };
    assertArrayEquals(m.mergesort(testArray), testArrayCorrect);
  }
}
