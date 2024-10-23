Análise User Story 4002 - Como usuário, quero cancelar uma reunião

* **REGRAS DE NEGÓCIO**

Apenas um administrador têm permissão para cancelar uma reunião.
A reunião cancelada vai ter um status, SCHEDULED, COMPLETED, CANCELED, quando cancelada vai mudar o seu status para CANCELED

* **Perguntas ao cliente**

Há alguma ação adicional que precisa ser executada após o cancelamento de uma reunião?

* **TESTES UNITÁRIOS**
 
* Verificar se o criador da reunião ou um administrador pode cancelar a reunião com sucesso.
* Verificar se a reunião cancelada altera seu status para CANCELED.
* Verificar se uma reunião que já está concluída (status COMPLETED) ou já cancelada (status CANCELED) pode ser cancelada novamente - isso deve falhar.