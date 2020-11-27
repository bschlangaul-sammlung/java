package org.bschlangaul.vererbung.saake;

import java.io.OutputStream;
import java.io.InputStream;

/**
 * Saake Seite 302
 */
interface Speicherbar {
  void speichern(OutputStream out);

  void laden(InputStream in);
}
