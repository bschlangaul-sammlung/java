grammar RelationenSchema;
einstiegs_punkt: relation+ EOF;
relation: relations_name '(' attribute ')';
attribute: attribut (trenner attribut)*;
relations_name: NAME;
attribut: fremd_schluessel | attribut_name;
fremd_schluessel: attribut_name '[' relations_name ']';
attribut_name: NAME;

trenner: KOMMA;
name: NAME;

// http://unicode.org/reports/tr44/#Properties
NAME: [a-zA-Z0-9_\p{Block=Latin_1_Supplement}]+;

KOMMA: ',';

WS: [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines
