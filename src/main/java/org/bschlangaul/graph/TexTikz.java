package org.bschlangaul.graph;

import org.bschlangaul.helfer.Tex;

public class TexTikz {

  private EinfachesGraphenFormat graph;

  public TexTikz(EinfachesGraphenFormat graph) {
    this.graph = graph;
  }

  public String gibTikzUmgebung() {
    String ausgabe = "";

    for (EinfachesGraphenFormat.Knoten knoten : this.graph.gibKnoten()) {
      ausgabe += formatiereKnoten(knoten);
    }

    ausgabe += '\n';

    for (EinfachesGraphenFormat.Kante kante : this.graph.gibKanten()) {
      ausgabe += formatiereKante(kante);
    }
    return Tex.umgebung("tikzpicture", ausgabe);
  }

  private String formatiereZahl(double zahl) {
    return EinfachesGraphenFormat.formatiereZahl(zahl);
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

  private String formatiereKnoten(EinfachesGraphenFormat.Knoten knoten) {
    return String.format("\\node[li graph knoten] (%s) at (%s,%s) {%s};\n", knoten.name, formatiereZahl(knoten.x), formatiereZahl(knoten.y), knoten.name);
  }

  private String formatiereKante(EinfachesGraphenFormat.Kante kante) {
    String gerichtet = kante.gerichtet ? ",->" : "";
    String gewicht = kante.gewicht != 1 ? String.format(" node {%s}", formatiereZahl(kante.gewicht)) : "";
    return String.format("\\path[li graph kante%s] (%s) edge%s (%s);\n", gerichtet, kante.von, gewicht, kante.nach);
  }
}
