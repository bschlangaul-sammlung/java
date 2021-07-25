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
   * @param name   Der Name des TeX-Makros {@code \makroname{}}.
   * @param option {@code \makroname[option]{}}
   * @param inhalt Der Inhalt des TeX-Makros umgeben von geschweiften Klammern
   *               ({@code \makroname{inhalt}}).
   *
   * @return Ein String der als regulärer Ausdruck eingesetzt werden kann.
   */
  public static String gibMakroRegex(String name, String option, String inhalt) {
    return gibMakroRegex(name + "(\\[" + option + "\\])?", inhalt);
  }

  /**
   * @param name   Der Name des TeX-Makros {@code \makroname{}}.
   * @param inhalt Der Reguläre Ausdruck des Inhalt des TeX-Makros umgeben von
   *               geschweiften Klammern.
   *
   * @return Ein String der als regulärer Ausdruck eingesetzt werden kann.
   */
  public static String gibMakroRegex(String name, String inhalt) {
    return "\\\\" + name + "\\{" + inhalt + "\\}";
  }

  /**
   * @param name Der Name der TeX-Umgebung
   *             {@code \begin{umgebungsname}...\end{umgebungsname}}.
   *
   * @return Ein String der als regulärer Ausdruck eingesetzt werden kann.
   */
  public static String gibUmgebungRegex(String name) {
    return gibMakroRegex("begin", name) + "(?<markup>.*?)" + gibMakroRegex("end", name);
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
   * @param text  Der Textinhalt, in dem mit Hilfe des regulären Ausdrucks nach
   *              Ausschnitten gesucht werden soll.
   * @param regex Ein regulärer Ausdruck der {@code (?<markup>...)} enthält.
   *
   * @return Eine Liste an gefundenen Markups.
   */
  public static List<String> suche(String text, String regex) {
    Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
    Matcher ergebnis = pattern.matcher(text);
    List<String> markups = new ArrayList<>();
    if (ergebnis.find()) {
      markups.add(ergebnis.group("markup"));
    }
    return markups;
  }

  /**
   * @param pfad  Der Dateipfad zur Text-Datei.
   *
   * @param regex Ein regulärer Ausdruck der {@code (?<markup>...)} enthält.
   *
   * @return Eine Liste an gefundenen Markups.
   */
  public static List<String> sucheInDatei(String pfad, String regex) {
    String inhalt = leseTextDatei(pfad);
    if (inhalt != null) {
      return suche(inhalt, regex);
    }
    return null;
  }

  /**
   * @param datei Eine Text-Datei (eine Instanz der Klasse {@link File}).
   *
   * @param regex Ein regulärer Ausdruck der {@code (?<markup>...)} enthält.
   *
   * @return Eine Liste an gefundenen Markups.
   */
  public static List<String> sucheInDatei(File datei, String regex) {
    String inhalt = leseTextDatei(datei);
    if (inhalt != null) {
      return suche(inhalt, regex);
    }
    return null;
  }

  /**
   * Suche nach mehreren TeX-Umgebungen mit dem Namen {@code name}.
   *
   * @param text Der Textinhalt, in dem mit Hilfe des regulären Ausdrucks nach
   *             Ausschnitten gesucht werden soll.
   * @param name Der Name der TeX-Umgebung.
   *
   * @return Eine Liste an gefundenen Inhalten der TeX-Umgebungen. Nur der Inhalt
   *         wird ausgegeben, nicht die umschließenden TeX-Makros.
   */
  public static List<String> sucheUmgebung(String text, String name) {
    return suche(text, gibUmgebungRegex(name));
  }

  /**
   * Suche nach mehreren TeX-Umgebungen mit dem Namen {@code name}.
   *
   * @param datei Eine Text-Datei (eine Instanz der Klasse {@link File}).

   * @param name Der Name der TeX-Umgebung.
   *
   * @return Eine Liste an gefundenen Inhalten der TeX-Umgebungen. Nur der Inhalt
   *         wird ausgegeben, nicht die umschließenden TeX-Makros.
   */
  public static List<String> sucheUmgebungInDatei(File datei, String name) {
    return sucheInDatei(datei, gibUmgebungRegex(name));
  }

}
