package org.bschlangaul.aufgaben.aud.pu_7.cd_regal;

@SuppressWarnings("unused")
public class Disk {
  private Typ typ;
  private Genre genre;
  private int bewertung;
  private String titel;

  /**
   * * erzeugt eine neue Disk Bewertung wird automatisch mit Hilfe des Genres
   * berechnet
   *
   * @param typ   Typ der Disk
   * @param genre Genre der Disk
   * @param titel Titel der Disk
   */
  public Disk(Typ typ, Genre genre, String titel) {
    this.typ = typ;
    this.genre = genre;
    this.titel = titel;
    this.bewertung = (int) erstelleStdBewertung(this.genre);
  }

  /**
   * * gibt die Bewertung der Disk zurück
   *
   * @return Bewertung
   */
  public int getBewertung() {
    return this.bewertung;
  }

  /**
   * * berechnet rekursiv die Bewertung einer Disk * @param genre Genre der Disk
   *
   * @return Bewertung
   */
  public double erstelleStdBewertung(Genre genre) {
    if (genre.equals(Genre.MUSIK)) {
      return 3;
    } else if (genre.equals(Genre.KOMOEDIE)) {
      return 2;
    } else if (genre.equals(Genre.THRILLER)) {
      return 2 * erstelleStdBewertung(Genre.KOMOEDIE);
    } else if (genre.equals(Genre.FANTASY)) {
      return 1.5 * erstelleStdBewertung(Genre.THRILLER);
    } else if (genre.equals(Genre.ACTION)) {
      return erstelleStdBewertung(Genre.THRILLER);
    } else {
      return 0;
    }
  }

}
