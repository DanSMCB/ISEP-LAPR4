package eapli.base.ExtraClasse.aplication;

import eapli.base.ExtraClasse.domain.ExtraClasse;
import eapli.base.ExtraClasse.repository.ExtraClasseRepository;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@ApplicationService
public class ScheduleExtraClasseService {
    private final AuthorizationService authz;
    private final ExtraClasseRepository extraClassRepository;
    private final TeacherRepository teacherRepository;

    public ScheduleExtraClasseService(final AuthorizationService authz, final ExtraClasseRepository extraClassRepository, final TeacherRepository teacherRepository) {
        this.authz = authz;
        this.extraClassRepository = extraClassRepository;
        this.teacherRepository = teacherRepository;
    }

    /**
     * Schedules a new extra class
     *
     * @param extraClasse The extra class to be scheduled
     * @return the scheduled extra class
     */
    public ExtraClasse scheduleExtraClass(ExtraClasse extraClasse) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);
        return extraClassRepository.save(extraClasse);
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

    /**
     * Find teacher by system user
     *
     * @param systemUser system user to find the teacher
     * @return Teacher instance
     */
    public Teacher findTeacherBySystemUser(SystemUser systemUser) {
        return teacherRepository.findTeacherBySystemUser(systemUser);
    }
}
