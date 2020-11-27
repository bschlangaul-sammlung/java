package org.bschlangaul.baum;

import java.util.ArrayList;

import org.bschlangaul.liste.saake.FeldWarteschlange;
import org.bschlangaul.liste.saake.Warteschlange;
import org.bschlangaul.liste.saake.WarteschlangeFehler;

@SuppressWarnings("rawtypes")
public abstract class Baum {

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
  Knoten kopf;

  Knoten nullKnoten;

  /**
   * Saake Seite 356
   */
  private void besucheInorder(Knoten knoten, ArrayList<Comparable> schlüssel) {
    if (knoten != nullKnoten) {
      besucheInorder(knoten.gibLinks(), schlüssel);
      schlüssel.add((Comparable) knoten.gibSchlüssel());
      besucheInorder(knoten.gibRechts(), schlüssel);
    }
  }

  /**
   * Saake Seite 356
   */
  private void besuchePreorder(Knoten knoten, ArrayList<Comparable> schlüssel) {
    if (knoten != nullKnoten) {
      schlüssel.add((Comparable) knoten.gibSchlüssel());
      besuchePreorder(knoten.gibLinks(), schlüssel);
      besuchePreorder(knoten.gibRechts(), schlüssel);
    }
  }

  /**
   * Saake Seite 356
   */
  private void besuchePostorder(Knoten knoten, ArrayList<Comparable> schlüssel) {
    if (knoten != nullKnoten) {
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
      Knoten knoten = (Knoten) q.leave();
      if (knoten.gibLinks() != nullKnoten)
        q.enter(knoten.gibLinks());
      if (knoten.gibRechts() != nullKnoten)
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

  abstract public Knoten gibKopf();

  abstract public boolean fügeEin(Comparable wert);

  /**
   * Füge mehrere Schlüssele auf einmal ein.
   *
   * @param Schlüssel Mehrere Schlüssel.
   *
   * @return Wahr, wenn das Einfügen erfolgreich war, d. h. alle Schlüssel
   *         eingefügt werden konnten. Konnte ein Schlüssel nicht eingefügt
   *         werden, wird falsch zurück gegeben.
   */
  public boolean fügeEin(Comparable... Schlüssel) {
    boolean ergebnis = true;
    boolean tmp;
    for (Comparable s : Schlüssel) {
      tmp = fügeEin(s);
      if (!tmp) {
        ergebnis = false;
      }
    }
    return ergebnis;
  }
}
