package org.bschlangaul.graph;

import java.util.List;
import java.util.regex.Pattern;

public class TexAdjazenzListe {
  private GraphAdjazenzListe graph;

  public TexAdjazenzListe(GraphAdjazenzListe graph) {
    this.graph = graph;
  }

  private String formatiereTabelle(String inhalt) {
    return String.format("\\begin{tabular}{%s}\n%s\\end{tabular}\n",
        "l".repeat(graph.gibMaximaleUnterListenTiefe() + 1), inhalt);
  }

  private String formatiereZeile(int vonKnotenNr, List<GraphAdjazenzListe.Kante> unterListe) {
    String trenner = " & $\\rightarrow$ ";
    String ausgabe = graph.gibKnotenName(vonKnotenNr) + trenner;
    for (int i = 0; i < unterListe.size(); i++) {
      ausgabe += graph.gibKnotenName(unterListe.get(i).nachNr) + trenner;
    }

    return ausgabe.replaceFirst(Pattern.quote(trenner) + "$", " \\\\\\\\\n");
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
