package org.bschlangaul;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestHelfer {

  /**
   * Erzeuge zu einem relativen Pfad relativ zu src/test/resources eine Instanz
   * der Klasse {@link Path}.
   *
   * @param relativerPfad Ein relativen Pfad relativ zu src/test/resources.
   *
   * @return Eine Instanz der Klasse {@link Path}.
   */
  public static Path erzeugePfad(String relativerPfad) {
    ClassLoader classLoader = TestHelfer.class.getClassLoader();
    String url = classLoader.getResource(relativerPfad).getPath();
    return Paths.get(url);
  }

  /**
   * Lese den Inhalt einer Textdatei ein.
   *
   * @param relativerPfad Ein relativen Pfad relativ zu src/test/resources.
   *
   * @return Der Inhalt der Textdatei im UTF-8-Format.
   */
  public static String leseDatei(String relativerPfad) {
    try {
      return new String(Files.readAllBytes(erzeugePfad(relativerPfad)), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
