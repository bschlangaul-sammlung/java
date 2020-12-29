#! /bin/bash


if [ -z "$2" ]; then
  echo "Usage: $0 GRAMMAR_NAME START_RULE_NAME"
  exit 1
fi


_clean_up() {
  echo "clean up ..."
  rm -f *.java
  rm -f *.tokens
  rm -f *.interp
  rm -f *.class
}

_clean_up

GRAMMAR_NAME="$1"
START_RULE_NAME="$2"
DEBUG_FILE_SUFFIX="$3"

JAR="/usr/local/lib/antlr-4.9-complete.jar"

# antlr4
java -Xmx500M -cp "$JAR:$CLASSPATH" org.antlr.v4.Tool "$GRAMMAR_NAME".g4

# javac
javac -cp "$JAR" "$GRAMMAR_NAME"*.java

DEBUG_FILE="$GRAMMAR_NAME".debug.txt

if [ -n "$DEBUG_FILE_SUFFIX" ]; then
  DEBUG_FILE="$GRAMMAR_NAME".debug."$DEBUG_FILE_SUFFIX".txt
else
  DEBUG_FILE="$GRAMMAR_NAME".debug.txt
fi

# grun
java -Xmx500M -cp "$JAR:$CLASSPATH" org.antlr.v4.gui.TestRig \
  "$GRAMMAR_NAME" "$START_RULE_NAME" "$DEBUG_FILE" -gui

_clean_up
