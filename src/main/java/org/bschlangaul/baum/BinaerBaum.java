package org.bschlangaul.baum;

import java.util.ArrayList;

import org.bschlangaul.baum.visualisierung.BaumReporter;
import org.bschlangaul.baum.visualisierung.StummerBaumReporter;
import org.bschlangaul.liste.saake.FeldWarteschlange;
import org.bschlangaul.liste.saake.Warteschlange;
import org.bschlangaul.liste.saake.WarteschlangeFehler;

/**
 * Die abstrakte Oberklasse eines Binärbaums für die Klassen
 * {@link BinaererSuchBaum} und {@AVLBaum}.
 */
@SuppressWarnings("rawtypes")
public abstract class BinaerBaum {

  public BaumReporter reporter = new StummerBaumReporter();

  public static final String[] traversierungsNamen = { "INORDER", "PREORDER", "POSTORDER", "LEVELORDER" };

  /**
   * Saake Seite 357
   */
  public static final int INORDER = 0;
  public static final int PREORDER = 1;
  public static final int POSTORDER = 2;
  public static final int LEVELORDER = 3;

  /**
   * Der erste Knoten wird auf den rechten Arm gelegt. Der Kopf-Knoten selbst hat
   * keinen Wert.
   */
  BaumKnoten kopf;

  /**
   * Zuerst wird der linke Teilbaum l durchlaufen, dann die Wurzel N betrachtet
   * und schließlich der rechte Teilbaum r durchlaufen. Diese Reihenfolge
   * entspricht bei binären Suchbäumen der Anordnung der Schlüssel und ist für die
   * meisten Anwendungen die gegebene. (nach Saake Seite 356)
   *
   * @param knoten    Der aktuelle Baumknoten, der besucht werden soll.
   * @param schlüssel Eine Liste, die mit den besuchten Schlüsselwerten gefüllt
   *                  wird.
   */
  private void besucheInorder(BaumKnoten knoten, ArrayList<Comparable> schlüssel) {
    if (knoten != null) {
      besucheInorder(knoten.gibLinks(), schlüssel);
      schlüssel.add((Comparable) knoten.gibSchlüssel());
      besucheInorder(knoten.gibRechts(), schlüssel);
    }
  }

  /**
   * Zuerst wird die Wurzel N betrachtet und anschließend der linke l, schließlich
   * der rechte Teilbaum r durchlaufen. (nach Saake Seite 356)
   *
   * @param knoten    Der aktuelle Baumknoten, der besucht werden soll.
   * @param schlüssel Eine Liste, die mit den besuchten Schlüsselwerten gefüllt
   *                  wird.
   */
  private void besuchePreorder(BaumKnoten knoten, ArrayList<Comparable> schlüssel) {
    if (knoten != null) {
      schlüssel.add((Comparable) knoten.gibSchlüssel());
      besuchePreorder(knoten.gibLinks(), schlüssel);
      besuchePreorder(knoten.gibRechts(), schlüssel);
    }
  }

  /**
   * Zuerst wird der linke l, dann der rechte Teilbaum r durchlaufen und
   * schließlich die Wurzel N betrachtet. (nach Saake Seite 356)
   *
   * @param knoten    Der aktuelle Baumknoten, der besucht werden soll.
   * @param schlüssel Eine Liste, die mit den besuchten Schlüsselwerten gefüllt
   *                  wird.
   */
  private void besuchePostorder(BaumKnoten knoten, ArrayList<Comparable> schlüssel) {
    if (knoten != null) {
      besuchePostorder(knoten.gibLinks(), schlüssel);
      besuchePostorder(knoten.gibRechts(), schlüssel);
      schlüssel.add((Comparable) knoten.gibSchlüssel());
    }
  }

  /**
   * Beginnend bei der Baumwurzel werden die Ebenen von links nach rechts
   * durchlaufen. (nach Saake Seite 358)
   *
   * @param warteschlange Eine Warteschlange aus der die Baumknoten entnommen
   *                      werden.
   * @param schlüssel     Eine Liste, die mit den besuchten Schlüsselwerten
   *                      gefüllt wird.
   *
   * @throws WarteschlangeFehler
   */
  private void besucheLevelorder(Warteschlange warteschlange, ArrayList<Comparable> schlüssel)
      throws WarteschlangeFehler {
    while (!warteschlange.isEmpty()) {
      BaumKnoten knoten = (BaumKnoten) warteschlange.leave();
      if (knoten.gibLinks() != null)
        warteschlange.enter(knoten.gibLinks());
      if (knoten.gibRechts() != null)
        warteschlange.enter(knoten.gibRechts());
      schlüssel.add((Comparable) knoten.gibSchlüssel());
    }
  }

  /**
   * Besuche die Knoten des Baums in verschiedenen Traversierungsmethoden.
   *
   * <table>
   * <thead>
   * <tr>
   * <td>strategie</td>
   * <td>Bezeichnung</td>
   * </tr>
   * </thead> <tbody>
   * <tr>
   * <td>0</td>
   * <td>INORDER</td>
   * </tr>
   * <tr>
   * <td>1</td>
   * <td>PREORDER</td>
   * </tr>
   * <tr>
   * <td>2</td>
   * <td>POSTORDER</td>
   * </tr>
   * <tr>
   * <td>3</td>
   * <td>LEVELORDER</td>
   * </tr>
   * </tbody>
   * </table>
   *
   * @param strategie 0 (INORDER), 1 (PREORDER), 2 (POSTORDER), 3 (LEVELORDER)
   */
  public ArrayList<Comparable> traversiere(int strategie) {
    ArrayList<Comparable> schlüssel = new ArrayList<Comparable>();
    switch (strategie) {
      case INORDER:
        besucheInorder(gibKopf(), schlüssel);
        break;
      case PREORDER:
        besuchePreorder(gibKopf(), schlüssel);
        break;
      case POSTORDER:
        besuchePostorder(gibKopf(), schlüssel);
        break;
      case LEVELORDER:
        Warteschlange queue = new FeldWarteschlange();
        try {
          queue.enter(gibKopf());
          besucheLevelorder(queue, schlüssel);
        } catch (WarteschlangeFehler e) {
          e.printStackTrace();
        }
        break;
      default:
    }
    return schlüssel;
  }

  abstract public BaumKnoten gibKopf();

  abstract public boolean fügeEin(Comparable schlüssel);

  /**
   * Füge mehrere Schlüssele auf einmal ein.
   *
   * @param schlüssel Mehrere Schlüssel.
   *
   * @return Wahr, wenn das Einfügen erfolgreich war, d. h. alle Schlüssel
   *         eingefügt werden konnten. Konnte ein Schlüssel nicht eingefügt
   *         werden, wird falsch zurück gegeben.
   */
  public boolean fügeEin(Comparable... schlüssel) {
    boolean ergebnis = true;
    boolean tmp;
    for (Comparable s : schlüssel) {
      tmp = fügeEin(s);
      if (!tmp) {
        ergebnis = false;
      }
    }
    return ergebnis;
  }

  abstract public boolean entferne(Comparable schlüssel);

}
