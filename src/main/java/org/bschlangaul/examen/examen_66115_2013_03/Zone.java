package org.bschlangaul.examen.examen_66115_2013_03;

import java.util.ArrayList;

class Zone {

  private ArrayList<ArrayList<String>> urlList = new ArrayList<ArrayList<String>>();

  private ArrayList<ArrayList<String>> ipList = new ArrayList<ArrayList<String>>();

  /**
   * Der Konstruktor initialisert die Streutabellen mit 46 Buckets, damit wir den
   * Code testen können.
   */
  public Zone() {
    for (int i = 0; i <= 45; i++) {
      urlList.add(new ArrayList<String>());
      ipList.add(new ArrayList<String>());
    }
  }

  /**
   * Diese Methode wird zum Testen der Methode {@link getIndex} gebraucht.
   *
   * @param hash Der Hashwert, für den eine Kollisionsliste gefunden werden soll.
   *
   * @return Eine Kollisionsliste bestehend aus Strings.
   */
  public ArrayList<String> getUrlCollisionList(int hash) {
    return urlList.get(hash);
  }

  /**
   * Nicht wirklich eine Hashfunktion. Gibt die Werte wie im Schaubild zurück.
   * Diese Methode muss nicht implementiert werden. Sie ist nur dazu da, um die
   * Code testen zu können.
   *
   * @param url Eine URL.
   *
   * @return Ein Hashwert, der größer gleich 0 ist.
   */
  public int hash(String url) {
    /* calculates hash-value h, >=0 */
    switch (url) {
      case "google.de":
        return 0;

      case "bayern.de":
        return 1;

      case "facebook.com":
      case "gmx.net":
        return 45;

      default:
        return 42;
    }
  }

  /**
   * Prüfe, ob bereits mindestens ein Eintrag für einen gegebenen Streuwert
   * vorhanden ist. Falls h größer ist als die derzeitige Größe der Streutabelle,
   * existiert der Eintrag nicht.
   *
   * @param h Der Index-Wert, der durch die Hashfunktion erzeugt wird.
   *
   * @return Wahr, wenn in beiden Streutabellen an einer bestimmen Index-Position
   *         mindestes ein Wert hinterlegt ist, sonst falsch.
   */
  boolean exists(int h) {
    if (urlList.size() - 1 < h || ipList.size() - 1 < h)
      return false;

    ArrayList<String> urlCollisionList = urlList.get(h);
    ArrayList<String> ipCollisionList = ipList.get(h);
    if (urlCollisionList.size() == 0 || ipCollisionList.size() == 0)
      return false;

    return true;
  }

  /**
   * Berechne den Index einer URL in der Kollisionsliste. Ist die URL in der
   * Kollisionsliste nicht vorhanden, soll -1 zurückgeliefert werden.
   *
   * @param url     Die URL, für die der Index gesucht werden soll.
   * @param urlList Die URL-Kollisionsliste, in der gesucht werden soll.
   *
   * @return Die Index-Nummer der gefundenen URL oder -1, wenn die URL nicht
   *         gefunden wurde.
   */
  int getIndex(String url, ArrayList<String> urlList) {
    for (int i = 0; i < urlList.size(); i++) {
      if (urlList.get(i).equals(url))
        return i;
    }
    return -1;
  }

  /**
   * Gib in der Streutabelle die IP-Adresse zurück. Wird eine nicht vorhandene
   * Adresse abgerufen, wird eine Fehlermeldung zurückgegeben.
   *
   * @param url Die gesuchte URL.
   *
   * @return Die entsprechende IP-Adresse.
   */
  String lookup(String url) {
    int h = hash(url);
    int collisionIndex = getIndex(url, urlList.get(h));
    if (collisionIndex == -1)
      return "Die URL kannte nicht in der Tabelle gefunden werden";
    return ipList.get(h).get(collisionIndex);
  }

  /**
   * Nicht verlangt. Zum Einfügen von Testwerten gedacht.
   *
   * @param url Eine URL.
   * @param ip  Eine IP-Adresse.
   */
  public void addUrl(String url, String ip) {
    int h = hash(url);
    urlList.get(h).add(url);
    ipList.get(h).add(ip);
  }

  public static void main(String[] args) {
    Zone zone = new Zone();
    zone.addUrl("google.de", "173.194.69.9");
    zone.addUrl("bayern.de", "195.200.71.1");
    zone.addUrl("facebook.com", "69.171.229.1");
    zone.addUrl("gmx.net", "213.165.64.7");
  }
}
