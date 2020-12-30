package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import tech.vanyo.tree_printer.TreePrinter;
import tech.vanyo.tree_printer.TreeNode;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.baum.AVLBaum;
import org.bschlangaul.baum.Baum;

import org.bschlangaul.baum.BinaerBaum;
import org.bschlangaul.baum.Knoten;

/**
 * Die Baum-Klassen im Paket org.bschlangaul.baum arbeiten mit
 * sogenannten Null-Knoten. Das ist die Ursache, dass die Knoten-Baume
 * nicht mit der {@link TreePrinter}-Klasse funktionieren. Dieser Kloner
 * stellt einen Baum aus {@link TreeNode}s her.
 */
class BaumKloner {

  public static TreeNode klone(Knoten knoten) {

    TreeNode ziel = new TreeNode((int) knoten.gibSchlüssel());
    klone(knoten, ziel);
    return ziel;
  }

  private static void klone(Knoten quelle, TreeNode ziel) {
    if (quelle == null) {
      return;
    }
    if (quelle.gibLinks() != null && quelle.gibLinks().gibSchlüssel() != null) {
      Knoten links = quelle.gibLinks();
      ziel.setLeft(new TreeNode((int) links.gibSchlüssel()));
      klone(links, ziel.getLeft());
    }
    if (quelle.gibRechts() != null && quelle.gibRechts().gibSchlüssel() != null) {
      Knoten rechts = quelle.gibRechts();

      ziel.setRight(new TreeNode((int) rechts.gibSchlüssel()));
      klone(rechts, ziel.getRight());
    }

  }
}

@Command(name = "baum", aliases = {
    "b" }, mixinStandardHelpOptions = true, description = "Führe baumspezifische Aufgaben aus.")
class SubKommandoBaum implements Callable<Integer> {

  @Option(names = { "-a", "--avl", "--avl-baum" }, description = "Als AVL-Baum ausgeben.")
  boolean isAvl;

  @Parameters(arity = "1..*", description = "Zahlen, die in den Baum eingefügt werden sollen.")
  List<Integer> werte;

  @Override
  public Integer call() {

    Baum baum;

    if (isAvl) {
      baum = new AVLBaum();
    } else {
      baum = new BinaerBaum();
    }

    for (int i = 0; i < werte.size(); i++) {
      baum.fügeEin(werte.get(i));
    }
    Knoten wurzel = baum.gibKopf();

    TreeNode geklonterBaum = BaumKloner.klone(wurzel);

    TreePrinter<TreeNode> printer = new TreePrinter<>(n -> ("" + n.getValue()), n -> n.getLeft(), n -> n.getRight());

    printer.setHspace(2);
    printer.setSquareBranches(false);
    System.out.println();

    printer.printTree(geklonterBaum);
    System.out.println();

    return 0;
  }
}
