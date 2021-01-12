/**
 * <h2>Paraleller Mergesort</h2>
 *
 * <h3>Überblick</h3>
 *
 * <p>
 * In dieser Aufgabe soll ein einfaches Zahlenarray mittels des
 * MergeSort-Algorithmus sortiert werden. Für eine Wiederholung des Algorithmus
 * eignen sich folgende Kurzerklärungen:
 *
 * <ul>
 * <li>https://www.youtube.com/watch?v=JSceec-wEyw (1,5 min)
 * <li>https://www.youtube.com/watch?v=4VqmGXwpLqc (3 min)
 * </ul>
 *
 * <p>
 * Die Klasse MergeSortImpl soll dafür vervollständigt werden. Die
 * merge()-Methode ist dabei bereits vorgegeben. Sie führt das vorsortierte
 * Array left und das vorsortierte Array right im übergebenen Array arr in
 * richtiger Reihenfolge zusammen. Die Länge von arr muss dabei gleich der Länge
 * von left plus der Länge von right sein.
 *
 * <h3>Aufgabenstellung und Hilfestellung</h3>
 *
 * <ol>
 * <li>Schreibe zunächst die Methode seqMergeSort(). Diese Methode soll das
 * übergebene Array sequentiell rekursiv mit dem MergeSort-Algorithmus
 * sortieren.
 *
 * <dl>
 * <dt>Hilfestellung
 * <dd>
 * <p>
 * Damit ihr euch viel Arbeit erspart, dürft ihr Arrays beliebig kopieren. Das
 * ermöglicht, dass man beim Halbieren eines Arrays nicht mit Indizes arbeiten
 * muss, sondern sich einfach die beiden Hälften (Array left und Array right)
 * aus dem Ursprungsarray herauskopiert und anschließend wieder im
 * Ursprungsarray zusammenführt (merge)!
 *
 * <dd>Nutze hierfür die Methode Arrays.copyOfRange(...); siehe hierfür
 * https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html
 * </dl>
 *
 * <li>Nun soll der Algorithmus in parallelMergeSort() parallelisiert werden.
 *
 * <dl>
 * <dt>Hilfestellung
 * <dd>
 * <p>
 * Die Anzahl der verfügbaren Threads soll immer einer 2er-Potenz sein und wird
 * beim Methodenaufruf übergeben. So kann die rekursive sequentielle Methode
 * erweitert werden, dass je Halbierung zwei neue Threads erzeugt werden. Diese
 * Threads rufen in ihrer run()-Methode nur die ursprüngliche Methode
 * parallelMergeSort() (rekursiv) auf.
 *
 * <dd>
 * <p>
 * Wichtig: Beginne mit der Abbruchbedingung und stelle vor dem Testen sicher,
 * dass die Anzahl der benutzten Threads beschränkt ist. Ansonsten wird das
 * Programm deinen Rechner in die Knie zwingen!
 *
 * <dd>
 * <p>
 * Teste dein Programm mit der vorgegebenen Testklasse.
 *
 * <dd>
 * <p>
 * Anforderung: Für sehr große Arrays muss die parallele Implementierung
 * schneller sein!
 * </dl>
 * </ol>
 */
package org.bschlangaul.aufgaben.tech_info.merge_sort;
