package org.bschlangaul.baum.visualisierung;

import org.bschlangaul.baum.Baum;

public abstract class BaumReporter {

  public static int redseligkeit = 1;

  public abstract String erzeugeBaum(Baum baum);

  public void berichteBaum(Baum baum) {
    System.out.println(erzeugeBaum(baum));
  }

  public abstract String erzeugeÜberschrift(String überschrift);

  public void berichteÜberschrift(String überschrift) {
    System.out.println(erzeugeÜberschrift(überschrift));
  }

  public abstract String erzeugeTraversierung(Baum baum);

  public void berichteTraversierung(Baum baum) {
    System.out.println(erzeugeTraversierung(baum));
  }

  public void berichteBaum(Baum baum, String überschrift) {
    berichteBaum(baum);
    berichteÜberschrift(überschrift);
  }

  public void berichteBaum(String überschrift, Baum baum) {
    berichteÜberschrift(überschrift);
    berichteBaum(baum);
  }

  public void berichteBaum(Baum baum, String überschrift, int redselig) {
    if (redseligkeit >= redselig) {
      berichteBaum(baum);
      berichteÜberschrift(überschrift);
    }
  }
}
