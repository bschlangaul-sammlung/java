package org.bschlangaul.baum.tex;

import java.util.ArrayList;

import org.bschlangaul.baum.Baum;
import org.bschlangaul.baum.BinaerBaum;

@SuppressWarnings("rawtypes")
public class TexBaumTraversierung {
  public String generiereTraversierung(Baum baum) {
    String ausgabe = "";
    ausgabe += "\n\n\\subsection{Traversierung}\n\n";
    for (int i = 0; i < 4; i++) {
      ArrayList<Comparable> schlüssel = baum.traversiere(i);
      ausgabe += "\\subsubsection{" + BinaerBaum.traversierungsNamen[i] + "}\n";
      int zähler = 0;
      for (Comparable s : schlüssel) {
        zähler++;
        if (zähler < schlüssel.size()) {
          ausgabe += s + ", ";
        } else {
          ausgabe += s.toString();
        }
      }
      ausgabe += "\n\n";
    }
    return ausgabe;
  }
}
