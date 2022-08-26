# java

Die Group-ID des Java-Pakets ist `org.bschlangaul`. Der Java-Code
befindet sich sich - in viele Unterpakete aufgeteilt - im Verzeichnis
`src/main/java/org/bschlangaul`. Zu einigen Paketen gibt es Tests. Das
Verzeichnis für die Tests lautet: `src/test/java/org/bschlangaul`.

------------------------------------------------------------------------

## Über die verwendeten Bezeichner

Wo es möglich ist, werden in diesem Repository deutsche Bezeichner für
Java Klassen, Interfaces, Variablen etc. verwendet. Besonders in den
kleineren Jahrgangsstufen empfiehlt es sich, im Informatikunterricht
Deutsch bei der Programmierung einzusetzen, um die Einstiegshürde so
gering wie möglich zu halten. Wir wollen hier mit gutem Beispiel voran
gehen.

Bei Examensaufgaben, bei denen die Angabe ersichtlich englische
Bezeichner verwendet, wird eine Ausnahme von dieser Regel gemacht. Mit
Ausnahme von Klassen- und Interfacebezeichner werden auch Umlaute
verwendet (Es müssten dann auch Umlaute in den Dateinamen verwendet
werden, was zu Problemen führen kann). Java bietet uns diese Möglichkeit
an. Warum sollte diese Möglichkeit nicht genutzt werden.

Als Methodenname werden Verben im Imperativ verwenden, [wie zum Beispiel
in diesem
Wikipedia-Artikel](https://de.wikipedia.org/wiki/Methode_(Programmierung)#Beispiel)
(`berechneEinnahmen(...)`).

Methodennamen sollen mit einem Kleinbuchstaben beginnen. Der
[Wikipedia-Artikel über Namenkonventionen in der
Datenverarbeitung](https://de.wikipedia.org/wiki/Namenskonvention_(Datenverarbeitung)#Namenskonventionen_für_Java)
nennt diese englischen Methodennamen als Beispiele: `add()` oder
`remove()`.

------------------------------------------------------------------------

## Source-Code-Dokumentation

Die Source-Code-Dokumentation dieses Projekts kann mit dem Maven-Befehl

```
mvn javadoc:javadoc
```

erstellt werden.

------------------------------------------------------------------------

## Source-Code-Formatierungsregeln

Wie intelij reformat code.

```java
public class WortPaar extends WoerterbuchEintrag {
  private final String deutsch;

  private final String englisch;

  public WortPaar(String deutsch, String englisch) {
    this.deutsch = deutsch;
    this.englisch = englisch;
  }

  public String gibDeutschesWort() {
    return deutsch;
  }

  public String gibEnglischesWort() {
    return englisch;
  }
}
```

# Testen

```
mvn test
```

------------------------------------------------------------------------

## Abhängigkeiten

```
mvn install:install-file \
   -Dfile=dependencies/aplu5-3.34.jar \
   -DgroupId=ch.aplu \
   -DartifactId=aplu \
   -Dversion=3.34 \
   -Dpackaging=jar \
   -DgeneratePom=true
```

```
mvn install:install-file \
   -Dfile=dependencies/greenfoot-3.6.1.jar \
   -DgroupId=greenfoot \
   -DartifactId=greenfoot \
   -Dversion=3.6.1 \
   -Dpackaging=jar \
   -DgeneratePom=true
```

```
mvn install:install-file \
   -Dfile=dependencies/engine-alpha-3.2.0.jar \
   -DgroupId=ea.edu \
   -DartifactId=AlphaEngine \
   -Dversion=3.2.0 \
   -Dpackaging=jar \
   -DgeneratePom=true
```

## Kommandozeilen-Interface

```sh
Usage: bschlangaul-werkzeug.java [-hktVv] [-a=<ausgabe>] [COMMAND]
Kommandozeilen-Interface für die Java-Didaktik-Beispiele.
  -a, --ausgabe=<ausgabe>   Mögliche Werte: konsole, tex.
  -h, --help                Show this help message and exit.
  -k, --konsole             Passendes Textformat für die Konsole ausgaben.
  -t, --tex                 Als TeX ausgeben.
  -v, --redselig            Mache die Ausgabe redseliger (verbose).
  -V, --version             Print version information and exit.
Commands:
  baum, b        Führe Aufgaben zum Thema Binärebaum aus.
  bbaum, bb      Führe Aufgaben zum Thema BBaum aus.
  db, d          Führe Aufgaben zum Themenbereich Datenbanken aus.
    normalisation, n
                 Führe Aufgaben zum Themenbereich Datenbanken aus.
    relationen-schema, r
                 Suche in der TeX-Datei nach
                   liRelationenSchemaFormat-Umgebungen und formatiere daraus
                   TeX- und SQL-Markup.
  graph, g       Führe graphspezifische Aufgaben aus.
    tex, t       Lese Text-Datei ein und versuche einen Graph zu erkennen,
                   formatiere den Graphen dann für TeX.
    dijkstra, d  Den Dijkstra-Algorithmus ausführen.
    prim, p      Den Algorithmus von Prim ausführen.
    kruskal, k   Den Algorithmus von Kruskal ausführen.
    bfs, b       Die Breitensuche (breadth-first search = BFS) mit Hilfe einer
                   Warteschlange ausführen.
    dfs, f       Die Tiefensuche (depth-first search = DFS) mit Hilfe eines
                   Stapels ausführen.
    dfr, r       Die Tiefensuche (depth-first search = DFS) rekursiv ausführen.
  sortiere, so   Sortiere mit verschiedenen Algorithmen.
  projekt-sprachen, s
                 Nach Projektsprachen in einer TeX-Datei suchen und diese dann
                   ausführen.
```

## Eigene DSLs (Domain-specific languages)

Die Projektspezifischen Mini-Sprachen werden mit ANTLR geparst. Die
Grammatik-Definitionen befinden sich im Verzeichnis
`src/main/antlr4/org/bschlangaul/antlr`

### GraphenFormat

```
A: 3 5
Knoten: 1 -2.3

A -- Knoten
B -- C
A->D: 3
Knoten mit Leerzeichen -> Z: 42
```
