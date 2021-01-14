/**
 * <h2>Monte-Carlo-Integration</h2>
 *
 * Info_2020-12-04-2020-12-04_13.04.27.mp4 39-43min
 *
 * <dl>
 * <dt><b>Aufgabenstellung</b>
 *
 * <dd>
 * <p>
 * Implementiere in der Klasse {@link MonteCarloImpl} das Interface
 * {@link MonteCarlo}. Die Methode {@code computeIntegral} soll für eine
 * übergebene Funktion (Klasse Function) das Integral im angegebenen Intervall
 * [a, b] mittels Monte-Carlo-Integration (analog zu der Beschreibung in der
 * Vorlesung) parallel berechnen, indem zufällige Punkte ausgewählt und mit dem
 * Funktionswert verglichen werden. Die Methode bekommt die Anzahl der zu
 * verwendenden Threads und die Anzahl der zu betrachtenden Zufallspunkte
 * (iterations) übergeben. Gehe davon aus, dass sich die Funktionswerte in dem
 * gegebenen Intervall immer innerhalb der Grenzen [minY, maxY] bewegen.
 *
 * <dt><b>Hinweise</b>
 *
 * <dd>
 * <p>
 * Die Zahl iterations gibt die Gesamtanzahl an Punkten an, die zufällig gesetzt
 * werden sollen. Das heißt, dass diese Gesamtanzahl an Punkten auf die Threads
 * aufgeteilt werden soll.
 *
 * <dd>
 * <p>
 * Zum Zählweise der Punkte die „getroffen“ haben muss thread- sicher
 * implementiert werden.
 *
 * <dd>
 * <p>
 * Die Zufälligkeit muss mithilfe der Klasse Random von Java
 * ({@code java.util.Random}) erzeugt werden ({@code nextDouble()}) Hier darf
 * {@code Math.random()} nicht genutzt werden, denn dieser Methodenaufruf ist
 * synchronisiert und somit wird die parallele Implementierung langsamer sein
 * als die sequentielle.
 *
 *
 * <dt><b>Hilfestellung</b>
 *
 * <dd>
 * <p>
 * Das Integral einer Funktion entspricht der Fläche zwischen dem Graphen der
 * Funktion und der x-Achse. Hierbei zählen die Flächenstücke unterhalb der
 * x-Achse negativ. Das Integral gibt somit die Flächenbilanz an: Integral =
 * blaue Fläche - rote Fläche
 *
 * <dd>
 * <p>
 * Um das Ganze zu vereinfachen kann folgendermaßen vorgegangen werden: Zunächst
 * wird allerdings das gesamte Rechteck gegeben durch die Intervalle [a,b] und
 * [minY,maxY] für die Zufallskoordinaten herangezogen, als sähe die Funktion so
 * aus:
 *
 * <dd>
 * <p>
 * Man „verschiebt“ somit die y-Achse entsprechend nach oben oder unten (hier:
 * nach unten)
 *
 * <dd>
 * <p>
 * Das ist allerdings zu viel bzw. zu wenig. Daher muss das rot gestreifte
 * Rechteck wieder abgezogen oder addiert (Fallunterscheidung anhand von minY)
 * werden, um das Ergebnis zu korrigieren.
 *
 * <dd>
 * <p>
 * Das Ergebnis ist dann das Integral der Ausgangsfunktion. Blaue Fläche minus
 * rote Fläche.
 * </dl>
 */
package org.bschlangaul.aufgaben.tech_info.monte_carlo;
