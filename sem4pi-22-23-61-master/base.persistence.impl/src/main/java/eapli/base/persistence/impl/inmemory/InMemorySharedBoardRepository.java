package eapli.base.persistence.impl.inmemory;

import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.domain.Shared_Board_Title;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySharedBoardRepository extends InMemoryDomainRepository<SharedBoard, Shared_Board_Title> implements SharedBoardRepository {

    static {
        InMemoryInitializer.init();
    }


    @Override
    public Iterable<SharedBoard> findSharedBoardsByOwner(SystemUser owner) {
        return match(e -> e.getOwner().username().toString().equals(owner.username().toString()));
    }

    @Override
    public Iterable<SharedBoard> findByBoardId(Shared_Board_Title title) {
        return match(e -> e.getTitle().getTitle().equals(title.getTitle()));
    }

    @Override
    public Iterable<SharedBoard> findBoardsBySystemUser(SystemUser systemUser) {
        return match(e -> e.getSharedUsers().containsKey(systemUser));
    }
}
