package eapli.base.sharedboardmanagement.domain;

import eapli.base.SharedBoard.aplication.CreateBoardPostItController;
import eapli.base.SharedBoard.domain.*;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreatePostItTest {

    private final CreateBoardPostItController controller = new CreateBoardPostItController(
            PersistenceContext.repositories().sharedBoardRepository());

    final SystemUserBuilder userBuilder = new SystemUserBuilder(new BasePasswordPolicy(), new PlainTextEncoder());
    final SystemUser newUser = userBuilder
            .withUsername("systemUserTest")
            .withPassword("password")
            .withName("SystemUser", "Test")
            .withEmail("systemusertest@email.org")
            .withRoles(BaseRoles.STUDENT)
            .build();

    final SystemUser newUser2 = userBuilder
            .withUsername("systemUserTest_update")
            .withPassword("password")
            .withName("SystemUser", "Test")
            .withEmail("systemusertest@email.org")
            .withRoles(BaseRoles.STUDENT)
            .build();

    SharedBoard sharedBoard = new SharedBoard(newUser, new Shared_Board_Title("title"), new NumberofRows(10), new NumberofColumns(10));
    @Before
    public void setUp() {
        sharedBoard.shareBoard(newUser, newUser2, SharedBoard.AccessType.READ);

        sharedBoard.getBoard().insertCell(1,1,new PostIt("post-it_1", newUser));
    }


    @Test
    void EnsureUserWithReadPermissionCantWrite(){
        Assertions.assertFalse(controller.getBoardAccessType(sharedBoard, newUser2));
    }

    @Test
    void EnsurePostItIsNotAddedIfTheDesiredPositionIsOccupied(){
        Assertions.assertFalse(sharedBoard.getBoard().insertCell(1,1,new PostIt("post-it_2", newUser)));
    }

    @Test
    void EnsurePostItIsAddedIfTheDesiredPositionIsFree(){
        Assertions.assertTrue(sharedBoard.getBoard().insertCell(1,2,new PostIt("post-it_3", newUser)));
    }
}
