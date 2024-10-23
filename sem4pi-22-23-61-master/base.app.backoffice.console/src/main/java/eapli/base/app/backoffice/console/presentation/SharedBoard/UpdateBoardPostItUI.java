package eapli.base.app.backoffice.console.presentation.SharedBoard;

import eapli.base.SharedBoard.aplication.CreateBoardPostItController;
import eapli.base.SharedBoard.aplication.UpdateBoardPostItController;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class UpdateBoardPostItUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final UpdateBoardPostItController controller = new UpdateBoardPostItController(
            PersistenceContext.repositories().sharedBoardRepository());
    @Override
    protected boolean doShow() {
        Iterable<SharedBoard> sharedBoards = controller.findBoardsBySystemUser(myUser);
        final SelectWidget<SharedBoard> selector = new SelectWidget<>("Shared Boards:", sharedBoards, new SharedBoardPrinter());
        selector.show();
        final SharedBoard sharedBoard = selector.selectedElement();
        if(sharedBoard!=null){
            if(controller.getBoardAccessType(sharedBoard, myUser)){
                System.out.println("Type \"0\" to update a created post-it or \"1\" to move a created post-it to a new position.");
                int option=Console.readOption(0,1,0);
                if(option==0){
                    int row = Console.readInteger("Type the row of the board where the post-it you wish to update is present.");
                    int column = Console.readInteger("Type the column of the board where the post-it you wish to update is present.");
                    String content = Console.readLine("Type the content of the post-it you wish to update.");
                    controller.createPostIt(content, myUser);
                    try {
                        controller.updatePostIt(sharedBoard, row, column);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    int row = Console.readInteger("Type the current row of the board where the post-it is present.");
                    int column = Console.readInteger("Type the current column of the board where the post-it is present.");
                    int newRow = Console.readInteger("Type the new row of the board you wish to move the post-it.");
                    int newColumn = Console.readInteger("Type the new column of the board you wish to move the post-it.");
                    try {
                        controller.movePostIt(sharedBoard, row, column, newRow, newColumn, myUser);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }else{
                System.out.println("You don't have permission to write on this board.");
            }

        }

        return false;
    }

    @Override
    public String headline() {
        return "Update a post-it in a board";
    }
}
