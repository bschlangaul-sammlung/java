package org.bschlangaul;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestHelfer {

  public static String leseDatei(String relativerPfad) {

    ClassLoader classLoader = TestHelfer.class.getClassLoader();
    String url = classLoader.getResource(relativerPfad).getPath();
    String inhalt = "";
    try {
      inhalt = new String(Files.readAllBytes(Paths.get(url)), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return inhalt;
  }
}
