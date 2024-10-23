**Análise User Story 3006 - Criar um post-it na board**


* **REGRAS DE NEGÓCIO**

    * O conteúdo do post-it pode ser texto ou uma imagem
    * Apenas utilizadores com permissão de write podem fazer alterações na board
    * Apenas um utilizador de cada vez pode fazer alterações na board



* **Perguntas ao cliente:**

    * **Pergunta 1**: As a client, do you want us to persist post-its in the database or they can be available only while the server is running (deleted if the server stops)?
    * **Resposta**: For me the information regarding the shared boards may be available only during the shared board server execution.

    * **Pergunta 2**: Uma célula pode ter mais do que um post it?
    * **Resposta**: Neste momento (no âmbito deste projeto) isso não será necessário. A ser possível (uma célula com mais do que 1 post-it) isso iria dificultar algumas funcionalidades, como a que permite mudar um post-it.

    * **Pergunta 3**: Quando um User cria um post-it deve passar um link da imagem por exemplo:
      "https://www.isep.ipp.pt/img/logo_20230106.png"
      Ou devemos anexar uma imagem que está no nosso computador?
    * **Resposta**: Para o cliente é um pouco indiferente o mecanismo que usam para fazer o "post" de imagens (assim como o(s) formato(s) suportado(s)).



* **TESTES UNITÁRIOS**

    * EnsureUserWithReadPermissionCantWrite
    * EnsurePostItIsNotAddedIfTheDesiredPositionIsOccupied
    * EnsurePostItIsAddedIfTheDesiredPositionIsFree