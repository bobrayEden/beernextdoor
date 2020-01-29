package com.bobrayeden.beernextdoor.repository;

import com.bobrayeden.beernextdoor.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
}
