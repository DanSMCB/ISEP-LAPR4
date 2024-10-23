package eapli.base.systemUserManagement;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;

public interface SystemUserRepository extends DomainRepository<Username, SystemUser> {

    Iterable<SystemUser> findByUsername(Username name);
}
