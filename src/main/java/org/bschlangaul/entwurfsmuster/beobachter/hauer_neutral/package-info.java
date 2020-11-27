/**
 * Quelle: <a
 * href="https://www.philipphauer.de/study/se/design-pattern/observer.php">Philipp
 * Hauer</a>
 *
 * <p>Das Observer Pattern ermöglicht, dass sich Objekte (Observer,
 * beobachtendes Objekt) bei einem anderem Objekt (Subject, beobachtetes
 * Objekt) registrieren und fortan vom diesem informiert werden, sobald
 * es sich ändert.</p>
 *
 * <p>Für die Observer wird eine einheitliche Schnittstelle (Interface)
 * mit mindestens einer Aktualisierungsmethode definiert. Diese wird vom
 * Subject im Falle von Aktualisierungen aufgerufen und ist in den
 * meisten Fällen mit näheren Daten zur Änderung parametrisiert.
 * Konkrete Observer ({@link ConcreteObserverA},
 * {@link ConcreteObserverB}) implementieren das Interface und damit die
 * Aktualisierungsmethode und bestimmen somit, wie der Observer auf die
 * Benachrichtigung reagieren soll.</p>
 *
 * <p>Das Subject ({@link Subject}) benötigt Administrationsmethoden, damit sich Observer an-
 * und abmelden können. Meldet sich ein Observer an, so nimmt das
 * Subject es in seine Liste der zu benachrichtigen Objekte auf. Treten
 * nun Änderungen am Subjectzustand auf, so werden alle registrierten
 * Observer informiert (notifyObservers()). Dies geschieht, in dem über
 * die Observerliste des Subjects iteriert wird, und auf jedem Observer
 * die Aktualisierungsmethode (update()) aufgerufen wird.</p>
 */
package org.bschlangaul.entwurfsmuster.beobachter.hauer_neutral;
