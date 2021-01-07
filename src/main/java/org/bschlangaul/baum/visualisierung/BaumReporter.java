package org.bschlangaul.baum.visualisierung;

import java.util.ArrayList;

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

  @SuppressWarnings("rawtypes")
  private String vereinigeListe(String zeichenKetten, ArrayList<Comparable> liste) {
    String ausgabe = "";
    if (liste.size() == 0)
      return null;
    if (liste.size() == 1)
      return liste.get(0).toString();
    int i = 0;
    for (Comparable element : liste) {
      ausgabe += element;
      if (i < liste.size() - 1)
        ausgabe += zeichenKetten;
      i++;
    }
    return ausgabe;
  }

  @SuppressWarnings("rawtypes")
  public String[][] sammleTraversierungsDaten(BinaerBaum baum) {
    String[][] ausgabe = new String[BinaerBaum.traversierungsNamen.length][2];
    for (int i = 0; i < BinaerBaum.traversierungsNamen.length; i++) {
      ausgabe[i][0] = BinaerBaum.traversierungsNamen[i];
      ArrayList<Comparable> schlüssel = baum.traversiere(i);
      ausgabe[i][1] = vereinigeListe(", ", schlüssel);
    }
    return ausgabe;
  }

  public abstract String erzeugeTraversierung(BinaerBaum baum);

  public void berichteTraversierung(BinaerBaum baum) {
    if (baum.gibKopf() == null)
      return;
    gibAus(erzeugeÜberschrift("Traversierung") + "\n" + erzeugeTraversierung(baum));
  }

  public abstract String erzeugeTabelle(String[] kopfZeile, String[][] zeilen);

  public void berichteTabelle(String[] kopfZeile, String[][] zeilen) {
    gibAus(erzeugeTabelle(kopfZeile, zeilen));
  }

}
