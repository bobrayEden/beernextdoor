package com.bobrayeden.beernextdoor.repository;

import com.bobrayeden.beernextdoor.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
