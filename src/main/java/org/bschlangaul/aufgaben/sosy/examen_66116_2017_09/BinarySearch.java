package org.bschlangaul.aufgaben.sosy.examen_66116_2017_09;

/**
 * BinarySearch.java
 *
 * Eine Implementierung der ”Binaere Suche”
 * mit einem iterativen Algorithmus
 */
class BinarySearch {

  /**
   * BinaereSuche
   * a: Eingabefeld
   * item: zusuchendesElement
   * returnValue: der Index des zu suchenden Elements oder -1
   *
   * Vorbedingung:
   * a.length > 0
   * a ist ein linear geordnetes Feld:
   * For all k: (1 <= k < a.length) ==> (a[k−1] <=a [k])
   *
   * Nachbedingung:
   * Wenn item in a, dann gibt es ein k mit a[k] == item und returnValue == k
   * Genau dann wenn returnValue == −1 gibt es kein k mit 0 <= k < a.length
   * und a[k]==item.
   */
  public static int binarySearch(float a[], float item) {

    int End;  // exklusiver Index fuer das Ende des
              // zudurchsuchenden Teils des Arrays
    int start = 1; // inklusiver Index fuer den Anfang der Suche
    End = a.length;

    // Die Schleife wird verlassen, wenn keine der beiden Haelften das
    // Element enthaelt.
    while (start < End) {

      // Teilung des Arrays in zwei Haelften
      // untere Haelfte: [0,mid[
      // obere Haelfte: ]mid,End[
      int mid = (start + End) / 2;

      if (item > a[mid]) {
        // Ausschluss der oberen Haelfte
        start = mid + 1;
      } else if (item < a[mid]) {
        // Ausschluss der unteren Haelfte
        End = mid - 1;
      } else {
        // Das gesuchte Element wird zurueckgegeben
        return (mid);
      }
    } // end of while

    // Bei Misserfolg der Suche wird −1 zurueckgegeben
    return (-1);
  }
}
