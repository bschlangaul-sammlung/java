package org.bschlangaul.aufgaben.tech_info.vorlesung;

import java.util.concurrent.*;

/**
 * Dieses Programm soll die Summe der Summen einer Zahl berechnen Beispiel f(3)
 * = sum(3) + sum(2) + sum(1) = 10 wobei sum(3) = 1 + 2 + 3 ist
 */
public class BeispielprogrammMitExecutorUndCallable {

  public static void main(String[] args) {
    int n = 3;
    long ergebnis = 0;

    ExecutorService e = Executors.newFixedThreadPool(4);
    @SuppressWarnings("unchecked")
    // Für jedes Teilergebnis wird eine Future-Referenz erzeugt.
    // Initialisiert werden die Future-Referenzen durch das Rückgabeobjekt von
    // submit() (siehe unten)
    Future<Long>[] f = new Future[n];

    for (int i = 1; i <= n; i++) {

      // Mit submit() werden die Callable-Objekt dem ExecutorService
      // übergeben. Der Rückgabetyp von submit() ist ein Future-Objekt
      // mit dem entsprechenden generischen Datentyp von Callable. Diese
      // Referenz auf das Rückgabeobjekt wird im Array (das wir hier in
      // der main angelegt hatten) gespeichert. Die i-1 kommt daher, dass
      // die Schleife von 1 bis n geht (und nicht von 0 bis n-1 wie die
      // Indizies des Arrays).
      f[i - 1] = e.submit(new MeinCallable(i));
    }

    // Es kommen keine weiteren Arbeitspakete, aber alle übergebenen Arbeitspakete
    // werden fertig berechnet.
    e.shutdown();

    for (int i = 0; i < n; i++) {
      try {
        ergebnis += f[i].get();
        // Die Ergebnisse werden aus den Future-Objekten ausgelesen (sofern fertig
        // berechnet) und in ergebnis aufaddiert.
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      } catch (ExecutionException executionException) {
        executionException.printStackTrace();
      }
    }

    // e.awaitTermination(60,TimeUnit.SECONDS);
    // nicht notwendig, weil die Methode Future.get() ja schon blockiert, bis fertig
    // gerechnet wurde
    System.out.println(ergebnis);
  }

}

class MeinCallable implements Callable<Long> {

  /**
   * Als generischer Datentyp kann auch Integer, String oder die Klasse dastehen,
   * die zurückgegeben wird. Long wird groß geschrieben, weil hier die Klasse Long
   * zu dem primitiven Datentyp long benutzt wird.
   */
  long zahl;

  public MeinCallable(long zahl) {
    this.zahl = zahl;
  }

  public Long call() throws Exception {
    for (long i = zahl - 1; i > 0; i--) {
      zahl += i;
    }
    return zahl;
  }
}
