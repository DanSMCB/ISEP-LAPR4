package eapli.base.SharedBoard.aplication;

import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.domain.Shared_Board_Title;
import eapli.base.persistence.impl.inmemory.InMemorySharedBoardRepository;
import eapli.base.systemUserManagement.SystemUserRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.authz.domain.model.Password;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class SharedBoardServiceTest {
    private SharedBoardService sharedBoardService;
    private SystemUserRepository systemUserRepository;
    private InMemorySharedBoardRepository sharedBoardRepository;

    @Before
    public void setUp() {
        sharedBoardRepository = new InMemorySharedBoardRepository();
        sharedBoardService = new SharedBoardService(sharedBoardRepository, systemUserRepository);
    }
/*
    @Test
    public void createSharedBoard() {
        Username username = Username.valueOf("user");
        Optional<Password> password = Password.encodedAndValid("adeus", 0xf, 4141);
        SystemUser myUser = new SystemUser();

        sharedBoardService.createSharedBoard(myUser, "Titulo teste", 3, 3);


        Iterable<SharedBoard> sharedBoards = sharedBoardRepository.findSharedBoardsByOwner(myUser);
        int count = 0;
        for (SharedBoard board : sharedBoards) {
            if (board.getTitle().getTitle().equals("TestTitle")) {
                count++;
            }
        }
        assertEquals(1, count);
    }

 */
}
