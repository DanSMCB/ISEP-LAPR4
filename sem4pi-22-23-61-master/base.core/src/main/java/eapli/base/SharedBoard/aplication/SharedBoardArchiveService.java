    package eapli.base.SharedBoard.aplication;

    import eapli.base.SharedBoard.domain.SharedBoard;
    import eapli.base.SharedBoard.domain.Shared_Board_Title;
    import eapli.base.SharedBoard.repository.SharedBoardRepository;
    import eapli.base.systemUserManagement.SystemUserRepository;
    import eapli.framework.infrastructure.authz.application.AuthorizationService;
    import eapli.framework.infrastructure.authz.domain.model.SystemUser;
    import eapli.framework.infrastructure.authz.domain.model.Username;

    import java.util.List;

    public class SharedBoardArchiveService {

        private final SystemUserRepository userRepository;
        private final SharedBoardRepository sharedBoardRepository;
        private final AuthorizationService authz;

        public SharedBoardArchiveService(final AuthorizationService authz, final SharedBoardRepository sharedBoardRepository, final SystemUserRepository systemUserRepository) {
            this.authz = authz;
            this.userRepository = systemUserRepository;
            this.sharedBoardRepository = sharedBoardRepository;
        }

        public void archiveBoard(Shared_Board_Title title, Username currentUser) {
            Iterable<SharedBoard> boardOptional = sharedBoardRepository.findByBoardId(title);
            if (!boardOptional.iterator().hasNext()) {
                throw new IllegalStateException("Board not found");
            }
            SharedBoard board = boardOptional.iterator().next();
            if (!board.getOwner().username().equals(currentUser)) {
                throw new IllegalStateException("Only the owner can archive the board");
            }
            board.archiveBoard();
            sharedBoardRepository.save(board);
        }

        public List<SharedBoard> findSharedBoardsByOwner(SystemUser owner) {
            return (List<SharedBoard>) sharedBoardRepository.findSharedBoardsByOwner(owner);
        }

    }
