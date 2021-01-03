package org.bschlangaul.baum.visualisierung;

import org.bschlangaul.baum.Baum;

public abstract class BaumReporter {

  protected Baum baum;

  public static int redseligkeit = 0;

  public abstract String erzeugeBaum(Baum baum);

  private void gibAus(String ausgabe) {
    if (ausgabe != null)
      System.out.println(ausgabe);
  }

  public void berichteBaum(Baum baum) {
    berichteBaum(baum, 0);
  }

  public void berichteBaum(Baum baum, int redselig) {
    if (redselig <= redseligkeit)
      gibAus(erzeugeBaum(baum));
  }

  public abstract String erzeugeÜberschrift(String überschrift);

  public void berichteÜberschrift(String überschrift) {
    berichteÜberschrift(überschrift, 0);
  }

  public void berichteÜberschrift(String überschrift, int redselig) {
    if (redselig <= redseligkeit)
      gibAus(erzeugeÜberschrift(überschrift));
  }

  public abstract String erzeugeTraversierung(Baum baum);

  public void berichteTraversierung(Baum baum) {
    gibAus(erzeugeTraversierung(baum));
  }

  public void berichteBaum(Baum baum, String überschrift, int redselig) {
    berichteBaum(baum, redselig);
    berichteÜberschrift(überschrift, redselig);
  }

  public void berichteBaum(Baum baum, String überschrift) {
    berichteBaum(baum);
    berichteÜberschrift(überschrift);
  }

  public void berichteBaum(String überschrift, Baum baum) {
    berichteÜberschrift(überschrift);
    berichteBaum(baum);
  }

  public void berichteBaum(String überschrift, Baum baum, int redselig) {
    berichteÜberschrift(überschrift, redselig);
    berichteBaum(baum, redselig);
  }

}
