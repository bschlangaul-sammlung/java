package org.bschlangaul.aufgaben.aud.examen_66115_2015_03;

public class Sort {

  /**
   * So ähnlich wie der <a href=
   * "https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/">Stapel-Sortiert-Algorithmus
   * der nur Stapel verwendet</a>, nur mit einer Warteschlange.
   *
   * @param s Der Stapel, der sortiert wird.
   */
  public static void sort(Stapel s) {
    Schlange q = new Schlange();
    while (!s.isEmpty()) {
      q.enqueue(s.pop());
    }
    while (!q.isEmpty()) {
      // Sortiert aufsteigend. Für absteigend einfach das „kleiner“
      // Zeichen umdrehen.
      while (!s.isEmpty() && s.top() < q.head()) {
        q.enqueue(s.pop());
      }
      s.push(q.dequeue());
    }
  }

  public static Stapel stapelBefüllen(int[] zahlen) {
    Stapel s = new Stapel();
    for (int i : zahlen) {
      s.push(i);
    }
    return s;
  }

  public static void zeigeStapel(Stapel s) {
    while (!s.isEmpty()) {
      System.out.print(s.pop() + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Stapel s1 = stapelBefüllen(new int[] { 4, 2, 1, 5, 3 });
    sort(s1);
    zeigeStapel(s1);

    Stapel s2 = stapelBefüllen(new int[] { 1, 2, 6, 3, 9, 11, 4 });
    sort(s2);
    zeigeStapel(s2);
  }
}
