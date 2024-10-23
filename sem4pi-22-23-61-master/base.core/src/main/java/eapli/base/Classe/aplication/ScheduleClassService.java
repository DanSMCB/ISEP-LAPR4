package eapli.base.Classe.aplication;

import eapli.base.Classe.domain.Classe;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@ApplicationService
class ScheduleClassService {
    private final AuthorizationService authz;
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;

    public ScheduleClassService(final AuthorizationService authz, final ClassRepository classRepository, final TeacherRepository teacherRepository) {
        this.authz = authz;
        this.classRepository = classRepository;
        this.teacherRepository = teacherRepository;
    }

    /**
     * Schedules a new class
     *
     * @param classe The class to be scheduled
     * @return the scheduled class
     */
    public Classe scheduleClass(Classe classe) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);
        return classRepository.save(classe);
    }

    /**
     * Returns all teachers
     *
     * @return all teachers
     */
    public Iterable<Teacher> teachers() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);
        return teacherRepository.findAll();
    }
    public Teacher findTeacherBySystemUser(SystemUser systemUser) {
        return teacherRepository.findTeacherBySystemUser(systemUser);
    }
}
