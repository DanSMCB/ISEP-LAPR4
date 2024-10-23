**Análise User Story 3007 - Alterar um post-it na board**


* **REGRAS DE NEGÓCIO**

    * Apenas utilizadores com permissão de write podem fazer alterações na board
    * O utilizador pode apenas alterar os post-it que criou na board
    * O utilizador pode alterar o conteúdo do post-it ou mover para uma célula livre
    * Apenas um utilizador de cada vez pode fazer alterações na board


* **TESTES UNITÁRIOS**

    * EnsureUserWithReadPermissionCantWrite
    * EnsurePostItIsNotUpdatedIfTheOriginalPositionIsNull
    * EnsurePostItIsNotUpdatedIfUserIsNotTheAuthorOfTheOriginalPostIt
    * EnsurePostItIsUpdatedIfAllConditionsAreMet
    * EnsurePostItIsNotMovedIfTheOriginalPositionIsNull
    * EnsurePostItIsNotMovedIfTheNewPositionIsOccupied
    * EnsurePostItIsNotMovedIfUserIsNotTheAuthorOfTheOriginalPostIt
    * EnsurePostItIsMovedIfAllConditionsAreMet