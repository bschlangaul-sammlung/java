package org.bschlangaul.examen.examen_46115.jahr_2015.herbst;

public class UnimodalFinder {

  public final static int KEIN_MAX = Integer.MIN_VALUE;

  /**
   * Nach den Pseudo-Codes der <a href=
   * "https://web.stanford.edu/class/archive/cs/cs161/cs161.1138/lectures/06/Small06.pdf">Standford-Universität</a>
   * <a
   * href=https://cs.stackexchange.com/questions/9888/algorithm-to-find-the-mode-in-a-unimodal-array">Stackexchange
   * (CS = Computer Science)</a>.
   *
   * @param feld   Ein Zahlen-Feld, in dem nach dem Maximum gesucht werden soll.
   * @param links  Die linke Index-Nummer, ab der die Suche durchgeführt werden
   *               soll.
   * @param rechts Die rechte Index-Nummer, bis zu der die Suche durchgeführt
   *               werden soll.
   *
   * @return Das Maximum, wenn das Feld tatsächlich eine unimodale Reihe ist,
   *         sonst irgendeine Zahl.
   */
  public static int findeMaxRekursiv(int feld[], int links, int rechts) {
    if (links == rechts - 1) {
      return feld[links];
    }
    // ⌊ ⌋ bedeutet aufrunden
    // https://stackoverflow.com/a/17149572
    int mitte = (int) Math.ceil((double) (links + rechts) / 2);
    if (feld[mitte - 1] < feld[mitte]) {
      return findeMaxRekursiv(feld, mitte, rechts);
    } else {
      return findeMaxRekursiv(feld, links, mitte);
    }
  }

  public static int findeMaxRekursiv(int feld[]) {
    return findeMaxRekursiv(feld, 0, feld.length - 1);
  }

  /**
   *
   *
   * Nach dem Java-Code auf <a href=
   * "https://github.com/yosriady/Other-Java-code/blob/master/Unimodal.java">Github</a>.
   *
   * @param feld Ein Zahlen-Feld, in dem nach dem Maximum gesucht werden soll.
   *
   * @return Das Maximum, wenn das Feld tatsächlich eine unimodale Reihe ist,
   *         sonst die kleinste mögliche Zahl, die in Java als Integer darstellbar
   *         ist.
   */
  public static int findeMaxIterativ(int[] feld) {
    int links = 0;
    int rechts = feld.length - 1;
    int mitte;

    while (links < rechts) {
      mitte = links + (rechts - links) / 2;
      if (feld[mitte] > feld[mitte - 1] && feld[mitte] > feld[mitte + 1]) {
        return feld[mitte];
      } else if (feld[mitte] > feld[mitte - 1]) {
        links = mitte + 1;
      } else {
        rechts = mitte - 1;
      }
    }
    return KEIN_MAX;
  }

  public static boolean testeUnimodalität(int[] feld) {
    if (feld.length < 2) {
      // Die Reihe braucht mindestens 3 Einträge
      return false;
    }

    if (feld[0] > feld[1]) {
      // Die Reihe muss zuerst ansteigen
      return false;
    }

    boolean maxErreicht = false;
    for (int i = 0; i < feld.length - 1; i++) {
      if (feld[i] > feld[i + 1] && !maxErreicht) {
        maxErreicht = true;
      }

      if (maxErreicht && feld[i] < feld[i + 1]) {
        // Das Maximum wurde bereichts erreicht und die nächste Zahl ist größer
        return false;
      }
    }

    // Es wurde kein Maximum gefunden (aufsteigende Reihe, Maximum ist letztes
    // Zeichen)
    if (!maxErreicht) {
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    findeMaxIterativ(new int[] { -1, -2, 3, 1 });
  }

}
