package eapli.base.app.backoffice.console.presentation.course;

import eapli.base.Course.Application.CreateCourseController;
import eapli.base.Course.Domain.Course;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CreateCourseUI extends AbstractUI {

    // Necessário completar esta US, ainda está incompleta, seguir um padrão semelhante ao das outras US
    //falta validar os cursos. Verificar se já nao existem
    private final CreateCourseController createCourseController = new CreateCourseController(PersistenceContext.repositories().courseRepository(), PersistenceContext.repositories().teacherRepository());
    Course course = new Course();
    @Override
    protected boolean doShow() {
        String name = Console.readLine("Name of the Course:");
        int minNumOfStud = Console.readInteger("Minimum number of students:");
        int maxNumOfStud = Console.readInteger("Maximum number of students:");
        String description = Console.readLine("Course Description:");
        final Iterable<Teacher> teachers = this.createCourseController.listTeachers();
        final SelectWidget<Teacher> selector = new SelectWidget<>("Teachers:", teachers, new TeacherPrinter());
        selector.show();
        final Teacher teacher = selector.selectedElement();
        if (teacher != null) {
            try {
                createCourseController.createCourse(name,minNumOfStud,maxNumOfStud,description, teacher);
            } catch (final IntegrityViolationException e) {
                System.out.println("Invalid Course");
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Create Course";
    }
}
