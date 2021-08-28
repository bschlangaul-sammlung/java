package org.bschlangaul.aufgaben.aud.komplexitaet;

public class Komplexitaet {

  public void magicStaff(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int counter = 0;
      if (array[i] % 3 == 0) {
        break;
      }
      do {
        if (array[i] % 2 == 0) {
          array[i] += array[counter];
        }
      } while (counter++ < array.length);
    }
  }
}
