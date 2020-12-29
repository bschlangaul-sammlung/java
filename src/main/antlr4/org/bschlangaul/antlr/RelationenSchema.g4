grammar RelationenSchema;
einstiegs_punkt:
	relation+ EOF; // match keyword hello followed by an identifier
relation: name '(' name (',' name)+ ')';
name: NAME | STRING_LITERAL;
// http://unicode.org/reports/tr44/#Properties
NAME:
	[a-zA-Z0-9_\p{Block=Latin_1_Supplement}]+; // match lower-case identifiers

STRING_LITERAL:
	'"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\'))* '"';

WS: [ \t\r\n]+ -> skip; // skip spaces, tabs, newlines
