package org.bschlangaul.baum.visualisierung;

import org.bschlangaul.baum.BinaerBaum;

public abstract class BaumReporter {

  protected BinaerBaum baum;

  public static int redseligkeit = 0;

  public abstract String erzeugeBaum(BinaerBaum baum);

  private void gibAus(String ausgabe) {
    if (ausgabe != null)
      System.out.println(ausgabe);
  }

  public void berichteBaum(BinaerBaum baum) {
    berichteBaum(baum, 0);
  }

  public void berichteBaum(BinaerBaum baum, int redselig) {
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

  public abstract String erzeugeTraversierung(BinaerBaum baum);

  public void berichteTraversierung(BinaerBaum baum) {
    gibAus(erzeugeTraversierung(baum));
  }

  public void berichteBaum(BinaerBaum baum, String überschrift, int redselig) {
    berichteBaum(baum, redselig);
    berichteÜberschrift(überschrift, redselig);
  }

  public void berichteBaum(BinaerBaum baum, String überschrift) {
    berichteBaum(baum);
    berichteÜberschrift(überschrift);
  }

  public void berichteBaum(String überschrift, BinaerBaum baum) {
    berichteÜberschrift(überschrift);
    berichteBaum(baum);
  }

  public void berichteBaum(String überschrift, BinaerBaum baum, int redselig) {
    berichteÜberschrift(überschrift, redselig);
    berichteBaum(baum, redselig);
  }

}
