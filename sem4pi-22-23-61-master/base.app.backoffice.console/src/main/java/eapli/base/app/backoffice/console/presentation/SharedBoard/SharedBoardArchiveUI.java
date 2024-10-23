package eapli.base.app.backoffice.console.presentation.SharedBoard;

import eapli.base.SharedBoard.aplication.SharedBoardArchiveController;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.domain.Shared_Board_Title;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class SharedBoardArchiveUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();


    private final SharedBoardArchiveController controller = new SharedBoardArchiveController(authz, PersistenceContext.repositories().sharedBoardRepository(), PersistenceContext.repositories().systemUserRepository());

    @Override
    public String headline() {
        return "Archive a board";
    }

    @Override
    protected boolean doShow() {
        Iterable<SharedBoard> sharedBoards = controller.findSharedBoardsByOwner(myUser);
        final SelectWidget<SharedBoard> selector = new SelectWidget<>("Shared Boards:", sharedBoards, new SharedBoardPrinter());
        selector.show();
        final SharedBoard sharedBoard = selector.selectedElement();
        if(sharedBoard!=null){
            Shared_Board_Title title = Shared_Board_Title.valueOf(Console.readLine("Enter the title of the board you want to archive: "));
            try {
                controller.archiveBoard(title, myUser.identity());
                System.out.println("Board with title " + title + " has been archived.");
            } catch (IllegalStateException e) {
                System.out.println("Failed to archive board: " + e.getMessage());
            }
        }else{
            System.out.println("No shared board selected. Please select a board to archive.");
        }

        return false;
    }
}
