**Análise User Story 4001 - Agendar um meeting**


* **REGRAS DE NEGÓCIO**

    * Qualquer utilizador pode criar um meeting
    * Qualquer utilizador pode ser convidado para o meeting
    * Se algum utilizador não estiver disponível, o meeting não deve ser criado



* **Perguntas ao cliente:**

    * **Pergunta 1**: Can any user of the system invite any other user? For example, can a student invite another student who is in a different course, or can a manager can create a meeting with any group of teachers.
    * **Resposta**: When in the document specification the term "User" is used it usually means "any user" of the system. Therefore, any user of the system can schedule a meeting and be a participant in a meeting.

    * **Pergunta 2**: Can a meeting and class overlap? If so, should the system notify that there is an overlap and for which user(s)?
    * **Resposta**: In the case of Meetings they should not be created if the participants are not available (i.e., they may have classes or other meetings at the same time).



* **TESTES UNITÁRIOS**

    * CancelMeeting_MeetingStatusChangedToCanceled