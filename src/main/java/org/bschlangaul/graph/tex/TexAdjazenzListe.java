package org.bschlangaul.graph.tex;

import java.util.List;

import org.bschlangaul.graph.GraphAdjazenzListe;
import org.bschlangaul.graph.einfaches_format.GraphenFormat;

public class TexAdjazenzListe {
  private GraphAdjazenzListe graph;

  public TexAdjazenzListe(GraphAdjazenzListe graph) {
    this.graph = graph;
  }

  private String formatiereTabelle(String inhalt) {
    return String.format("\\begin{tabular}{%s}\n%s\\end{tabular}\n",
        "l".repeat(graph.gibMaximaleUnterListenTiefe() + 1), inhalt);
  }

  private String formatiereTrenner(GraphAdjazenzListe.Kante kante) {
    if (kante.gewicht != 1) {
      return String.format(" & $\\xrightarrow{~%s~}$ ", GraphenFormat.formatiereZahl(kante.gewicht));
    }
    return " & $\\rightarrow$ ";
  }

  private String formatiereZeile(int vonKnotenNr, List<GraphAdjazenzListe.Kante> unterListe) {
    String ausgabe = graph.gibKnotenName(vonKnotenNr) + ":";
    for (int i = 0; i < unterListe.size(); i++) {
      GraphAdjazenzListe.Kante kante = unterListe.get(i);
      ausgabe += formatiereTrenner(kante);
      ausgabe += graph.gibKnotenName(kante.nachNr);
    }
    return ausgabe + " \\\\\n";
  }

  public String gibTexAusgabe() {
    String inhalt = "";
    for (int i = 0; i < graph.liste.size(); i++) {
      inhalt += formatiereZeile(i, graph.liste.get(i));
    }
    return formatiereTabelle(inhalt);
  }

  public static void main(String[] args) {
    TexAdjazenzListe tex = new TexAdjazenzListe(new GraphAdjazenzListe("a--b: 1; a--c: 2;a--d: 3"));
    System.out.println(tex.gibTexAusgabe());
  }

}
