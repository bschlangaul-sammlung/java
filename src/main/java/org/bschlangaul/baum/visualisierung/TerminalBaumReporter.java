package org.bschlangaul.baum.visualisierung;

import org.bschlangaul.baum.Baum;
import org.bschlangaul.baum.BaumKnoten;
import org.bschlangaul.cli.KonsoleHelfer;

import tech.vanyo.tree_printer.TreePrinter;

public class TerminalBaumReporter extends BaumReporter {

  @Override
  public void visualisiereBaum(Baum baum) {
    TreePrinter<BaumKnoten> printer = new TreePrinter<>(knoten -> ("" + knoten.gibSchlüssel()), knoten -> knoten.gibLinks(),
        knoten -> knoten.gibRechts());

    printer.setHspace(2);
    printer.setSquareBranches(false);
    System.out.println();

    printer.printTree(baum.gibKopf());
    System.out.println();
  }

  @Override
  public void visualisiereÜberschrift(String überschrift) {
    KonsoleHelfer.gibÜberschriftAus(überschrift);
  }

}
