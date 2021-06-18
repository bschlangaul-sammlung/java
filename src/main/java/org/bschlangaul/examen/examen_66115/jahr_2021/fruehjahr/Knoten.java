package org.bschlangaul.examen.examen_66115.jahr_2021.fruehjahr;

public class Knoten {
  public Knoten links;
  public Knoten rechts;
  public String name;

  /**
   * Bewegung bezüglich des Vorknotens. Relative Lage.
   */
  public int xVerschiebung;

  public Knoten(String name, int xVerschiebung) {
    this.name = name;
    this.xVerschiebung = xVerschiebung;
  }

  public void durchlaufen() {
    durchlaufe(this, 0 + xVerschiebung, 0);
  }

  private void durchlaufe(Knoten knoten, int x, int y) {
    System.out.println("Beschriftung: " + knoten.name + " x: " + x + " y: " + y);

    if (links != null) {
      links.durchlaufe(links, x + links.xVerschiebung, y + 1);
    }
    if (rechts != null) {
      rechts.durchlaufe(rechts, x + rechts.xVerschiebung, y + 1);
    }
  }

  public static void main(String[] args) {
    Knoten a = new Knoten("a", 1);
    Knoten b = new Knoten("b", 1);
    Knoten c = new Knoten("c", -2);
    Knoten d = new Knoten("d", 2);
    Knoten e = new Knoten("e", 0);

    a.links = b;
    a.rechts = c;
    c.links = d;
    c.rechts = e;

    a.durchlaufen();
  }
}
