/**
 * Quelle: <a
 * href="https://www.philipphauer.de/study/se/design-pattern/decorator.php">Philipp
 * Hauer</a>
 *
 * <p>
 * Gegeben sei folgendes Szenario: Ein Restaurant möchte seine beiden
 * beliebtesten Gerichte "Wiener Schnitzel mit Pommes" und "Wiener
 * Schnitzel mit Bratkartoffeln" modellieren. Der Preis und die
 * Beschreibung sollen via Methoden abgefragt werden.
 * </p>
 *
 * <p>
 * Unsere Basisgericht- und Beilagenklassen sollen konstant sein und
 * damit für Veränderung geschlossen, allerdings kann jedes Basisgericht
 * durch diverse Beilagen erweitert werden und ist somit für Erweiterung
 * offen. Das war jetzt sehr theoretisch. Wie realisieren wir dies?
 * </p>
 *
 * <p>
 * azu begreifen wir Beilagen als "Wrapper", als Hülle, die wir um ein
 * Basisgericht legen. Es sind Objekte, die ein Gericht (beispielweise
 * eine Basisgericht) besitzen. also eine Instanzvariabale auf ein
 * solches besitzen. Soll eine Salatbeilage seinen Preis ausgeben, so
 * fragt er zuerst sein Hüftsteak nach dessen Preis und addiert
 * anschließend seinen eigenen Salatpreis hinzu.
 * </p>
 *
 * <p>
 * Auch soll es möglich sein, Beilagen beliebig ineinander zu schachteln
 * (Hüftsteak mit Nudeln und Salat).
 * </p>
 *
 * <p>
 * Der Trick, den wir nun nutzen, ist, dass unsere Beilagen vom selben
 * Typ sind, wie die Basisgerichte: Gericht. Logisch: ein Basisgericht
 * und eine Beilage zusammen sind immer noch ein Gericht. Beilagen
 * erweitern somit ebenso das Interface Gericht. Damit ist es möglich,
 * dass eine Beilage sowohl ein Basisgericht als auch eine andere
 * Beilage einpacken kann (diese wiederum ein anderes Gericht...).
 * </p>
 *
 * <p>
 * Damit wäre das Decorator Pattern prinzipiell schon realisiert. Nur
 * ist es üblich und zweckmäßig zwischen dem Interface Gericht und den
 * konkreten Beilagen noch eine abstrakte Beilagenklasse zu schalten,
 * die generischen Code für alle Beilagen enthalten kann. In unserem
 * Fall wäre dies die Deklaration der Instanzvariable auf ein Gericht,
 * sowie Methoden zum Setzen dieser Instanzvariable (Setter oder
 * Konstruktor).
 * </p>
 */
package org.bschlangaul.entwurfsmuster.dekorierer.hauer;
