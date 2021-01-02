package org.bschlangaul.baum.visualisierung;

import org.bschlangaul.baum.Baum;

public abstract class BaumReporter {

  public static int redseligkeit = 1;

  public abstract void visualisiereBaum(Baum baum);

  public abstract void visualisiereÜberschrift(String überschrift);

  public void visualisiereBaum(Baum baum, String überschrift) {
    visualisiereBaum(baum);
    visualisiereÜberschrift(überschrift);

  }

  public void visualisiereBaum(String überschrift, Baum baum) {
    visualisiereÜberschrift(überschrift);
    visualisiereBaum(baum);
  }

  public void visualisiereBaum(Baum baum, String überschrift, int redselig) {
    if (redseligkeit >= redselig) {
      visualisiereÜberschrift(überschrift);
      visualisiereBaum(baum);
    }
  }
}
