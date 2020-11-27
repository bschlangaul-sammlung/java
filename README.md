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
