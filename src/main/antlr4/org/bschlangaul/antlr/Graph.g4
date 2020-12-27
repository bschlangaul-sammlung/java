grammar Graph;

graph: zeile+ EOF;

zeile: (knoten | kante) ZEILEN_ENDE;

kante : von  ' -- '  nach;
von : KNOTENNAME;
nach : KNOTENNAME;

knoten : name ': ' x ' ' y;
name : KNOTENNAME;
x : INTEGER;
y : INTEGER;

fragment BUCHSTABE : [A-Za-z];
fragment ZAHL : [0-9];
fragment OPTIONALES_LEERZEICHEN: LEERZEICHEN+;

INTEGER : ZAHL+;
FLOAT : INTEGER | DECIMAL;


DECIMAL: INTEGER '.' INTEGER;

KNOTENNAME : BUCHSTABE+;
ZEILEN_ENDE : '\r'? '\n' | '\r' | ';';
LEERZEICHEN: [ \t]+;
