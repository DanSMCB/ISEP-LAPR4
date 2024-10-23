package eapli.base.SharedBoard.aplication;

import eapli.base.SharedBoard.domain.CreateBoardPostItThread;
import eapli.base.SharedBoard.domain.PostIt;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CreateBoardPostItService {

    private final SharedBoardRepository sharedBoardRepository;
    private PostIt postIt;
    private PostIt postItBackup;

    public CreateBoardPostItService(final SharedBoardRepository sharedBoardRepository) {
        this.sharedBoardRepository = sharedBoardRepository;
    }

    public Iterable<SharedBoard> findBoardsBySystemUser(SystemUser systemUser){
        return sharedBoardRepository.findBoardsBySystemUser(systemUser);
    }

    public boolean getBoardAccessType(SharedBoard sharedBoard, SystemUser systemUser){
        return sharedBoard.getAccessType(systemUser).equals(SharedBoard.AccessType.WRITE);
    }

    public void createPostIt(String content, SystemUser systemUser){
        if (postIt != null) {
            postItBackup = new PostIt(postIt.getContent(), postIt.getAuthor());
        }
        this.postIt=new PostIt(content, systemUser);
    }


    public void undoPostIt() {
        if (postItBackup != null) {
            postIt = new PostIt(postItBackup.getContent(), postItBackup.getAuthor());
            postItBackup = null;
        }
    }
    public void addPostIt(SharedBoard sharedBoard, int row, int column) throws InterruptedException {
        CreateBoardPostItThread createBoardPostItThread = new CreateBoardPostItThread(sharedBoard.getBoard(), row, column, postIt);
        Thread thread = new Thread(createBoardPostItThread);
        thread.start();
        thread.join();
    }
}
