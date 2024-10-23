package eapli.base.SharedBoard.aplication;

import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class UpdateBoardPostItController {

    private final UpdateBoardPostItService svc;

    public UpdateBoardPostItController(final SharedBoardRepository sharedBoardRepository) {
        svc = new UpdateBoardPostItService(sharedBoardRepository);
    }

    public boolean getBoardAccessType(SharedBoard sharedBoard, SystemUser systemUser){
        return svc.getBoardAccessType(sharedBoard, systemUser);
    }

    public Iterable<SharedBoard> findBoardsBySystemUser(SystemUser systemUser){
        return svc.findBoardsBySystemUser(systemUser);
    }

    public void createPostIt(String content, SystemUser systemUser){
        svc.createPostIt(content, systemUser);
    }

    public void updatePostIt(SharedBoard sharedBoard, int row, int column) throws InterruptedException {
        svc.updatePostIt(sharedBoard, row, column);
    }

    public void movePostIt(SharedBoard sharedBoard, int row, int column, int newRow, int newColumn, SystemUser systemUser) throws InterruptedException {
        svc.movePostIt(sharedBoard, row, column, newRow, newColumn, systemUser);
    }
}
