package org.bschlangaul.entwurfsmuster.zustand.hauer;

/**
 * Besonders schön lässt sich unser Entwurf nun durch den Client
 * verwenden. Der Client hat keine Kenntnis von den Zuständen und
 * Zustandswechsel der Freundin. Er kennt allein das Freundinobjekt und
 * interagiert ausschließlich mit diesem. Dabei wechselt die Freundin
 * zur Laufzeit dynamisch ihr Verhalten, ohne dass der Client davon
 * etwas mitbekommt oder gar etwas dazu beiträgt. Er scheint so, als
 * hätte die Freundin ihre Klasse geändert.
 */
public class Klient {
  public static void main(String[] args) {
    Freundin freundin = new Freundin();
    // Defaultzustand: Neutral
    freundin.unterhalten(); // Hallo!
    freundin.verärgern(); // Du spinnst wohl! Ich bin sauer! ;-(
    // Ab jetzt: Bockig
    freundin.unterhalten(); // Fahr jetzt nach Hause! Ich will nicht mit dir reden!
    freundin.unterhalten(); // Fahr jetzt nach Hause! Ich will nicht mit dir reden!
    freundin.kussGeben(); // Na gut! Hab dich wieder lieb.
    // Ab jetzt: Neutral
    freundin.kussGeben(); // Hihi :-)
    // Ab jetzt: Fröhlich
    freundin.unterhalten(); // Hihi, mir gehts super!
  }
}
