/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.infrastructure.bootstrapers;

import java.util.HashSet;
import java.util.Set;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 * Edited by João Cruz
 *
 * Classe destinada a criar um utilizador para apresentação do projeto em forma de bootstrapper.
 */
public class MasterUsersBootstrapper extends UsersBootstrapperBase implements Action {

    @Override
    public boolean execute() {
        registerAdmin("Desenvolvedor", TestDataConstants.PASSWORD1, "Desenvolvedor", "LAPR4", "desenvolvedor.lapr4@local"); // Administrador
        registerManager("JoaoSimoes", TestDataConstants.PASSWORD1, "Joao", "Simoes", "joao.simoes@local"); // Manager 1
        registerManager("HenriqueMedina", TestDataConstants.PASSWORD1, "Henrique", "Medina", "henrique.medina@local"); // Manager 2
        registerTeacher("LuisFabiano", TestDataConstants.PASSWORD1, "Luis", "Fabiano", "luis.fabiano@local"); // Teacher 1
        registerTeacher("AlexandreMatias", TestDataConstants.PASSWORD1, "Alexandre", "Matias", "alexandre.matias@local"); // Teacher 2
        registerStudent("RicardoFilipe", TestDataConstants.PASSWORD1, "Ricardo", "Filipe", "ricardo.filipe@local"); // Student 1
        registerStudent("JoseAfonso", TestDataConstants.PASSWORD1, "Jose", "Afonso", "jose.afonso@local"); // Student 2
        return true;
    }

    /**
     *
     */
    private void registerAdmin(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.ADMIN);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerManager(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.MANAGER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerTeacher(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.TEACHER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerStudent(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.STUDENT);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}
