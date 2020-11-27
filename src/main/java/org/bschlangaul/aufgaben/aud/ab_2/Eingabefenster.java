package org.bschlangaul.aufgaben.aud.ab_2;

/**
 * https://www.studon.fau.de/file2548490_download.html
 *
 * https://www.studon.fau.de/file2548491_download.html
 *
 * Die originale Klasse liegt nur als Kompilat vor. Hier eine Klasse, die die
 * benötigten Methoden imitiert durch reine Textausgabe.
 */
public class Eingabefenster {

  public void schreibeZeile(String ausgabe) {
    System.out.println(ausgabe);
  }

  public void schreibe(String ausgabe) {
    System.out.print(ausgabe);
  }

  public void schreibeArray(int[] array) {
    String ausgabe = "";
    for (int i = 0; i < array.length; i++) {
      if (i == 0) {
        ausgabe = ausgabe +  array[i];
      } else {
        ausgabe = ausgabe  + ", " + array[i];
      }
    }
    System.out.print(ausgabe);
  }
}
