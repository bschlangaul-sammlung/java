/**
 * Quelle: <a
 * href="https://www.philipphauer.de/study/se/design-pattern/state.php">Philipp
 * Hauer</a>
 *
 * <p>Die Zustände müssen in eigenen Objekten gekapselt werden. Schauen wir
 * uns doch noch einmal die Methoden unterhalten(), kussGeben() und
 * verärgern() an. Sie haben alle eine ähnliche Struktur von
 * Bedingungsanweisungen. Nun liegt es Nahe, alle Bedingungszweige, der
 * logisch zu einem Zustand gehört, auch in ein gemeinsames Objekt (das
 * Zustandsobjekt) zu übertragen. Damit enthält jedes entstandene
 * Zustandsobjekt das Verhalten für diesen Zustand.</p>
 *
 * <p>Die Zustandsklassen haben die selbe Schnittstelle, wie die Freundin
 * (unterhalten(), kussGeben(), verärgern()). Die Freundin aggregiert
 * fortan ein solches Zustandsobjekt (= aktueller Zustand) und delegiert
 * Aufrufe an dieses Zustandsobjekt. Dazu wird eine Schnittstelle (hier
 * Interface Zustand) für die Zustände eingeführt.</p>
 *
 * <p>Damit die Zustandsobjekte selbstständig den Zustand der Freundin
 * wechseln können, benötigen sie eine Referenz auf die Freundin.
 * Weiterhin muss die Freundin um einen Setter zum Setzen des
 * gewünschten Zustands erweitert werden und ein mit der Freundin
 * parametrisierter Konstrukur für die Zustände definiert werden, damit
 * die Zustände die Freundin kennen und den aktuellen Zustand der
 * Freundin setzen können.</p>
 */
package org.bschlangaul.entwurfsmuster.zustand.hauer;
