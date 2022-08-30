package com.ecommerce.app.security.repo;

import com.ecommerce.app.security.model.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DAOUser, Long> {

    DAOUser findByUsername(String username);
}