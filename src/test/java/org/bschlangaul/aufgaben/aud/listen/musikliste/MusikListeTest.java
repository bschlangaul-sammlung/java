package org.bschlangaul.aufgaben.aud.listen.musikliste;

import static org.junit.Assert.*;
import org.junit.Test;

public class MusikListeTest {

  private MusikListe macheListe() {
    MusikListe liste = new MusikListe();

    MusikStueck stueck1 = new MusikStueck("Hangover");
    MusikStueck stueck2 = new MusikStueck("Roar");
    MusikStueck stueck3 = new MusikStueck("On the Floor");
    MusikStueck stueck4 = new MusikStueck("Whistle");

    Knoten platz1 = new Knoten(stueck1);
    Knoten platz2 = new Knoten(stueck2);
    Knoten platz3 = new Knoten(stueck3);
    Knoten platz4 = new Knoten(stueck4);

    liste.setzeErsten(platz1);
    platz1.setzeNächsten(platz2);
    platz2.setzenVorherigen(platz1);
    platz2.setzeNächsten(platz3);
    platz3.setzenVorherigen(platz2);
    platz3.setzeNächsten(platz4);
    platz4.setzenVorherigen(platz3);
    liste.aktualisiereAnzahl();
    return liste;
  }

  @Test
  public void methodeGibMusikstückListe() {
    MusikListe liste = macheListe();
    assertEquals("Hangover | Roar | On the Floor | Whistle", liste.gibMusikstückListe());
  }

  @Test
  public void methodeEntnimmOben() {
    MusikListe liste = macheListe();
    assertEquals("Hangover", liste.entnimmOben().gibMusikstück().gibTitel());
    assertEquals("Roar | On the Floor | Whistle", liste.gibMusikstückListe());
  }

  @Test
  public void methodeZähleEinträge() {
    MusikListe liste = macheListe();
    assertEquals(4, liste.zähleEinträge());
  }
}
