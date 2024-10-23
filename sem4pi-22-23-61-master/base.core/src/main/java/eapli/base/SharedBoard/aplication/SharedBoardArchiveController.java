package eapli.base.SharedBoard.aplication;

import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.domain.Shared_Board_Title;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.base.systemUserManagement.SystemUserRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.springframework.stereotype.Controller;

@Controller
public class SharedBoardArchiveController {


    private final SharedBoardArchiveService sharedBoardArchiveService;

    public SharedBoardArchiveController(final AuthorizationService authz , final SharedBoardRepository sharedBoardRepository, final SystemUserRepository systemUserRepository) {
        sharedBoardArchiveService = new SharedBoardArchiveService(authz,sharedBoardRepository, systemUserRepository);
    }

    public void archiveBoard(Shared_Board_Title title, Username currentUser) {
        sharedBoardArchiveService.archiveBoard(title, currentUser);
    }

    public Iterable<SharedBoard> findSharedBoardsByOwner(SystemUser owner) {
        return sharedBoardArchiveService.findSharedBoardsByOwner(owner);
    }



}
