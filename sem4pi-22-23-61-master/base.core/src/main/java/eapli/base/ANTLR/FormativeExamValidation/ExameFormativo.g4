grammar ExameFormativo;

prog: exam;

exam: TESTE DOISPONTOS ABRECHAVETA formative_exam_body FECHACHAVETA;

formative_exam_body: ID DOISPONTOS id_exam=INT VIRGULA TITULO DOISPONTOS title_exam=FRASE section+;

section: SECÇÃO ID DOISPONTOS id_section=INT VIRGULA ABRECHAVETA DESCRICAO_TEXTUAL DOISPONTOS desc_section=FRASE DIFICULDADE DOISPONTOS difficulty_section=INT LIMITE_PERGUNTAS DOISPONTOS limit=INT TIPO_PERGUNTA DOISPONTOS question_type=QUESTION_TYPE FECHACHAVETA;

QUESTION_TYPE: 'Numérica' | 'Escolha Múltipla' | 'Correspondência' | 'Verdadeiro ou Falso' | 'Palavras em Falta' | 'Resposta Curta';

DOISPONTOS : ':';
ABRECHAVETA : '{';
FECHACHAVETA : '}';
VIRGULA : ',';
TESTE : 'Teste';
ID : 'ID';
TITULO : 'Título';
SECÇÕES : 'Secções';
ENUNCIADO : 'Enunciado';
COTACAO : 'Cotação';
DIFICULDADE : 'Dificuldade';
DESCRICAO_TEXTUAL : 'Descrição Textual';
LIMITE_PERGUNTAS : 'Limite de Perguntas';
SECÇÃO : 'Secção';
TIPO_PERGUNTA : 'Tipo de Pergunta';
INT : [0-9]+;
WS: [\r\n ]+ -> skip;
CARACTERE_ESPECIAL: [_()+/\-'#"] -> skip;
STRING : [a-zA-Z]+[0-9]+ | '_' | '\'' ;
ESPACO: [ \t] -> skip;
FRASE: CARACTERE_ESPECIAL? (PALAVRA|INT) PALAVRA (PALAVRA|INT|ESPACO|CARACTERE_ESPECIAL|VIRGULA)* FIM CARACTERE_ESPECIAL? ;
PALAVRA: [A-Za-z]+;
FIM: [.!?;];
