package org.bschlangaul.helfer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class DateiSchreiber {

  public static void schreibe(String dateiPfad, String inhalt) {
    try {
      Writer ziel = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateiPfad), "UTF-8"));
      ziel.write(inhalt);
      ziel.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
