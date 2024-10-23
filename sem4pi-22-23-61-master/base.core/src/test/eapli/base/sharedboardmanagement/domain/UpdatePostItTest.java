package eapli.base.sharedboardmanagement.domain;

import eapli.base.SharedBoard.aplication.CreateBoardPostItController;
import eapli.base.SharedBoard.aplication.UpdateBoardPostItController;
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

public class UpdatePostItTest {

    private final UpdateBoardPostItController controller = new UpdateBoardPostItController(
            PersistenceContext.repositories().sharedBoardRepository());

    final SystemUserBuilder userBuilder = new SystemUserBuilder(new BasePasswordPolicy(), new PlainTextEncoder());
    final SystemUser newUser = userBuilder
            .withUsername("systemUserTest_update")
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

    final SystemUser newUser3 = userBuilder
            .withUsername("systemUserTest_update")
            .withPassword("password")
            .withName("SystemUser", "Test")
            .withEmail("systemusertest@email.org")
            .withRoles(BaseRoles.STUDENT)
            .build();

    SharedBoard sharedBoard = new SharedBoard(newUser, new Shared_Board_Title("title"), new NumberofRows(10), new NumberofColumns(10));
    @Before
    public void setUp() {
        sharedBoard.shareBoard(newUser, newUser2, SharedBoard.AccessType.WRITE);
        sharedBoard.shareBoard(newUser, newUser3, SharedBoard.AccessType.READ);

        sharedBoard.getBoard().insertCell(1,1,new PostIt("post-it_1", newUser));
        sharedBoard.getBoard().insertCell(1,2,new PostIt("post-it_2", newUser2));
    }


    @Test
    void EnsureUserWithReadPermissionCantWrite(){
        Assertions.assertFalse(controller.getBoardAccessType(sharedBoard, newUser3));
    }

    @Test
    void EnsurePostItIsNotUpdatedIfTheOriginalPositionIsNull(){
        Assertions.assertFalse(sharedBoard.getBoard().updateCell(2,2,new PostIt("post-it",newUser)));
    }

    @Test
    void EnsurePostItIsNotUpdatedIfUserIsNotTheAuthorOfTheOriginalPostIt(){
        Assertions.assertFalse(sharedBoard.getBoard().updateCell(1,1,new PostIt("post-it",newUser2)));
    }

    @Test
    void EnsurePostItIsUpdatedIfAllConditionsAreMet(){
        Assertions.assertFalse(sharedBoard.getBoard().updateCell(1,1,new PostIt("post-it",newUser)));
    }

    @Test
    void EnsurePostItIsNotMovedIfTheOriginalPositionIsNull(){
        Assertions.assertEquals(sharedBoard.getBoard().moveCell(2,2,2,1,newUser),3);
    }

    @Test
    void EnsurePostItIsNotMovedIfTheNewPositionIsOccupied(){
        Assertions.assertEquals(sharedBoard.getBoard().moveCell(1,1,1,2,newUser),2);
    }

    @Test
    void EnsurePostItIsNotMovedIfUserIsNotTheAuthorOfTheOriginalPostIt(){
        Assertions.assertEquals(sharedBoard.getBoard().moveCell(1,1,2,2,newUser2),1);
    }

    @Test
    void EnsurePostItIsMovedIfAllConditionsAreMet(){
        Assertions.assertEquals(sharedBoard.getBoard().moveCell(1,1,3,3,newUser),0);
    }
}
