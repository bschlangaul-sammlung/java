/**
 * Quelle: <a
 * href="https://www.philipphauer.de/study/se/design-pattern/observer.php">Philipp
 * Hauer</a>
 *
 * <p>Das Beobachter Pattern ermöglicht, dass sich Objekte (Beobachter,
 * beobachtendes Objekt) bei einem anderem Objekt (Gegenstand, beobachtetes
 * Objekt) registrieren und fortan vom diesem informiert werden, sobald
 * es sich ändert.</p>
 *
 * <p>Für die Beobachter wird eine einheitliche Schnittstelle (Interface)
 * mit mindestens einer Aktualisierungsmethode definiert. Diese wird vom
 * Gegenstand im Falle von Aktualisierungen aufgerufen und ist in den
 * meisten Fällen mit näheren Daten zur Änderung parametrisiert.
 * Konkrete Beobachter ({@link KonkreterBeobachterA},
 * {@link KonkreterBeobachterB}) implementieren das Interface und damit die
 * Aktualisierungsmethode und bestimmen somit, wie der Beobachter auf die
 * Benachrichtigung reagieren soll.</p>
 *
 * <p>Das Gegenstand ({@link Gegenstand}) benötigt Administrationsmethoden, damit sich Beobachter an-
 * und abmelden können. Meldet sich ein Beobachter an, so nimmt das
 * Gegenstand es in seine Liste der zu benachrichtigen Objekte auf. Treten
 * nun Änderungen am Gegenstandzustand auf, so werden alle registrierten
 * Beobachter informiert (notifyBeobachters()). Dies geschieht, in dem über
 * die Beobachterliste des Gegenstands iteriert wird, und auf jedem Beobachter
 * die Aktualisierungsmethode (update()) aufgerufen wird.</p>
 */
package org.bschlangaul.entwurfsmuster.beobachter.allgemein;
