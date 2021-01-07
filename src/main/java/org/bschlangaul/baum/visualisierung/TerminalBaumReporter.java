package org.bschlangaul.baum.visualisierung;

import org.bschlangaul.baum.BinaerBaum;

import com.jakewharton.fliptables.FlipTable;

import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.AVLBaumKnoten;
import org.bschlangaul.baum.BaumKnoten;
import org.bschlangaul.cli.KonsoleHelfer;

import tech.vanyo.tree_printer.TreePrinter;

import org.bschlangaul.helfer.Farbe;

public class TerminalBaumReporter extends BaumReporter {

  @Override
  public String erzeugeBaum(BinaerBaum baum) {
    TreePrinter<BaumKnoten> printer = new TreePrinter<>(knoten -> {
      String ausgabe = "";
      ausgabe += Farbe.gelb(knoten.gibSchlüssel());
      if (knoten instanceof AVLBaumKnoten) {
        AVLBaumKnoten avlKnoten = (AVLBaumKnoten) knoten;
        AVLBaum avlBaum = (AVLBaum) baum;
        // ausgabe += " h" + avlKnoten.gibHöhe();
        ausgabe += " " + Farbe.cyan(avlBaum.gibBalance(avlKnoten));
      }
      return ausgabe;
    }, knoten -> knoten.gibLinks(), knoten -> knoten.gibRechts());

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
    String[] kopfZeile = { "Methode", "Schlüssel-Reihenfolge" };
    String[][] zeilen = sammleTraversierungsDaten(baum);
    return erzeugeTabelle(kopfZeile, zeilen);
  }

  public String erzeugeTabelle(String[] kopfZeile, String[][] zeilen) {
    return FlipTable.of(kopfZeile, zeilen);
  }

}
