/**
 * Die Klasse {@link Reporter} kann dazu verwendet werden, Zwischenschritte von
 * Algorithmen (z. B. die verschiedenen Sortieralgorithmen) zu berichten. Es
 * können verschieden Ausgaben konfiguriert werden, z. B. eine Ausgabe auf der
 * Konsole oder Ausgabe als TeX-Markup.
 *
 * Diese Klasse kann von spezialisierten Reporter-Klassen (BaumReporter,
 * SortierReporter) geerbt werden. Sie soll unter dem Attribute
 * <em>berichte</em> in die entsprechenen Algorithmen-Klassen eingebunden
 * werden. Die Methodennamen dieser Klasse sind ausnahmsweise Substantive. So
 * werden die einzelnen Reporter-Methode beispielsweise folgendermaßen
 * aufgerufen: this.berichte.überschrift().
 */
package org.bschlangaul.helfer.report;
