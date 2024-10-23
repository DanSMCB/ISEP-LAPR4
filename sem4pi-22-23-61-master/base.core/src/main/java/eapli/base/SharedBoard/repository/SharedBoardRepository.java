package eapli.base.SharedBoard.repository;

import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.domain.Shared_Board_Title;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public interface SharedBoardRepository extends DomainRepository<Shared_Board_Title, SharedBoard> {

    Iterable<SharedBoard> findSharedBoardsByOwner(SystemUser owner);

    Iterable<SharedBoard> findByBoardId(Shared_Board_Title title);

    Iterable<SharedBoard> findBoardsBySystemUser(SystemUser systemUser);
}
