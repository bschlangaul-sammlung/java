grammar Graph;

graph: zeile+ (knoten | kante)? EOF;

zeile: (knoten | kante) TRENNER;

kante:
	von ' '* (gerichtet | ungerichtet) ' '* nach (
		' '* ':' ' '* gewicht
	)?;
gerichtet: '->';
ungerichtet: '--';
von: KNOTENNAME;
nach: KNOTENNAME;
gewicht: GLEITZAHL;

knoten: name ' '* (':' ' '* x ' '+ y)?;
name: KNOTENNAME;
x: GLEITZAHL;
y: GLEITZAHL;

fragment BUCHSTABE: [A-Za-z\p{Block=Latin_1_Supplement}];
fragment ZAHL: [0-9];

KOMMENTAR: [ \t]* '%' ~[\r\n;]* TRENNER -> skip;

KNOTENNAME: BUCHSTABE (BUCHSTABE | ZAHL | '_')*;

GLEITZAHL: DEZIMALZAHL | GANZZAHL;
DEZIMALZAHL: '-'? GANZZAHL '.' GANZZAHL;
GANZZAHL: '-'? ZAHL+;

TRENNER: ('\r'? '\n' | '\r' | ';')+ ' '*;
LEERZEICHEN: [ \t]+ -> skip;
