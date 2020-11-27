package org.bschlangaul.vererbung.saake;

/**
 * Saake Seite 296
 */
abstract class GeomObjekt {
  int x;
  int y;

  GeomObjekt() {
    x = y = 0;
  }

  GeomObjekt(int xp, int yp) {
    x = xp;
    y = yp;
  }

  // Methode zum Verschieben
  public void verschieben(int dx, int dy) {
    x += dx;
    y += dy;
  }

  public abstract void zeichnen();
}
