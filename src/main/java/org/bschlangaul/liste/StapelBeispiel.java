package org.bschlangaul.liste;

/**
 * Nutzung des Datentyps Stapel.
 *
 * nach Saake/Sattler: Algorithmen und Datenstrukturen (Seite 317/318),
 * heißt im Original „StackExample“.
 */
public class StapelBeispiel {
  public static void main(String args[]) {
    ListeStapel stack = new ListeStapel();
    try {
      // Elemente auf Stack ablegen
      stack.fügeHinzu("Eins");
      stack.fügeHinzu("Zwei");
      stack.fügeHinzu("Drei");
      while (!stack.istLeer()) {
        // Elemente herunternehmen
        String s = (String) stack.entnimm();
        System.out.println(s);
      }
    } catch (StapelFehler exc) {
      System.out.println(exc);
    }

    stack = new ListeStapel();
    stack.fügeHinzu(Integer.valueOf(1));
    stack.fügeHinzu(Integer.valueOf(2));
    stack.fügeHinzu(Integer.valueOf(3));
    while (!stack.istLeer()) {
      try {
        Integer i = (Integer) stack.entnimm();
        System.out.println(i.intValue());
      } catch (StapelFehler exc) {
        System.out.println(exc);
      }
    }
  }
}
