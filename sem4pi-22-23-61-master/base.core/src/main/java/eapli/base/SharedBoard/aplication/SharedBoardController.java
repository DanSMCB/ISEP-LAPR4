package eapli.base.SharedBoard.aplication;

import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.base.systemUserManagement.SystemUserRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.stereotype.Controller;

@Controller
public class SharedBoardController {

    private final SharedBoardService sharedBoardService;

    public SharedBoardController(final SharedBoardRepository sharedBoardRepository, final SystemUserRepository systemUserRepository) {
        sharedBoardService = new SharedBoardService(sharedBoardRepository, systemUserRepository);
    }

    public void createSharedBoard(SystemUser myUser, String sharedBoardTitle, int numberOfRows, int numberOfColumns){
        sharedBoardService.createSharedBoard(myUser, sharedBoardTitle, numberOfRows, numberOfColumns);
    }

    public void shareBoard(String ownerUsername, String userToShareUsername, SharedBoard.AccessType accessType) throws InterruptedException {
        sharedBoardService.shareBoard(ownerUsername, userToShareUsername,accessType);
    }

  /*  public Iterable<SharedBoard> listSharedBoards() {
        return sharedBoardService.listSharedBoards();
    }

   */


    public Iterable<SharedBoard> findSharedBoardsByOwner(SystemUser owner) {
        return sharedBoardService.findSharedBoardsByOwner(owner);
    }




}
