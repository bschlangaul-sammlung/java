# Java-Didaktik-Beispiele

Beispiel Java-Code für didaktische Zwecke

# javadoc Dokumentation erzeugen

```
mvn javadoc:javadoc
```

# Testen

```
mvn test
```

```
mvn install:install-file \
   -Dfile=/usr/local/share/java/jars/aplu5-3.34.jar \
   -DgroupId=ch.aplu \
   -DartifactId=aplu \
   -Dversion=3.34 \
   -Dpackaging=jar \
   -DgeneratePom=true
```

```
mvn install:install-file \
   -Dfile=/usr/local/share/java/jars/greenfoot-3.6.1.jar \
   -DgroupId=greenfoot \
   -DartifactId=greenfoot \
   -Dversion=3.6.1 \
   -Dpackaging=jar \
   -DgeneratePom=true
```

# Java-Coding-Style

## Methodenamen

Verb im Imperativ: `berechneEinnahmen(...)` [wie in diesem Wikipedia-Artikel](https://de.wikipedia.org/wiki/Methode_(Programmierung)#Beispiel)

Methodennamen sollen Verben sein und mit einem Kleinbuchstaben beginnen,
z. B. add oder remove.
[Wikipedia](https://de.wikipedia.org/wiki/Namenskonvention_(Datenverarbeitung)#Namenskonventionen_f%C3%BCr_Java)

Wie intelij reformat code. Umlaute im Methodennamen etc sind erlaubt.

```java
public class WortPaar extends WörterbuchEintrag {
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
