package org.bschlangaul.baum.visualisierung;

import org.bschlangaul.baum.BinaerBaum;
import org.bschlangaul.baum.BaumKnoten;
import org.bschlangaul.cli.KonsoleHelfer;

import tech.vanyo.tree_printer.TreePrinter;

public class TerminalBaumReporter extends BaumReporter {

  @Override
  public String erzeugeBaum(BinaerBaum baum) {
    TreePrinter<BaumKnoten> printer = new TreePrinter<>(knoten -> ("" + knoten.gibSchlüssel()),
        knoten -> knoten.gibLinks(), knoten -> knoten.gibRechts());

    printer.setHspace(2);
    printer.setSquareBranches(false);
    return "\n" + printer.generateTree(baum.gibKopf());
  }

  @Override
  public String erzeugeÜberschrift(String überschrift) {
    return KonsoleHelfer.erzeugeÜberschrift(überschrift);
  }

  @Override
  public String erzeugeTraversierung(BinaerBaum baum) {
    return "";
  }

}
