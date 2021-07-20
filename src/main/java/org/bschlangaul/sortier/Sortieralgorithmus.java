package org.bschlangaul.sortier;

public abstract class Sortieralgorithmus {

  int[] zahlen;

  public Sortieralgorithmus() {
    this.zahlen = new int[] {};
  }

  public Sortieralgorithmus(int[] zahlen) {
    this.zahlen = zahlen;
  }

  public void setzeZahlen(int[] zahlen) {
    this.zahlen = zahlen;
  }

  /**
   * Vertausche zwei Zahlen im einem Zahlen-Feld. Im Englischen heißt die Methode
   * auch oft „swap“.
   *
   * @param zahlen Ein Feld mit Zahlen.
   * @param index1 Die Index-Nummer der ersten Zahl.
   * @param index2 Die Index-Nummer der zweiten Zahl.
   */
  protected void vertausche(int index1, int index2) {
    int tmp = zahlen[index1];
    zahlen[index1] = zahlen[index2];
    zahlen[index2] = tmp;
  }

  /**
   * Zeige einen Ausschnitt der Zahlen in der Textkonsole. Es handelt sich um eine
   * Hilfsmethode, um die Sortieralgorithmen besser verstehen zu können.
   *
   * @param links  Die linke Grenze, die gezeigt werden soll.
   * @param rechts Die rechte Grenze, die gezeigt werden soll.
   */
  protected void druckeZahlen(int links, int rechts) {
    for (int i = links; i <= rechts; i++) {
      System.out.print(zahlen[i] + " ");
    }
    System.out.println();
  }

  /**
   * Zeige die Zahlen in der Textkonsole. Es handelt sich um eine Hilfsmethode, um
   * die Sortieralgorithmen besser verstehen zu können.
   */
  protected void druckeZahlen() {
    for (int i = 0; i < zahlen.length; i++) {
      System.out.print(zahlen[i] + " ");
    }
    System.out.println();
  }

  public abstract int[] sortiere();

  public int[] sortiere(int[] zahlen) {
    this.setzeZahlen(zahlen);
    return sortiere();
  }

  /**
   * Teste den Algorithmus mit einer gegeben Zahlenreihe und zeige das sortierte
   * Ergebnis aus der Kommandozeile.
   */
  public void teste(int[] zahlen) {
    setzeZahlen(zahlen);
    druckeZahlen();
    System.out.println("sortiere:");
    sortiere();
    druckeZahlen();
    System.out.println();
  }

  /**
   * Teste den Algorithmus mit einigen Zahlenreihen und zeige das sortierte
   * Ergebnis aus der Kommandozeile.
   */
  public void teste() {
    System.out.println("Teste 1-10 zufällig angeordnet");
    teste(new int[] { 4, 7, 1, 10, 8, 3, 6, 2, 9, 5 });

    System.out.println("Teste 1-10 bereits sortiert");
    teste(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });

    System.out.println("Teste 1-10 absteigend sortiert");
    teste(new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 });

    System.out.println("Teste negative Zahlen");
    teste(new int[] { -5, 5, -26, 42, 8, 78, -1, 0, -74 });

    System.out.println("Teste ein Wert");
    teste(new int[] { 1 });

    System.out.println("Teste leeres Feld");
    teste(new int[] { });
  }
}
