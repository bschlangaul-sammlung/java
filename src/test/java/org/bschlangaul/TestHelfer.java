package org.bschlangaul;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestHelfer {

  public static String leseDatei(String relativerPfad) throws IOException {
    ClassLoader classLoader = TestHelfer.class.getClassLoader();
    String url = classLoader.getResource(relativerPfad).getPath();
    String inhalt = new String(Files.readAllBytes(Paths.get(url)), StandardCharsets.UTF_8);
    System.out.println(inhalt);
    return inhalt;
  }
}
