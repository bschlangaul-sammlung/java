package org.bschlangaul.examen.examen_46115.jahr_2017.fruehjahr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BubbleSortTest {

  @Test
  public void teste() {
    int[] array = new int[] { 3, 2, 4, 1 };
    BubbleSort.bubblesort(array);
    assertEquals(1, array[0]);
    assertEquals(2, array[1]);
    assertEquals(3, array[2]);
    assertEquals(4, array[3]);
  }
}
