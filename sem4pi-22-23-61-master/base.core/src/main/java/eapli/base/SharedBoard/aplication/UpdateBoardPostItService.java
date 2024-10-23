package eapli.base.SharedBoard.aplication;

import eapli.base.SharedBoard.domain.*;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class UpdateBoardPostItService {

    private final SharedBoardRepository sharedBoardRepository;
    private PostIt postIt;

    public UpdateBoardPostItService(final SharedBoardRepository sharedBoardRepository) {
        this.sharedBoardRepository = sharedBoardRepository;
    }

    public Iterable<SharedBoard> findBoardsBySystemUser(SystemUser systemUser){
        return sharedBoardRepository.findBoardsBySystemUser(systemUser);
    }

    public boolean getBoardAccessType(SharedBoard sharedBoard, SystemUser systemUser){
        return sharedBoard.getAccessType(systemUser).equals(SharedBoard.AccessType.WRITE);
    }

    public void createPostIt(String content, SystemUser systemUser){
        this.postIt=new PostIt(content, systemUser);
    }

    public void updatePostIt(SharedBoard sharedBoard, int row, int column) throws InterruptedException {
        UpdateBoardPostItThread updateBoardPostItThread = new UpdateBoardPostItThread(sharedBoard.getBoard(), row, column, postIt);
        Thread thread = new Thread(updateBoardPostItThread);
        thread.start();
        thread.join();
    }

    public void movePostIt(SharedBoard sharedBoard, int row, int column, int newRow, int newColumn, SystemUser systemUser) throws InterruptedException {
        MoveBoardPostItThread moveBoardPostItThread = new MoveBoardPostItThread(sharedBoard.getBoard(), row, column, newRow, newColumn, systemUser);
        Thread thread = new Thread(moveBoardPostItThread);
        thread.start();
        thread.join();
    }
}
