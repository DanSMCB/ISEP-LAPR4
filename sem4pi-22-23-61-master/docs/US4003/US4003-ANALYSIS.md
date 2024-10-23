**Análise User Story 4003 - Aceitar/Rejeitar convites para um meeting**


* **REGRAS DE NEGÓCIO**

    * O utilizador tem a opção de aceitar ou rejeitar o convite
    * O convite tem 3 estados: pendente, aceite, rejeitado



* **Perguntas ao cliente:**

    * **Pergunta 1**: When mentioning "accept or reject meeting", I though to myself about an invite being sent to the user and 2 options for the response:
        * -The sent invite has already as a response "Rejected", so that it can either be changed to "Accepted" or stays as it is, seeing that if the user doesn't accept it, he will be rejecting it.
        * -The sent invite has a response being  "No answer" and, at a certain time near the begining of the meeting, the answer would change to "Rejected". The answer can be changed before it at any time to "Accepted" or "Rejected"
  
    * **Resposta**: In FRM04, the status of someone that did not answer should be "no answer" or "unknown".



* **TESTES UNITÁRIOS**

    * EnsureInviteStateChangesToAcceptedWhenUserAccepts
    * EnsureMeetingContainsUserWhenInviteIsAccepted
    * EnsureInviteStateChangesToRefusedWhenUserRefuses
    * EnsureMeetingDoesNotContainsUserWhenInviteIsRefused