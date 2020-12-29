#! /bin/sh

rm -f *.java

START_RULE_NAME="einstiegs_punkt"
GRAMMAR_NAME="RelationenSchema"
JAR="/usr/local/lib/antlr-4.9-complete.jar"

# antlr4
java -Xmx500M -cp "$JAR:$CLASSPATH" org.antlr.v4.Tool "$GRAMMAR_NAME".g4

# javac
javac -cp "$JAR" "$GRAMMAR_NAME"*.java

# grun
java -Xmx500M -cp "$JAR:$CLASSPATH" org.antlr.v4.gui.TestRig \
  "$GRAMMAR_NAME" "$START_RULE_NAME" "$GRAMMAR_NAME".debug.txt -gui
