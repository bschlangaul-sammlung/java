/**
 * Philosophenproblem
 *
 * <dl>
 * <dt><b>Aufgabe: Verklemmung</b>
 *
 * <dd><p>Importiere die Klassen aus der .zip-Datei Verklemmung in deine
 * Entwicklungsumgebung und verschaffe dir einen Überblick über die
 * Klasse Verklemmung, Gabel, Philosoph und PhilosophDeadlock und
 * teste die Verklemmung des Programms.
 *
 * <dt><b>Aufgabe: Gegenmaßnahme Belegung auf einen Schlag</b>
 *
 * <dd><p>Die notwendige Bedingung der iterativen Anforderung wird
 * ausgehebelt, indem man die beiden Gabeln „gleichzeitig“ (d.h. für
 * andere Threads nicht unterbrechbar) anfordert. (synchronized)
 *
 * <p>Nutze anschließend die Gegenmaßnahme (Belegung auf einen
 * Schlag), sodass sich die Klasse PhilosophGleichzeitigeBelegung
 * nicht mehr verklemmt:
 *
 * <ul>
 * <li>Nutze einen möglichst kleinen synchronized-Block
 * <li>Überlege dir, welche Objekte sich zum Synchronisieren eigenen. Hier gibt es
 * mehrere Möglichkeiten.
 * </ul>
 *
 * <dt><b>Aufgabe: Globale Ordnung</b>
 *
 * <dd><p>Gabeln (= Marken) sind durchnummeriert.
 * Philosophen greifen immer erst den Löffel
 * mit der kleineren Nummer (= Erwerben
 * entsprechend der globalen Ordnung).
 *
 * <p>Bis auf den Philosophen X greifen alle erst nach
 * dem rechten, dann nach dem linken Löffel. Da
 * X in der problematischen Situation den Löffel 1
 * nicht erhält, wird mindestens 1 Philosoph mit
 * dem Essen beginnen können.
 *
 * PhilosophGlobaleOrdnung, sodass sich
 * diese Variante nicht mehr verklemmt.
 * </dl>
 */
package org.bschlangaul.aufgaben.tech_info.philosoph;
