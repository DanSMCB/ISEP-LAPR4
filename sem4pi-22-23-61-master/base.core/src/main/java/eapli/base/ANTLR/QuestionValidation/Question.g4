grammar Question;

pprog: (add_question | update_question)+;

add_question: ADD_QUESTION COLON OPENBRACE question_body CLOSEBRACE SEMI;

update_question: UPDATE_QUESTION COLON OPENBRACE question_id COMMA question_body CLOSEBRACE SEMI;

question_id: ID COLON INT;

question_body: (title | question_type | options | answer ) (COMMA (title | question_type | options | answer))* ;

title: TITLE COLON PHRASE;
question_type: QUESTION_TYPE COLON PHRASE;
options: OPTIONS COLON OPENBRACKETS option+ CLOSEBRACKETS;
answer: ANSWER COLON INT;

option: OPTION INT COLON PHRASE;

ADD_QUESTION: 'Add Question';
UPDATE_QUESTION: 'Update Question';
ID: 'ID';
TITLE: 'Title';
OPTIONS: 'Options';
OPTION: 'Option';
ANSWER: 'Answer';
QUESTION_TYPE: 'Question Type';
INT: [0-9]+;
PHRASE: '"' ~["]* '"';
COLON: ':';
COMMA: ',';
OPENBRACE: '{';
CLOSEBRACE: '}';
OPENBRACKETS: '[';
CLOSEBRACKETS: ']';
SEMI: ';';
WS: [\r\n\t ]+ -> skip;
