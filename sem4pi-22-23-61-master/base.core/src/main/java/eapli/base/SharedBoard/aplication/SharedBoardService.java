package eapli.base.SharedBoard.aplication;

import eapli.base.SharedBoard.domain.*;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.base.systemUserManagement.SystemUserRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;
import java.util.Optional;

public class SharedBoardService {
    private final SystemUserRepository userRepository;
    private final SharedBoardRepository sharedBoardRepository;
    private SharedBoard sharedBoard;

    public SharedBoardService(final SharedBoardRepository sharedBoardRepository, final SystemUserRepository systemUserRepository) {
        this.userRepository = systemUserRepository;
        this.sharedBoardRepository = sharedBoardRepository;
    }

    public void createSharedBoard(SystemUser myUser, String sharedBoardTitle, int numberOfRows, int numberOfColumns){
        this.sharedBoard = sharedBoardRepository.save(new SharedBoard(myUser, new Shared_Board_Title(sharedBoardTitle), new NumberofRows(numberOfRows), new NumberofColumns(numberOfColumns)));
    }

    public void shareBoard(String ownerUsername, String userToShareUsername, SharedBoard.AccessType accessType) throws InterruptedException {
        Iterable<SystemUser> ownerOptional = userRepository.findByUsername(Username.valueOf(ownerUsername));
        Iterable<SystemUser> userToShareOptional = userRepository.findByUsername(Username.valueOf(userToShareUsername));

        if (!ownerOptional.iterator().hasNext() || !userToShareOptional.iterator().hasNext()) {
            throw new IllegalStateException("Owner or user to share not found");
        }

        SystemUser owner = ownerOptional.iterator().next();
        SystemUser userToShare = userToShareOptional.iterator().next();

        SharedBoardThread sharingThread = new SharedBoardThread(sharedBoard, owner, userToShare, accessType);
        Thread thread = new Thread(sharingThread);
        thread.start();
        thread.join();

    }

    public Iterable<SharedBoard> listSharedBoards() {
        return sharedBoardRepository.findAll();
    }

    public Iterable<SharedBoard> findSharedBoardsByOwner(SystemUser owner) {
        return sharedBoardRepository.findSharedBoardsByOwner(owner);
    }
}
