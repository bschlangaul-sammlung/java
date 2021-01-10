/**
 * Aufgabe 1: Modellierung eines Reiseunternehmens
 * <p>
 * Es sei folgender Sachverhalt gegeben:
 * <p>
 * Ein Reiseunternehmen bietet verschiedene Reisen an. Dazu beschäftigt es eine
 * Reihe von Reiseleitern, wobei eine Reise von mindestens einem Reiseleiter
 * geleitet wird. Da Reiseleiter freiberuflich arbeiten, können sie bei mehreren
 * Reiseunternehmen Reisen leiten.
 * <p>
 * An einer Reise können mehrere Teilnehmer teilnehmen, ein Teilnehmer kann auch
 * an verschiedenen Reisen teilnehmen.
 * <p>
 * Eine Reise kann jedoch nur mit einer begrenzten Kapazität angeboten werden,
 * das heißt, zu einer bestimmten Reise kann nur eine begrenzte Anzahl von
 * Teilnehmern assoziiert werden. Als Ausgleich soll pro Reise eine Warteliste
 * verwaltet werden.
 * <p>
 * Modellieren Sie diesen erweiterten Sachverhalt in einem neuen Diagramm. Nicht
 * veränderte Klassen brauchen nicht noch einmal angegeben werden. Beachten Sie
 * dabei, dass die Reihenfolge bei einer Warteliste eine Rolle spielt.
 * <p>
 * Implementieren Sie die in Aufgabenteil b) modellierten Klassen in Java. Fügen
 * Sie eine Methode hinzu, die einen Teilnehmer von einer Reise entfernt. Dabei
 * soll automatisch der erste Platz der Warteliste zu einem Reiseteilnehmer
 * werden, wenn die Warteliste nicht leer ist. Achten Sie auf die
 * Navigierbarkeit Ihrer Assoziationen. Sie können davon ausgehen, dass die
 * Methode nur mit Teilnehmern aufgerufen wird, die in der Tat Teilnehmer der
 * Reise sind.
 */
package org.bschlangaul.examen.examen_46116.jahr_2010.fruehjahr.reiseunternehmen;
