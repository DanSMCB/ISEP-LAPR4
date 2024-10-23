package eapli.base.app.backoffice.console.presentation.SharedBoard;

import eapli.base.SharedBoard.aplication.SharedBoardController;
import eapli.base.SharedBoard.aplication.SharedBoardService;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;

public class SharedBoardUI extends AbstractUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final SharedBoardController controller = new SharedBoardController(PersistenceContext.repositories().sharedBoardRepository(), PersistenceContext.repositories().systemUserRepository());

    @Override
    public String headline() {
        return "Share a board";
    }

    @Override
    protected boolean doShow() {

        String ownerUsername = myUser.username().toString();
        String sharedBoardTitle = Console.readNonEmptyLine("Enter the title of the shared board", "Shared board title cannot be empty");
        int numberOfRows = Console.readInteger("Enter the number of rows of the board");
        int numberOfColumns = Console.readInteger("Enter the number of columns for the board");
        String userToShareUsername = Console.readNonEmptyLine("Enter the username of the user you want to share the board with: ", "Username cannot be empty");
        String accessType = Console.readNonEmptyLine("Enter the access type for the user (READ/WRITE): ", "Access type cannot be empty");
        try {
            controller.createSharedBoard(myUser, sharedBoardTitle, numberOfRows, numberOfColumns);
            controller.shareBoard(ownerUsername, userToShareUsername, SharedBoard.AccessType.valueOf(accessType.toUpperCase()));
            System.out.println("Shared board with user: " + userToShareUsername + " with " + accessType + " access.");
        } catch (IllegalStateException e) {
            System.out.println("Failed to share board: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
