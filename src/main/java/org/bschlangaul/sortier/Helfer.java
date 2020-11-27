package org.bschlangaul.sortier;

public class Helfer {
  static void vertausche(int[] zahlen, int index1, int index2) {
    int tmp = zahlen[index1];
    zahlen[index1] = zahlen[index2];
    zahlen[index2] = tmp;
  }
}
