/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.infrastructure.bootstrapers;

import eapli.base.Classe.domain.*;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.Course.Domain.*;
import eapli.base.Course.Repository.CourseRepository;
import eapli.base.SharedBoard.domain.NumberofColumns;
import eapli.base.SharedBoard.domain.NumberofRows;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.domain.Shared_Board_Title;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.base.Student_Teacher.Date_Of_Birth;
import eapli.base.Student_Teacher.Student.Repository.StudentRepository;
import eapli.base.Student_Teacher.Student.domain.MechanographicNumber;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.Student_Teacher.Tax_Payer_Number;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.domain.ExamDate;
import eapli.base.exammanagement.domain.ExamTime;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Base Bootstrapping data app
 *
 * @author Paulo Gandra de Sousa
 *
 * Classe destinada a criar um utilizador pelo bootstrap.
 */
@SuppressWarnings("squid:S106")
public class BaseBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseBootstrapper.class);

    private static final String ADMIN = "admin";
    private static final String ADMIN_PWD = "Password1";
    private static final String USER = "user";
    private static final String USER_PWD = "userA1";
    private static final String MANAGER = "manager";
    private static final String MANAGER_PWD = "managerA1";
    private static final String TEACHER = "teacher";
    private static final String TEACHER_PWD = "teacherA1";
    private static final String STUDENT = "student";
    private static final String STUDENT_PWD = "studentA1";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();
    private final StudentRepository studentRepository = PersistenceContext.repositories().studentRepository();
    private final CourseRepository courseRepository = PersistenceContext.repositories().courseRepository();
    private final ExamRepository examRepository = PersistenceContext.repositories().examRepository();
    private final ClassRepository classeRepository = PersistenceContext.repositories().classRepository();
    private final SharedBoardRepository sharedBoardRepository = PersistenceContext.repositories().sharedBoardRepository();

    private SystemUser user1;
    private SystemUser user2;
    private Teacher teacher;

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = { new MasterUsersBootstrapper(), };

        registerAdmin();
        registerUser();
        registerManager();
        registerTeacher();
        registerStudent();
        registerSharedBoard();
        try {
            registerCourseExams();
            registerClass1();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        authenticateForBootstrapping();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    /**
     * register a power user directly in the persistence layer as we need to
     * circumvent authorisations in the Application Layer
     */
    private boolean registerUser() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(USER).withPassword(USER_PWD).withName("Joao", "Martins")
                .withEmail("joaomartins@email.org").withRoles(BaseRoles.CLIENT_USER);
        final SystemUser newUser = userBuilder.build();

        SystemUser poweruser;
        try {
            poweruser = userRepository.save(newUser);
            assert poweruser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerAdmin() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(ADMIN).withPassword(ADMIN_PWD).withName("Developer", "Administrator")
                .withEmail("admin@email.org").withRoles(BaseRoles.ADMIN);
        final SystemUser newUser = userBuilder.build();

        SystemUser poweruser;
        try {
            poweruser = userRepository.save(newUser);
            assert poweruser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerManager() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(MANAGER).withPassword(MANAGER_PWD).withName("Main", "Manager")
                .withEmail("mainmanager@email.org").withRoles(BaseRoles.MANAGER);
        final SystemUser newUser = userBuilder.build();

        SystemUser manager;
        try {
            manager = userRepository.save(newUser);
            assert manager != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerTeacher() {
        LocalDate date = LocalDate.of(1990,10,10);

        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(TEACHER).withPassword(TEACHER_PWD).withName("Main", "Teacher")
                .withEmail("mainteacher@email.org").withRoles(BaseRoles.TEACHER);
        final SystemUser newUser = userBuilder.build();


        teacher=new Teacher(newUser, new Acronym("ABC"), new Tax_Payer_Number("123456789"), new Date_Of_Birth(date));
        try {
            user1 = userRepository.save(newUser);
            teacherRepository.save(teacher);
            assert user1 != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerStudent() {
        LocalDate date = LocalDate.of(2002,10,10);

        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(STUDENT).withPassword(STUDENT_PWD).withName("Main", "Student")
                .withEmail("mainstudent@email.org").withRoles(BaseRoles.STUDENT);
        final SystemUser newUser = userBuilder.build();

        try {
            user2 = userRepository.save(newUser);
            studentRepository.save(new Student(newUser, new MechanographicNumber("1200801"), new Tax_Payer_Number("123456789"), new Date_Of_Birth(date)));
            assert user2 != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerSharedBoard(){
        SharedBoard sharedBoard = new SharedBoard(user1, new Shared_Board_Title("title"), new NumberofRows(10), new NumberofColumns(10));
        sharedBoard.shareBoard(user1, user2, SharedBoard.AccessType.WRITE);

        sharedBoardRepository.save(sharedBoard);
        return false;
    }

    private boolean registerCourseExams() throws ParseException {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Application.settings().getPersistenceUnitName());
//        EntityManager em = emf.createEntityManager();
        Course course = new Course(new Course_Name("INF"), new Maximum_Number_Of_Students(200), new Minimum_Number_Of_Students(20), new Small_Textual_Description("desc"), teacher);
//        em.getTransaction().begin();
//        em.persist(course);
//        em.getTransaction().commit();
//        em.close();
//        emf.close();

        try {
            course = courseRepository.save(course);
            assert course != null;
            registerExam1(course);
            registerExam2(course);
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", course.getCourseName());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean registerExam1(Course course) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        Date open = simpleDateFormat.parse("09:30");
        Date close = simpleDateFormat.parse("10:30");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = simpleDateFormat2.parse("10-08-2023");

        Exam exam = new Exam(new ExamTitle("exam_title1"), new ExamDate(date), new ExamTime(open, close), course);   //after current time


        try {
            exam = examRepository.save(exam);
            assert exam != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", exam.identity());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }

    }

    private boolean registerExam2(Course course) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        Date open = simpleDateFormat.parse("09:30");
        Date close = simpleDateFormat.parse("10:30");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = simpleDateFormat2.parse("10-05-2023");

        Exam exam2 = new Exam(new ExamTitle("exam_title2"), new ExamDate(date2), new ExamTime(open, close), course); //before current time
        try {
            exam2 = examRepository.save(exam2);
            assert exam2 != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {

            LOGGER.warn("Assuming {} already exists (activate trace log for details)", exam2.identity());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }
    private boolean registerClass1() throws ParseException {

        LocalTime startTime = LocalTime.parse("09:00");
        LocalTime finishTime = LocalTime.parse("10:30");

        LocalDate date = LocalDate.parse("10-06-2023");
        DayOfWeek dayOfWeek = new DayOfWeek(1);

        Classe classe = new Classe(new Classe_Title("Class 1"), new Classe_Start_Date(date), new Classe_Finish_Date(date),
                new Classe_Start_Time(startTime), new Classe_Finish_Time(finishTime), dayOfWeek,
                new Acronym("C1"));

        try {
            classe = classeRepository.save(classe);
            assert classe != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
           
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", classe.identity());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }
    private boolean registerClass2() throws ParseException {

        LocalTime startTime = LocalTime.parse("11:00");
        LocalTime finishTime = LocalTime.parse("12:30");

        LocalDate date = LocalDate.parse("11-06-2023");
        DayOfWeek dayOfWeek = new DayOfWeek(2);

        Classe classe = new Classe(new Classe_Title("Class 2"), new Classe_Start_Date(date), new Classe_Finish_Date(date),
                new Classe_Start_Time(startTime), new Classe_Finish_Time(finishTime), dayOfWeek,
                new Acronym("C2"));

        try {
            classe = classeRepository.save(classe);
            assert classe != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated class
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", classe.identity());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }



    /**
     * authenticate a super user to be able to register new users
     *
     */
    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(MANAGER, MANAGER_PWD);
        Invariants.ensure(authz.hasSession());
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
