package eapli.base.SharedBoard.domain;

import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class SharedBoardThread implements Runnable {
    private SharedBoard sharedBoard;
    private SystemUser owner;
    private SystemUser userToShare;
    private SharedBoard.AccessType accessType;

    public SharedBoardThread(SharedBoard sharedBoard, SystemUser owner, SystemUser userToShare, SharedBoard.AccessType accessType) {
        this.sharedBoard = sharedBoard;
        this.owner = owner;
        this.userToShare = userToShare;
        this.accessType = accessType;
    }

    @Override
    public void run() {
        sharedBoard.shareBoard(owner, userToShare, accessType);
    }
}
