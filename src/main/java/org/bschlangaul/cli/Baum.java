package org.bschlangaul.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import tech.vanyo.tree_printer.TreePrinter;
import tech.vanyo.tree_printer.TreeNode;

import java.util.List;
import java.util.concurrent.Callable;

import org.bschlangaul.baum.BinaerBaum;
import org.bschlangaul.baum.Knoten;

@Command(name = "baum", aliases = {
    "b" }, mixinStandardHelpOptions = true, description = "Führe baumspezifische Aufgaben aus.")
class Baum implements Callable<Integer> {

  @Option(names = { "-a", "--avl", "--avl-baum" }, description = "Als AVL-Baum ausgeben.")
  boolean isAvl;

  @Parameters(arity = "1..*", description = "Zahlen, die in den Baum eingefügt werden sollen.")
  List<Integer> werte;

  @Override
  public Integer call() {

    // BinaerBaum baum = new BinaerBaum();
    // baum.fügeEin(15, 7, 2, 8, 19, 29);
    // Knoten knoten = baum.gibKopf();

    TreeNode root = new TreeNode(3);
    root.setLeft(new TreeNode(1));
    root.setRight(new TreeNode(5));

    TreePrinter<TreeNode> printer = new TreePrinter<TreeNode>(n -> ("" + n.getValue()), n -> n.getLeft(), n -> n.getRight());
    printer.printTree(root);
    // for (int i = 0; i < werte.size(); i++) {
    //   System.out.println(werte.get(i));
    // }
    // System.out.println(isAvl);
    return 0;
  }
}
