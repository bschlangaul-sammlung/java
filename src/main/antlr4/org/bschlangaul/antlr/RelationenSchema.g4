grammar RelationenSchema;
einstiegsPunkt: relation+ EOF;
relation: relationenName '(' attribute ')';
attribute: attribut (trenner attribut)*;
relationenName: NAME;
attribut: (fremdSchluessel | attributName) (
		zusätzlicherSqlAusruck
		| istPrimaer
	)?;
fremdSchluessel: attributName '[' relationenName ']';
attributName: NAME;
istPrimaer: '*';
zusätzlicherSqlAusruck: '{' NAME+ '}';

trenner: KOMMA;
name: NAME;

// http://unicode.org/reports/tr44/#Properties
NAME: [a-zA-Z0-9_\p{Block=Latin_1_Supplement}]+;

KOMMA: ',';

WS: [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines
