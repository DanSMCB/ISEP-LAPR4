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
package eapli.base.usermanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 *
 *  Classe destinada a listar os utilizadores e criar novos roles.
 */
public final class BaseRoles {
    /**
     * USER - Poderá servir caso o utilizador não for nenhum dos roles do Projeto Integrador
     */
    public static final Role CLIENT_USER = Role.valueOf("USER");
    /**
     * Base Administrator (Developer)
     */
    public static final Role ADMIN = Role.valueOf("ADMIN");
    /**
     *
     */

    // Roles do Projeto Integrador
    public static final Role STUDENT = Role.valueOf("STUDENT");
    public static final Role TEACHER = Role.valueOf("TEACHER");
    public static final Role MANAGER = Role.valueOf("MANAGER");

    /**
     * get available role types for adding new users
     *
     * @return
     */
    public static Role[] nonUserValues() {
        return new Role[] { STUDENT, TEACHER, MANAGER };
    }

    public boolean isCollaborator(final Role role) {
        return role != CLIENT_USER;
    }
}
