package org.bschlangaul.helfer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TexDateiSchreiber {

  Writer ziel;

  public TexDateiSchreiber(String baumArt) {

    öffneTeXDokument();
  }

  public TexDateiSchreiber() {
    this("binaer");
  }

  private void öffneTeXDokument() {
    DateTimeFormatter datum = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    try {
      ziel = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
          System.getProperty("user.home") + "/git-repositories/github/hbschlang/lehramt-informatik/tmp-binaerer-baum__"
              + datum.format(LocalDateTime.now()) + ".tex"),
          "UTF-8"));
      ziel.write("\\documentclass{lehramt-informatik}\n" + "\\InformatikPakete{baum}\n\n" + "\\begin{document}\n");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void schließeTeXDokument() {
    try {
      ziel.write("\\end{document}\n");
      ziel.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // BinaerBaumNachTikz baum = new BinaerBaumNachTikz();
    // baum.fügeEin(4, 6, 19, 2, 3, 15, 1, 8, 5, 7);
    // baum.generiereTikz();
    // baum.fügeEin(9, 10, 11);
    // baum.generiereTikz();
    // baum.schließeTeXDokument();

    TexDateiSchreiber baum = new TexDateiSchreiber();
    baum.schließeTeXDokument();

    // BaumTikzKonvertierer baum = new BaumTikzKonvertierer("avl");
    // baum.fügeEin(1, 2, 3, 4, 5);
    // baum.generiereTikz();
    // baum.schließeTeXDokument();
  }

}
