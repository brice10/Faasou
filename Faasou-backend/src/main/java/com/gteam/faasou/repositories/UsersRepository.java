package com.gteam.faasou.repositories;

import com.gteam.faasou.beans.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Arleon Zemtsop
 */
public interface UsersRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {
    Users findByPseudo(String pseudo);

    Users findByEmail(String email);
}
