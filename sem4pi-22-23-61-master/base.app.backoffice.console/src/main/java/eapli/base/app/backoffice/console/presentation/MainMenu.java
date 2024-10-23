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
package eapli.base.app.backoffice.console.presentation;

import eapli.base.app.backoffice.console.presentation.Classe.ScheduleClassAction;
import eapli.base.app.backoffice.console.presentation.Classe.ScheduleExtraClassAction;
import eapli.base.app.backoffice.console.presentation.Classe.UpdateClassAction;
import eapli.base.app.backoffice.console.presentation.SharedBoard.*;
import eapli.base.app.backoffice.console.presentation.authz.AddUserAction;
import eapli.base.app.backoffice.console.presentation.course.CreateCourseAction;
import eapli.base.app.backoffice.console.presentation.course.OpenCloseCourseAction;
import eapli.base.app.backoffice.console.presentation.course.OpenCloseEnrollementsAction;
import eapli.base.app.backoffice.console.presentation.enrollment.ApproveEnrollmentAction;
import eapli.base.app.backoffice.console.presentation.enrollment.RequestEnrollmentAction;
import eapli.base.app.backoffice.console.presentation.exam.CreateExamAction;
import eapli.base.app.backoffice.console.presentation.exam.ListCourseExamsAction;
import eapli.base.app.backoffice.console.presentation.exam.ListFutureExamsAction;
import eapli.base.app.backoffice.console.presentation.meeting.AcceptRefuseInvitesAction;
import eapli.base.app.backoffice.console.presentation.meeting.CancelMeetingAction;
import eapli.base.app.backoffice.console.presentation.meeting.ListParticipantAction;
import eapli.base.app.backoffice.console.presentation.meeting.ScheduleMeetingAction;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 *
 * Menu do usuário do backoffice
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS - MENU
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS - MENU
    private static final int UNDERDEVELOPMENT_MESSAGE = 1;

    // COURSE - MENU
    private static final int CREATE_COURSE = 1;
    private static final int OpenCloseEnrollement = 2;
    private static final int OpenCloseCourse = 3;

    // EXAMS - MENU
    private static final int CREATE_EXAM = 1;
    private static final int SEE_COURSE_EXAMS = 2;
    private static final int SEE_FUTURE_EXAMS = 3;
    private static final int ADMIN_SEE_FUTURE_EXAMS = 3;

    // CLASS - MENU
    private static final int SCHEDULE_CLASSES = 1;
    private static final int UPDATE_CLASSES = 2;
    private static final int SCHEDULE_EXTRA_CLASS = 3;

    // SHARED BOARD - MENU
    private static final int SHARE_THE_BOARD = 1;
    private static final int LIST_THE_BOARDS = 2;
    private static final int ARCHIVE_BOARD = 3;
    private static final int CREATE_POSTIT = 4;
    private static final int UPDATE_POSTIT = 5;
    private static final int UNDO_POSTIT = 6;

    // ENROLLMENT - MENU
    private static final int REQUEST_ENROLLMENT = 1;
    private static final int ACCEPT_REJECT_ENROLLMENT = 2;

    // MAIN MENU - GERAL
    private static final int MY_USER_OPTION = 1;

    // MAIN MENU - ADMINISTRADOR
    private static final int USERS_OPTION = 2;
    private static final int COURSE_MENU = 3;
    private static final int EXAM_MENU = 4;
    private static final int CLASS_MENU = 5;
    private static final int MEETINGS_MENU = 6;
    private static final int ENROLLMENT_MENU = 7;
    private static final int SHARED_BOARD_MENU = 8;
    private static final int SETTINGS_OPTION = 9;
    private static final int SCHEDULE_MEETING = 1;
    private static final int LIST_MEETING_INVITES = 2;
    private static final int LIST_PARTICIPANTS = 3;

    private static final int CANCEL_MEETING = 3;

    // MAIN MENU - MANAGER
    private static final int USERS_OPTION_MANAGER = 2;
    private static final int COURSE_MENU_MANAGER = 3;
    private static final int MEETINGS_MENU_MANAGER = 4;
    private static final int SHARED_BOARD_MENU_MANAGER = 5;
    private static final int SETTINGS_OPTION_MANAGER = 6;

    // MAIN MENU - TEACHER
    private static final int EXAM_MENU_TEACHER = 2;
    private static final int CLASS_MENU_TEACHER = 3;
    private static final int MEETINGS_MENU_TEACHER = 4;
    private static final int SHARED_BOARD_MENU_TEACHER = 5;

    // MAIN MENU - STUDENT
    private static final int EXAM_MENU_STUDENT = 2;
    private static final int CLASS_MENU_STUDENT = 3;
    private static final int MEETINGS_MENU_STUDENT = 4;
    private static final int ENROLLMENT_MENU_STUDENT = 5;
    private static final int SHARED_BOARD_MENU_STUDENT = 6;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        // Opções do Usuário
        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);


        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        // Opções do Administrador
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu courseMenu = buildCourseMenu();
            mainMenu.addSubMenu(COURSE_MENU, courseMenu);
            final Menu examMenu = buildExamMenu();
            mainMenu.addSubMenu(EXAM_MENU, examMenu);
            final Menu classMenu = buildClassMenu();
            mainMenu.addSubMenu(CLASS_MENU, classMenu);
            final Menu meetingMenu = buildMeetingMenu();
            mainMenu.addSubMenu(MEETINGS_MENU, meetingMenu);
            final Menu enrollmentMenu = buildEnrollmentMenu();
            mainMenu.addSubMenu(ENROLLMENT_MENU, enrollmentMenu);
            final Menu sharedMenu = buildSharedBoardMenu();
            mainMenu.addSubMenu(SHARED_BOARD_MENU,sharedMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        // Opções do Manager
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.MANAGER)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION_MANAGER, usersMenu);
            final Menu courseMenu = buildCourseMenu();
            mainMenu.addSubMenu(COURSE_MENU_MANAGER, courseMenu);
            final Menu meetingMenu = buildMeetingMenu();
            mainMenu.addSubMenu(MEETINGS_MENU_MANAGER, meetingMenu);
            final Menu sharedMenu = buildSharedBoardMenu();
            mainMenu.addSubMenu(SHARED_BOARD_MENU_MANAGER,sharedMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION_MANAGER, settingsMenu);
        }

        // Opções do Teacher
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)){
            final Menu examMenu = buildExamMenu();
            mainMenu.addSubMenu(EXAM_MENU_TEACHER, examMenu);
            final Menu classMenu = buildClassMenu();
            mainMenu.addSubMenu(CLASS_MENU_TEACHER, classMenu);
            final Menu meetingMenu = buildMeetingMenu();
            mainMenu.addSubMenu(MEETINGS_MENU_TEACHER, meetingMenu);
            final Menu sharedMenu = buildSharedBoardMenu();
            mainMenu.addSubMenu(SHARED_BOARD_MENU_TEACHER,sharedMenu);
        }

        // Opções do Student
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)){
            final Menu examMenu = buildExamMenu();
            mainMenu.addSubMenu(EXAM_MENU_STUDENT, examMenu);
            final Menu classMenu = buildClassMenu();
            mainMenu.addSubMenu(CLASS_MENU_STUDENT, classMenu);
            final Menu meetingMenu = buildMeetingMenu();
            mainMenu.addSubMenu(MEETINGS_MENU_STUDENT, meetingMenu);
            final Menu enrollmentMenu = buildEnrollmentMenu();
            mainMenu.addSubMenu(ENROLLMENT_MENU_STUDENT, enrollmentMenu);
            final Menu sharedMenu = buildSharedBoardMenu();
            mainMenu.addSubMenu(SHARED_BOARD_MENU_STUDENT,sharedMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Exiting the application... Bye!"));

        return mainMenu;
    }

    //Menu do Exam
    private Menu buildExamMenu(){
        final Menu menu = new Menu("Exam >");

        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.ADMIN)){
            menu.addItem(CREATE_EXAM, "Create a new exam", new CreateExamAction());
        }

        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.ADMIN)){
            menu.addItem(SEE_COURSE_EXAMS, "View list of all exams in a course", new ListCourseExamsAction());
        }

        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)){
            menu.addItem(SEE_FUTURE_EXAMS, "View list of all my future exams", new ListFutureExamsAction());
        }

        // ID Option diferente para o admin para evitar conflitos e erros de execução
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.ADMIN)){
            menu.addItem(ADMIN_SEE_FUTURE_EXAMS, "View list of all my future exams", new ListFutureExamsAction());
        }

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    //Menu das Classes
    private Menu buildClassMenu(){
        final Menu menu = new Menu("Class >");
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.ADMIN)){
            menu.addItem(SCHEDULE_CLASSES, "Schedule a new class",new ScheduleClassAction());

        }
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.ADMIN)){
            menu.addItem(UPDATE_CLASSES, "Update an existing class", new UpdateClassAction());

        }
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.ADMIN)){
            menu.addItem(SCHEDULE_EXTRA_CLASS, "Schedule a new extra class", new ScheduleExtraClassAction());

        }
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

    // Menu do Course
    private Menu buildCourseMenu() {
        final Menu menu = new Menu("Course >");
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.MANAGER, BaseRoles.ADMIN)){
            menu.addItem(CREATE_COURSE, "Create a new Course", new CreateCourseAction());
            menu.addItem(OpenCloseEnrollement, "Open/Close enrollements", new OpenCloseEnrollementsAction());
            menu.addItem(OpenCloseCourse, "Open/Close course", new OpenCloseCourseAction());
        }
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildSharedBoardMenu(){
        final Menu menu = new Menu("SharedBoard >");

        menu.addItem(SHARE_THE_BOARD, "Share the board with someone",new SharedBoardAction());
        menu.addItem(LIST_THE_BOARDS, "List all the boards",new SharedBoardListAction());
        menu.addItem(ARCHIVE_BOARD, "Archive the board",new SharedBoardArchiveAction());
        menu.addItem(CREATE_POSTIT, "Create a post-it in a board", new CreateBoardPostItAction());
        menu.addItem(UPDATE_POSTIT, "Update a post-it in a board", new UpdateBoardPostItAction());
        menu.addItem(UNDO_POSTIT, "Undo the last change in a post-it", new UndoPostItAction());


        return  menu;
    }

    // Menu do Meeting
    private Menu buildMeetingMenu(){
        final Menu menu = new Menu("Meeting >");

        menu.addItem(SCHEDULE_MEETING, "Schedule a meeting", new ScheduleMeetingAction());
        menu.addItem(LIST_MEETING_INVITES, "View list of meetings I've been invited to", new AcceptRefuseInvitesAction());
        menu.addItem(LIST_PARTICIPANTS, "List participants", new ListParticipantAction());
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.ADMIN)) {
            menu.addItem(CANCEL_MEETING, "Cancel the Meeting", new CancelMeetingAction());
        }
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildEnrollmentMenu() {
        final Menu menu = new Menu("Enrollment >");
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT, BaseRoles.ADMIN)) {
            menu.addItem(REQUEST_ENROLLMENT, "Request Enrollment",  new RequestEnrollmentAction());
        }
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.MANAGER, BaseRoles.ADMIN)) {
            menu.addItem(ACCEPT_REJECT_ENROLLMENT, "Accept / Reject enrollment requests", new ApproveEnrollmentAction());
        }
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    // Settings do Administrador
    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(UNDERDEVELOPMENT_MESSAGE, "Testing Option", new ShowMessageAction("This option is under development"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserAction());
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request", new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }





}
