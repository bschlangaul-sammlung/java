package org.bschlangaul.graph.tex;

import java.util.ArrayList;

import org.bschlangaul.graph.einfaches_format.GraphenFormat;
import org.bschlangaul.graph.einfaches_format.GraphenFormatKante;
import org.bschlangaul.graph.einfaches_format.GraphenFormatKnoten;
import org.bschlangaul.helfer.Tex;

/**
 * Formatiert über die Methode gibTikzUmgebung() den Graphen als
 * Tikz-Graphik, damit der Graph in LaTeX gesetzt werden kann.
 */
public class TexTikz {

  private GraphenFormat graph;

  public TexTikz(GraphenFormat graph) {
    this.graph = graph;
  }

  public String gibTikzUmgebung() {
    String ausgabe = "";

    for (GraphenFormatKnoten knoten : this.graph.gibKnoten()) {
      ausgabe += formatiereKnoten(knoten);
    }

    ausgabe += '\n';

    for (GraphenFormatKante kante : this.graph.gibKanten()) {
      ausgabe += formatiereKante(kante);
    }
    return Tex.umgebungOption("tikzpicture", ausgabe, "li graph");
  }

  private String formatiereZahl(double zahl) {
    return GraphenFormat.formatiereZahl(zahl);
  }

  private String formatiereKnoten(GraphenFormatKnoten knoten) {
    return String.format("\\node (%s) at (%s,%s) {%s};\n", knoten.name, formatiereZahl(knoten.x),
        formatiereZahl(knoten.y), knoten.name);
  }

  private String formatiereKante(GraphenFormatKante kante) {
    ArrayList<String> optionen = new ArrayList<String>();
    if (kante.markiert) {
      optionen.add("li markierung");
    }
    if (kante.gerichtet) {
      optionen.add("->");
    }
    String opt = "";
    if (optionen.size() > 0) {
      opt = "[" + String.join(",", optionen) + "]";
    }
    String gewicht = String.format(" node {%s}", formatiereZahl(kante.gewicht));
    return String.format("\\path%s (%s) edge%s (%s);\n", opt, kante.von, gewicht, kante.nach);
  }
}
