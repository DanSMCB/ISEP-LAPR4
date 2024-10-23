package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.Course.Domain.Course;
import eapli.base.Course.Domain.Course_ID;
import eapli.base.Course.Domain.Course_Name;
import eapli.base.Course.Repository.CourseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaCourseRepository extends JpaAutoTxRepository<Course, Long, Course_Name> implements CourseRepository {

    public JpaCourseRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaCourseRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Iterable<Course> findCourseByName(String courseName) {
        final TypedQuery<Course> q = createQuery(
                "SELECT e FROM Course e WHERE e.courseName.value = :courseName", Course.class);
        q.setParameter("courseName", courseName);
        return q.getResultList();
    }

}
