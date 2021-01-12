/**
 * Picasso
 *
 * <p>
 * Es soll mit mehreren Threads eine Leinwand (canvas) parallelisiert bemalt
 * werden.
 *
 * <dl>
 * <dt><strong>Aufgaben 1</strong></dt>
 * <dd>
 * <p>
 * Jeder erzeugte Thread soll zufällig Felder auf der Leinwand mit passenden
 * Größe auswählen und diese in seiner Farbe (=Index/Nummer/id des Threads)
 * färben. Dies soll aber nur geschehen, wenn das Feld noch nicht gefärbt ist.
 * Wenn auf der Leinwand genug Felder gefärbt wurden ({@code finished==true}),
 * sollen sich die Threads beenden.
 *
 * <p>
 * Achte darauf, dass die Feldauswahl der Threads wirklich zufällig ist, und
 * dass jeder Thread beliebig oft jedes Feld auf der Leinwand färben könnte.
 *
 * <p>
 * Hinweis: Benutze hierfür die Methoden der Leinwand!
 *
 * <dl>
 * <dt><strong>Hilfestellung für die Zufälligkeit</strong></dt>
 * <dd>Die Zufälligkeit muss mithilfe der Klasse Random von Java
 * ({@code java.util.Random}) erzeugt werden: {@code nextInt(int bound)} Hier
 * darf {@code Math.random()} nicht genutzt werden, denn dieser Methodenaufruf
 * ist synchronisiert und somit könnte die parallele Implementierung langsamer
 * sein als die sequentielle.</dd>
 * </dl>
 *
 * <dl>
 * <dt><strong>Wettlaufsituation bei Picasso</strong></dt>
 * <dd>
 * <p>
 * Beim parallelen Bemalen der Leinwand kommt es zu einer Wettlaufsituation,
 * wenn zwei Threads gleichzeitig dasselbe zufällige Feld auswählen – welches
 * noch nicht bemalt ist – und beide dieses Feld mit ihrer Farbe bemalen.
 *
 * <p>
 * Dadurch bleibt ein anderes Feld unbemalt, weil sich die Leinwand nach
 * Breite*Höhe-vielen Bemalungen als fertig bemalt ansieht.</dd>
 * </dl>
 * </dd>
 *
 * <dt><strong>Aufgaben 2</strong></dt>
 * <dd>Verhindere mit geeigneten Mitteln diese Wettlaufsituation, sodass die
 * Leinwand immer vollständig bemalt wird!</dd>
 * </dl>
 */
package org.bschlangaul.aufgaben.tech_info.picasso;
