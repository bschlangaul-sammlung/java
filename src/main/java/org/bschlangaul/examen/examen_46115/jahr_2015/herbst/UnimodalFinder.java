package org.bschlangaul.examen.examen_46115.jahr_2015.herbst;

public class UnimodalFinder {

  /**
   * https://gist.github.com/viniru/6f134fecc98a15465bae2149ef89a3f7
   *
   * @param a
   * @param l
   * @param h
   */
  public static int findeMaxRekursiv(int a[], int l, int h) {
    int mid = (l + h) / 2;
    if (a[mid] < a[mid + 1]) {
      if (a[mid + 1] > a[mid + 2]) {
        return a[mid + 1];
      } else {
        return findeMaxRekursiv(a, mid + 1, h);
      }
    }

    return findeMaxRekursiv(a, l, mid);

  }

  /**
   * https://github.com/yosriady/Other-Java-code/blob/master/Unimodal.java
   *
   * @param A
   * @param size
   * @return
   */
  public static int findeMaxIterativ(int[] A, int size) {
    int begin = 0;
    int end = size - 1;
    int mid;

    while (begin < end) {
      mid = begin + (end - begin) / 2;
      if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
        return A[mid];
      } else if (A[mid] > A[mid - 1]) {
        begin = mid + 1;
      } else {
        // if the element on the left of mid is bigger
        end = mid - 1;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    int[] test = { 1, 3, 4, 6, 7, 8, 9, 11, 6, 5, 4, 3, 2 };

    System.out.println(findeMaxIterativ(test, test.length));

    int a[] = { 1, 2, 3, 1 };
    System.out.println(findeMaxRekursiv(a, 0, a.length - 1));

  }

}
