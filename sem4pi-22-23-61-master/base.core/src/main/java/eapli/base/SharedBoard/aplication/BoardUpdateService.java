/*package eapli.base.SharedBoard.aplication;

import eapli.base.SharedBoard.domain.Board;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.base.systemUserManagement.SystemUserRepository;

import java.util.ArrayList;
import java.util.List;


public class BoardUpdateService {
    private final SharedBoardRepository boardRepository;
    private SharedBoard sharedBoard;

    public BoardUpdateService(SharedBoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoardUpdateHistory(Board boardId) {
        Board board = boardRepository.findByBoardId(boardId);
        if (board != null) {
            return board.;
        }
        return new ArrayList<>(); // Retornar uma lista vazia se a board não for encontrada
    }
}*/

