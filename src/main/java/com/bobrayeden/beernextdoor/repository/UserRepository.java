package com.bobrayeden.beernextdoor.repository;

import com.bobrayeden.beernextdoor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNameUser(String nameUser);

    Optional<User> findByNameUserAndPassword(String nameUser, String password);

    Optional<User> findByEmailAndPassword(String email, String password);
}
