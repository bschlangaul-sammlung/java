package org.bschlangaul.entwurfsmuster.beobachter.hauer;

public class BeispielKlient {

  public static void main(String[] args) {
    FAZVerlag verlag = new FAZVerlag();
    verlag.aboHinzufuegen(new FamilieFischer());
    verlag.aboHinzufuegen(new FamilieMeier());
    FirmaXY firma = new FirmaXY();
    verlag.aboHinzufuegen(firma);

    verlag.setAktuelleZeitung(new Zeitung("Skandal!"));
    // Familie Fischer erhielt die aktuelle Zeitung: Skandal!
    // Familie Meier erhielt die aktuelle Zeitung: Skandal!
    // Firma XY erhielt die aktuelle Zeitung: Skandal!

    verlag.aboEntfernen(firma);
    verlag.setAktuelleZeitung(new Zeitung("Doch alles halb so wild!"));
    // Familie Fischer erhielt die aktuelle Zeitung: Doch alles halb so wild!
    // Familie Meier erhielt die aktuelle Zeitung: Doch alles halb so wild!
  }
}
