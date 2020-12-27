package org.bschlangaul.examen.examen_66115_2013_03;

import java.util.ArrayList;

class Zone {
  private ArrayList<ArrayList<String>> urlList = new ArrayList<ArrayList<String>>();
  private ArrayList<ArrayList<String>> ipList = new ArrayList<ArrayList<String>>();

  public int hash(String url) {
    return 1;
    /* calculates hash-value h, >=0 */

  }

  /**
   * Prüfe, ob bereits mindestens ein Eintrag für einen gegebenen Streuwert
   * vorhanden ist. Falls h größer ist als die derzeitige Größe der Streutabelle,
   * existiert der Eintrag nicht.
   *
   * @param h
   * @return
   */
  boolean exists(int h) {
    return true;
  }

  /**
   * Berechne den Index einer URL in der Kollisionsliste. Ist die URL in der
   * Kollisionsliste nicht vorhanden, soll -1 zurückgeliefert werden.
   */
  int getIndex(String url, ArrayList<String> urlList) {
    return 0;

  }

  /**
   * Gib in der Streutabelle die IP-Adresse zurück. Wird eine nicht vorhandene
   * Adresse abgerufen, wird eine Fehlermeldung zurückgegeben.
   *
   * @param url
   * @return
   */
  String lookup(String url) {
    return "lol";
  }

}
