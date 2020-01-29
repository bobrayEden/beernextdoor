package com.bobrayeden.beernextdoor.repository;

import com.bobrayeden.beernextdoor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
