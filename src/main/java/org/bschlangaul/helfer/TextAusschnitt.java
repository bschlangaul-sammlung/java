package org.bschlangaul.helfer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Findet in einer Text-Datei mit Hilfe eines regulären Ausdrucks
 * Textausschnitte, die weiterverarbeitet werden können.
 */
public class TextAusschnitt {

  /**
   *
   * @param makroname Der Name des TeX-Makros {@code \makroname{}}.
   * @param option    {@code \makroname[option]{}}
   * @param inhalt    Der Inhalt des TeX-Makros umgeben von geschweiften Klammern
   *                  ({@code \makroname{inhalt}}).
   *
   * @return Ein String der als regulärer Ausdruck eingesetzt werden kann.
   */
  public static String gibTexMakroRegex(String makroname, String option, String inhalt) {
    return gibTexMakroRegex(makroname + "(\\[" + option + "\\])?", inhalt);
  }

  /**
   * @param makroname Der Name des TeX-Makros {@code \makroname{}}.
   * @param inhalt    Der Inhalt des TeX-Makros umgeben von geschweiften Klammern.
   *
   * @return Ein String der als regulärer Ausdruck eingesetzt werden kann.
   */
  public static String gibTexMakroRegex(String makroname, String inhalt) {
    return "\\\\" + makroname + "\\{" + inhalt + "\\}";
  }

  /**
   * @param umgebungsname Der Name der TeX-Umgebung
   *                      {@code \begin{umgebungsname}...\end{umgebungsname}}.
   *
   * @return Ein String der als regulärer Ausdruck eingesetzt werden kann.
   */
  public static String gibTexUmgebungRegex(String umgebungsname) {
    return gibTexMakroRegex("begin", umgebungsname) + "(?<markup>.*?)" + gibTexMakroRegex("end", umgebungsname);
  }

  /**
   * Lese den Inhalt einer Text-Datei ein.
   *
   * @param datei Eine Text-Datei.
   *
   * @return Der Inhalt der Text-Datei.
   */
  private static String leseTextDatei(File datei) {
    try {
      return Files.readString(datei.toPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Lese den Inhalt einer Text-Datei ein.
   *
   * @param pfad Der Dateipfad zur Text-Datei.
   *
   * @return Der Inhalt der Text-Datei.
   */
  private static String leseTextDatei(String pfad) {
    File datei = new File(pfad);
    return leseTextDatei(datei);
  }

  /**
   * @param inhalt Der Textinhalt in dem mit Hilfe des regulären Ausdrucks nach
   *               Ausschnitten gesucht werden soll.
   * @param regex  Ein regulärer Ausdruck der {@code (?<markup>...)} enthält.
   *
   * @return Eine Liste an gefunden Markups
   */
  public static List<String> sucheInText(String inhalt, String regex) {
    Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(inhalt);
    List<String> markups = new ArrayList<>();
    if (ergebnis.find()) {
      markups.add(ergebnis.group("markup"));
    }
    return markups;
  }

  /**
   * @param pfad  Der Dateipfad zur Text-Datei.
   *
   * @param regex  Ein regulärer Ausdruck der {@code (?<markup>...)} enthält.
   *
   * @return Eine Liste an gefunden Markups
   */
  public static List<String> sucheInDatei(String pfad, String regex) {
    String inhalt = leseTextDatei(pfad);
    if (inhalt != null) {
      return sucheInText(inhalt, regex);
    }
    return null;
  }

  /**
   * @param datei Eine Text-Datei.
   *
   * @param regex  Ein regulärer Ausdruck der {@code (?<markup>...)} enthält.
   *
   * @return Eine Liste an gefunden Markups
   */
  public static List<String> sucheAusschnitteInTextDatei(File datei, String regex) {
    String inhalt = leseTextDatei(datei);
    if (inhalt != null) {
      return sucheInText(inhalt, regex);
    }
    return null;
  }
}
