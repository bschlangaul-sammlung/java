package org.bschlangaul.graph;

import org.bschlangaul.graph.einfaches_format.GraphenFormat;
import org.bschlangaul.graph.einfaches_format.GraphenFormatKante;
import org.bschlangaul.graph.einfaches_format.GraphenFormatKnoten;
import org.bschlangaul.helfer.Tex;

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
    return Tex.umgebung("tikzpicture", ausgabe, "li graph");
  }

  private String formatiereZahl(double zahl) {
    return GraphenFormat.formatiereZahl(zahl);
  }

  // \def\knoten#1(#2,#3){
  // \node (#1) at (#2,#3) {#1};
  // }

  // \def\kante(#1-#2)#3{
  // \path (#1) edge node {#3} (#2);
  // }

  // \def\kanteO(#1-#2){
  // \path (#1) edge (#2);
  // }

  // \def\kanteR(#1>#2)#3{
  // \path[->] (#1) edge node {#3} (#2);
  // }

  // \def\KANTE(#1-#2)#3{
  // \path[ultra thick] (#1) edge node {#3} (#2);
  // }

  private String formatiereKnoten(GraphenFormatKnoten knoten) {
    return String.format("\\node (%s) at (%s,%s) {%s};\n", knoten.name, formatiereZahl(knoten.x),
        formatiereZahl(knoten.y), knoten.name);
  }

  private String formatiereKante(GraphenFormatKante kante) {
    String gerichtet = kante.gerichtet ? ",->" : "";
    String gewicht = kante.gewicht != 1 ? String.format(" node {%s}", formatiereZahl(kante.gewicht)) : "";
    return String.format("\\path[li graph kante%s] (%s) edge%s (%s);\n", gerichtet, kante.von, gewicht, kante.nach);
  }
}
