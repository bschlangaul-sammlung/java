package org.bschlangaul.aufgaben.sosy.examen_64116_2014_09;

public class Hanoi {
  int hanoi(int nr, char from, char to) {
    char free = (char) ('A' + 'B' + 'C' - from - to);
    if (nr > 0) {
      int moves = 1;
      moves += hanoi(nr - 1, from, free);
      System.out.println("Move piece nr. " + nr + " from " + from + " to " + to);
      moves += hanoi(nr - 1, free, to);
      return moves;
    } else {
      return 0;
    }
  }
}
