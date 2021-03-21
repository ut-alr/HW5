grammar Expr;

formula
    : formula COMMA lequals
    | formula COMMA lnequals
    | lnequals
    | lequals
    ;


obj
    : FUNC LPAR objlist RPAR
    | VAR
    ;

lequals
    : LPAR  obj EQUALS obj RPAR
    ;

lnequals
    : LPAR  obj NEQUALS obj RPAR
    ;

objlist
    : obj COMMA objlist
    | obj
    ;


VAR
    : 'x' ('1' .. '9')('0' .. '9')*
    ;

FUNC
    : 'f' ('1' .. '9')('0'..'9')*
    ;

LPAR
    : '('
    ;

RPAR
    : ')'
    ;

COMMA
    : ','
    ;

EQUALS
    : '='
    ;

NEQUALS
    : '!='
    ;

WS
   : [ \r\n\t]+ -> skip
   ;