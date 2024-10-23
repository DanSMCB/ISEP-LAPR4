package eapli.base.SharedBoard.Domain;

import eapli.base.SharedBoard.domain.NumberofColumns;
import eapli.base.SharedBoard.domain.NumberofRows;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.domain.Shared_Board_Title;
import eapli.framework.infrastructure.authz.domain.model.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;


public class SharedBoardTest {

    private SystemUser owner;
    private SystemUser currentUser;
    private SystemUser newUser;
    private Shared_Board_Title title;
    private NumberofRows numberOfRows;
    private NumberofColumns numberOfColumns;
    private SharedBoard sharedBoard;

    @Before
    public void setUp() {
        Set<Role> roles = Collections.emptySet();
       // owner = new SystemUser();
        currentUser = owner;
      //  newUser = new SystemUser();
        title = new Shared_Board_Title("Sample Title");
        numberOfRows = new NumberofRows(5);
        numberOfColumns = new NumberofColumns(5);

        sharedBoard = new SharedBoard(owner, title, numberOfRows, numberOfColumns);
    }

    @Test
    public void testShareBoard() {
        sharedBoard.shareBoard(currentUser, newUser, SharedBoard.AccessType.WRITE);
        assertEquals(SharedBoard.AccessType.WRITE, sharedBoard.getAccessType(newUser));
    }

    @Test(expected = IllegalStateException.class)
    public void testShareBoardNotOwner() {
        // SystemUser notOwnerUser = new SystemUser(Username.valueOf("notOwner"), new Password("password"), new Name("Not Owner Name"), new EmailAddress("notowner@mail.com"), roles);
       // sharedBoard.shareBoard(notOwnerUser, newUser, SharedBoard.AccessType.WRITE);
    }

    @Test
    public void testArchiveBoard() {
        sharedBoard.shareBoard(currentUser, newUser, SharedBoard.AccessType.WRITE);
        sharedBoard.archiveBoard();
        assertTrue(sharedBoard.isArchived());
        assertEquals(SharedBoard.AccessType.READ, sharedBoard.getAccessType(newUser));
    }

    private void assertTrue(boolean archived) {

    }

    @Test
    public void testSameAs() {
        SharedBoard otherBoard = new SharedBoard(owner, title, numberOfRows, numberOfColumns);
        assertTrue(sharedBoard.sameAs(otherBoard));
    }

    @Test
    public void testNotSameAs() {
        Shared_Board_Title otherTitle = new Shared_Board_Title("Other Title");
        SharedBoard otherBoard = new SharedBoard(owner, otherTitle, numberOfRows, numberOfColumns);
        assertFalse(sharedBoard.sameAs(otherBoard));
    }
}
