    package eapli.base.app.backoffice.console.presentation.SharedBoard;

    import eapli.base.SharedBoard.domain.SharedBoard;
    import eapli.base.infrastructure.persistence.PersistenceContext;
    import eapli.base.SharedBoard.aplication.SharedBoardController;

    import eapli.base.systemUserManagement.SystemUserRepository;
    import eapli.framework.infrastructure.authz.application.AuthorizationService;
    import eapli.framework.infrastructure.authz.application.AuthzRegistry;
    import eapli.framework.infrastructure.authz.application.UserSession;
    import eapli.framework.infrastructure.authz.domain.model.SystemUser;
    import eapli.framework.presentation.console.AbstractUI;

    public class SharedBoardListUI extends AbstractUI {

        private final AuthorizationService authz = AuthzRegistry.authorizationService();
        final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
        final SystemUser myUser = s.authenticatedUser();

        private final SharedBoardController controller = new SharedBoardController(PersistenceContext.repositories().sharedBoardRepository(), PersistenceContext.repositories().systemUserRepository());


        @Override
        protected boolean doShow() {
            final Iterable<SharedBoard> sharedBoards = this.controller.findSharedBoardsByOwner(myUser);
            for(SharedBoard sb : sharedBoards){
                System.out.println("Shared Board title: " + sb.getTitle());
            }
            return false;
        }

        @Override
        public String headline() {
            return "List shared boards";
        }
    }
