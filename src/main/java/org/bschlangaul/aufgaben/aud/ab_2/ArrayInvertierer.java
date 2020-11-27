package org.bschlangaul.aufgaben.aud.ab_2;

public class ArrayInvertierer {

  /**
   * Invertiert das übergebene String Feld rekursiv durch Aufruf der
   * Methode in {@link invertiereRekursiv} mit dem jeweils
   * aktuellen Arrayindex als Startwert.
   *
   * @param quelle Das Feld, dessen Inhalt invertiert werden soll.
   * @param ziel Hilfs-Feld.
   * @param index aktueller Index
   */
  private static void invertiereRekursiv(String[] quelle, String[] ziel, int index) {
    if (index < quelle.length) {
      ziel[quelle.length - index - 1] = quelle[index];
      invertiereRekursiv(quelle, ziel, ++index);
    }
  }

  /**
   * Invertiert das übergebene String Feld rekursiv durch Aufruf der
   * Methode in {@link invertiereRekursiv} mit dem Hilfsfeld und dem
   * ersten Feldindex als Startwert.
   *
   * @param quelle Das Feld, dessen Inhalt invertiert werden soll.
   *
   * @return Ein neues Feld, das den umgekehrten Inhalt besitzt.
   */
  private static String[] invertiereRekursiv(String[] quelle) {
    String[] ziel = new String[quelle.length];
    invertiereRekursiv(quelle, ziel, 0);
    return ziel;
  }

  /**
   * Die Lösung für die optionale Aufgaben. In situ bedeutet, dass
   * kein neues Feld erzeugt wird.
   *
   * @param quelle Ein Feld mit Wörtern.
   * @param index Die Index-Nummer, die bearbeitet werden soll.
   */
  private static void invertiereRekursivInSitu(String[] quelle, int index) {
    if (index < quelle.length / 2) {
      int gespiegelterIndex = quelle.length - 1 - index;
      String tmp = quelle[gespiegelterIndex];
      quelle[gespiegelterIndex] = quelle[index];
      quelle[index] = tmp;
      invertiereRekursivInSitu(quelle, ++index);
    }
  }

  /**
   * Hilfsmethode zur Ausgabe des String-Arrays in einem Satz.
   *
   * @param feld Ein Feld mit Wörtern.
   */
  private static void gibFeldAus(String[] feld) {
    System.out.println(String.join(" ", feld));
  }

  /**
   * Lass Meister Yoda sprechen.
   *
   * @param satz Ein Feld mit Wörtern.
   * @param inSitu Bei wahr wird die Methode
   * {@link invertiereRekursivInSitu} verwendet. Achtung! Dadurch wird
   * das Feld verändert.
   */
  public static void lassYodaSprechen(String[] satz, boolean inSitu) {
    System.out.println("\nDen Satz");
    System.out.print("  ");
    gibFeldAus(satz);
    System.out.println("würde Meister Yoda so aussprechen:");
    System.out.print("  ");
    if (!inSitu) {
      gibFeldAus(invertiereRekursiv(satz));
    } else {
      invertiereRekursivInSitu(satz, 0);
      gibFeldAus(satz);
    }
  }

  public static void main(String[] args) {
    lassYodaSprechen(new String[] { "Ich", "find", "dich", "einfach", "klasse!" }, false);
    lassYodaSprechen(new String[] { "Das", "war", "super", "einfach/schwer" }, true);
  }

}
