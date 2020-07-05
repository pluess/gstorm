grammar GCode;

gcode : line*;

line : (
    start |
    end |
    comment |
    command WHITESPACE* COMMENT?) NEWLINE;

start: PERCENT;
end: PERCENT;
comment : COMMENT;

command : (
    g00 |
    g01 |
    g02 |
    g03 |
    g21 |
    m2  |
    m3  |
    m5)  ;

g00 : G '00' WHITESPACE coordinates ;
g01 : G '01' WHITESPACE coordinates (WHITESPACE f)?;
g02 : G '02' WHITESPACE coordinates WHITESPACE arc (WHITESPACE f)?;
g03 : G '03' WHITESPACE coordinates WHITESPACE arc (WHITESPACE f)?;

g21 : G '21';

m2 : M '2';
m3 : M '3';
m5 : M '5';

coordinates : (x | y | z | WHITESPACE)+; // jeder nur einmal, min 1, max 3
arc : i WHITESPACE j;
speed : f WHITESPACE;

f : F FLOAT;
i : I FLOAT;
j : J FLOAT;
x : X FLOAT;
y : Y FLOAT;
z : Z FLOAT;


fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment DIGIT : [0-9];

NEWLINE : ('\r'? '\n' | '\r')+ ;
WHITESPACE : (' ' | '\t') ;
SPECIAL : [."':];
COMMENT : '(' (UPPERCASE | LOWERCASE | WHITESPACE | SPECIAL | DIGIT)+  ')';
PERCENT : '%';
DIGITS : DIGIT+;
F : 'F';
G : 'G';
I : 'I';
J : 'J';
M : 'M';
X : 'X';
Y : 'Y';
Z : 'Z';
FLOAT: ('+'|'-')? DIGITS '.' DIGITS;

