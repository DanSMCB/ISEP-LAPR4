grammar Exame;

prog: exame;

exame: TESTE DOISPONTOS ABRECHAVETA corpo_exame FECHACHAVETA;

corpo_exame: ID DOISPONTOS id_exame=INT VIRGULA TITULO DOISPONTOS titulo_exame=FRASE seccao+ DATAABERTURA DOISPONTOS data_abertura=DATA DATAFECHO DOISPONTOS data_fecho=DATA;

seccao: SECCAO ID DOISPONTOS id_seccao=INT VIRGULA ABRECHAVETA DESCRICAO_TEXTUAL DOISPONTOS desc_seccao=FRASE DIFICULDADE DOISPONTOS dificuldade_seccao=INT LIMITE_PERGUNTAS DOISPONTOS limite=INT PERGUNTAS DOISPONTOS pergunta+ FECHACHAVETA;

pergunta: PERGUNTA ID DOISPONTOS id_pergunta=INT VIRGULA ENUNCIADO DOISPONTOS enunciado_pergunta=FRASE tipo_pergunta=TIPO_PERGUNTA DOISPONTOS corpo COTACAO DOISPONTOS cotacao_pergunta=INT DIFICULDADE DOISPONTOS dificuldade_pergunta=INT;

corpo: correspondencia
    | escolha_multipla
    | resposta_curta
    | numerica
    | palavras_em_falta
    | verdadeiro_falso;

verdadeiro_falso: VERDADEIROOUFALSO RESPOSTA DOISPONTOS resposta_verdOuFal = (VERDADEIRO | FALSO) ;

palavras_em_falta: PALAVRAS_EM_FALTA RESPOSTA DOISPONTOS ABRECHAVETA resposta_falta+ FECHACHAVETA;

resposta_falta : RESPOSTA INT DOISPONTOS resp_falta=FRASE;

numerica: NUMERICA RESPOSTA DOISPONTOS resp_numerica=NUMERO;

resposta_curta: RESPOSTA_CURTA RESPOSTA DOISPONTOS resp_curta=FRASE;

escolha_multipla: ESCOLHA_MULTIPLA OPCOES DOISPONTOS ABREPARANTESIS opcao_escolha_multipla+ FECHAPARANTESIS RESPOSTA DOISPONTOS resposta_escolha_multipla=INT;

opcao_escolha_multipla: OPCAO INT DOISPONTOS opcao_escolha=FRASE;

correspondencia: CORRESPONDENCIA TABELA_A DOISPONTOS ABREPARANTESIS token_correspondencia+ FECHAPARANTESIS TABELA_B DOISPONTOS ABREPARANTESIS token_correspondencia+ FECHAPARANTESIS RESPOSTA DOISPONTOS respostas_correspondencia+;

token_correspondencia: ENUNCIADO INT DOISPONTOS resp_correspondencia=FRASE;

respostas_correspondencia: TABELA_A DOISPONTOS INT TRACO TABELA_B DOISPONTOS INT;

DOISPONTOS : ':';
ABRECHAVETA : '{';
FECHACHAVETA : '}';
ABREPARANTESIS : '[';
FECHAPARANTESIS : ']';
VIRGULA : ',';
TESTE : 'Teste';
ID : 'ID';
TRACO : '-';
RESPOSTA : 'Resposta';
TITULO : 'Titulo';
DATAABERTURA : 'DataAbertura';
DATAFECHO : 'DataFecho';
OPCOES : 'Opcoes';
OPCAO : 'Opcao';
ENUNCIADO : 'Enunciado';
TABELA_A : 'TabelaA';
TABELA_B : 'TabelaB';
COTACAO : 'Cotacao';
DIFICULDADE : 'Dificuldade';
DESCRICAO_TEXTUAL : 'DescricaoTextual';
PERGUNTAS : 'Perguntas';
LIMITE_PERGUNTAS : 'LimitePerguntas';
SECCAO : 'Seccao';
PERGUNTA : 'Pergunta';
TIPO_PERGUNTA : 'TipoPergunta';
NUMERICA : 'Numerica';
ESCOLHA_MULTIPLA : 'EscolhaMultipla';
CORRESPONDENCIA : 'Correspondencia';
VERDADEIROOUFALSO : 'VerdadeiroOuFalso';
PALAVRAS_EM_FALTA : 'PalavrasEmFalta';
RESPOSTA_CURTA : 'RespostaCurta';
VERDADEIRO : 'Verdadeiro';
FALSO : 'Falso';
TIPO : 'Tipo';
INT : [0-9]+;
DATA : (('0' [0-9]) | ('1' [0-9]) | ('2' [0-9]) | ('3' [0-1])) '/' (('0' [0-9]) | ('1' [0-2])) '/' (([0-9] [0-9] [0-9] [0-9])) ' ' ([0-9][0-9]) DOISPONTOS ([0-9][0-9]);
WS: [\r\n ]+ -> skip;
CARACTERE_ESPECIAL: [_()+/\-'#"] -> skip;
NUMERO: INT'.'INT | INT;
FRASE: STRING (ESPACO STRING)*;
STRING : [a-zA-Z]+;
ESPACO: [ \t] -> skip;
