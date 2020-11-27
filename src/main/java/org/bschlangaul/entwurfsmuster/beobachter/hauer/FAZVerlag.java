package org.bschlangaul.entwurfsmuster.beobachter.hauer;

public class FAZVerlag extends Verlag {

  private Zeitung aktuelleZeitung;

  public void setAktuelleZeitung(Zeitung aktuelleZeitung) {
    this.aktuelleZeitung = aktuelleZeitung;
    // Nach dem einen neue Zeitung gesetzt wurde, werden alle Abonnenten
    // benachrichtigt.
    verteileZeitung(aktuelleZeitung);
  }

  public Zeitung getAktuelleZeitung() {
    return aktuelleZeitung;
  }
}
