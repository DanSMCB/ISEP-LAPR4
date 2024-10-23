package eapli.base.app.backoffice.console.presentation.course;

import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.framework.visitor.Visitor;

public class TeacherPrinter implements Visitor<Teacher> {

    @Override
    public void visit(Teacher visitee) {
        System.out.printf("Teacher acronym: %s, Date of birth: %s, Tax payer number: %s\n",
                visitee.getAcronym(), visitee.getDate_of_Birth().getDateOfBirth(), visitee.getTax_payer_number().getNumber());
    }
}
