grammar GCode;

gcode :
    start_end NEWLINE
    line (NEWLINE line)* NEWLINE
    start_end
    NEWLINE? EOF;

line : (
        comment |
        command WHITESPACE* COMMENT?
);

start_end : PERCENT;
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
g01 : G '01' WHITESPACE coordinates (speed)?;
g02 : G '02' WHITESPACE coordinates arc (speed)?;
g03 : G '03' WHITESPACE coordinates arc (speed)?;

g21 : G '21';

m2 : M '2';
m3 : M '3' (WHITESPACE 'S' DIGITS+)?;
m5 : M '5';

coordinates : x? y? z?; // jeder nur einmal, min 1, max 3
arc : i j;
speed : f;

f : F FLOAT (WHITESPACE)?;
i : I FLOAT (WHITESPACE)?;
j : J FLOAT (WHITESPACE)?;
x : X FLOAT (WHITESPACE)?;
y : Y FLOAT (WHITESPACE)?;
z : Z FLOAT (WHITESPACE)?;

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
fragment DIGIT : [0-9];

NEWLINE : ('\r'? '\n' | '\r')+ ;
WHITESPACE : (' ' | '\t') ;
SPECIAL : [."':];
COMMENT : '(' .*? ')';
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

