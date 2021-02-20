package com.collabera.hcp.repository;

import com.collabera.hcp.model.Enrollee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnrolleeRepository extends JpaRepository<Enrollee, Integer> {
}
