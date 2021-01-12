package org.bschlangaul.aufgaben.tech_info.philosoph;

public class Verklemmung {

  public static void PhilosophenDeadlock(int anzahl) {
    PhilosophDeadlock[] p = new PhilosophDeadlock[anzahl];
    Gabel[] f = new Gabel[anzahl];
    for (int i = 0; i < anzahl; i++) {
      f[i] = new Gabel(i);
    }
    for (int i = 0; i < anzahl; i++) {
      if (i != anzahl - 1) {
        p[i] = new PhilosophDeadlock(f[i], f[i + 1], i);
      } else {
        p[i] = new PhilosophDeadlock(f[i], f[0], i); // Der letzte Philosoph bekommt die letzte und die erste Gabel
      }

    }
    for (int i = 0; i < anzahl; i++) {
      new Thread(p[i]).start();
    }
  }

  public static void PhilosophenGleichzeitigeBelegung(int anzahl) {
    // TODO
  }

  public static void PhilosophenGlobaleOrdnung(int anzahl) {
    // TODO
  }

  public static void main(String[] args) {
    Verklemmung.PhilosophenDeadlock(3);
    // Verklemmung.PhilosophenGleichzeitigeBelegung(7);
    // Verklemmung.PhilosophenGlobaleOrdnung(5);
  }

}
