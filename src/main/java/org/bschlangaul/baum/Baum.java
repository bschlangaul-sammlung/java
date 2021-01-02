package org.bschlangaul.baum;

import java.util.ArrayList;

import org.bschlangaul.baum.visualisierung.BaumReporter;
import org.bschlangaul.baum.visualisierung.StummerBaumReporter;
import org.bschlangaul.liste.saake.FeldWarteschlange;
import org.bschlangaul.liste.saake.Warteschlange;
import org.bschlangaul.liste.saake.WarteschlangeFehler;

@SuppressWarnings("rawtypes")
public abstract class Baum {

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
   * Saake Seite 356
   */
  private void besucheInorder(BaumKnoten knoten, ArrayList<Comparable> schlüssel) {
    if (knoten != null) {
      besucheInorder(knoten.gibLinks(), schlüssel);
      schlüssel.add((Comparable) knoten.gibSchlüssel());
      besucheInorder(knoten.gibRechts(), schlüssel);
    }
  }

  /**
   * Saake Seite 356
   */
  private void besuchePreorder(BaumKnoten knoten, ArrayList<Comparable> schlüssel) {
    if (knoten != null) {
      schlüssel.add((Comparable) knoten.gibSchlüssel());
      besuchePreorder(knoten.gibLinks(), schlüssel);
      besuchePreorder(knoten.gibRechts(), schlüssel);
    }
  }

  /**
   * Saake Seite 356
   */
  private void besuchePostorder(BaumKnoten knoten, ArrayList<Comparable> schlüssel) {
    if (knoten != null) {
      besuchePostorder(knoten.gibLinks(), schlüssel);
      besuchePostorder(knoten.gibRechts(), schlüssel);
      schlüssel.add((Comparable) knoten.gibSchlüssel());
    }
  }

  /**
   * Saake Seite 358
   *
   * @param q
   * @throws WarteschlangeFehler
   */
  private void printLevelorder(Warteschlange q, ArrayList<Comparable> schlüssel) throws WarteschlangeFehler {
    while (!q.isEmpty()) {
      BaumKnoten knoten = (BaumKnoten) q.leave();
      if (knoten.gibLinks() != null)
        q.enter(knoten.gibLinks());
      if (knoten.gibRechts() != null)
        q.enter(knoten.gibRechts());
      schlüssel.add((Comparable) knoten.gibSchlüssel());
    }
  }

  /**
   *
   * @param strategie 0 (INORDER), 1 (PREORDER), 2 (POSTORDER), 3 (LEVELORDER)
   * @throws WarteschlangeFehler
   */
  public ArrayList<Comparable> traversiere(int strategie) {
    ArrayList<Comparable> schlüssel = new ArrayList<Comparable>();
    switch (strategie) {
      case INORDER:
        besucheInorder(kopf.gibRechts(), schlüssel);
        break;
      case PREORDER:
        besuchePreorder(kopf.gibRechts(), schlüssel);
        break;
      case POSTORDER:
        besuchePostorder(kopf.gibRechts(), schlüssel);
        break;
      case LEVELORDER:
        Warteschlange queue = new FeldWarteschlange();
        try {
          queue.enter(kopf.gibRechts());
          printLevelorder(queue, schlüssel);
        } catch (WarteschlangeFehler e) {
          e.printStackTrace();
        }
        break;
      default:
    }
    return schlüssel;
  }

  abstract public BaumKnoten gibKopf();

  abstract public boolean fügeEin(Comparable wert);

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
}
