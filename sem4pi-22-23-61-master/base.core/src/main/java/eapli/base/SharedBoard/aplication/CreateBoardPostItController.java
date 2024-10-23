package eapli.base.SharedBoard.aplication;

import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CreateBoardPostItController {

    private final CreateBoardPostItService svc;

    public CreateBoardPostItController(final SharedBoardRepository sharedBoardRepository) {
        svc = new CreateBoardPostItService(sharedBoardRepository);
    }

    public boolean getBoardAccessType(SharedBoard sharedBoard, SystemUser systemUser){
        return svc.getBoardAccessType(sharedBoard, systemUser);
    }

    public void createPostIt(String content, SystemUser systemUser){
        svc.createPostIt(content, systemUser);
    }

    public Iterable<SharedBoard> findBoardsBySystemUser(SystemUser systemUser){
        return svc.findBoardsBySystemUser(systemUser);
    }

    public void addPostIt(SharedBoard sharedBoard, int row, int column) throws InterruptedException {
        svc.addPostIt(sharedBoard, row, column);
    }
    public void undoPostIt(){
        svc.undoPostIt();
    }
}
