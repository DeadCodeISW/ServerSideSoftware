package com.freelanceworld.accounts.domain.repository;

import com.freelanceworld.accounts.domain.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,Long> {
    Employer findByFirstname(String firstname);
    Employer findByLastname(String lastname);
    List<Employer> findEmployersByCompanyName(String companyName);
    List<Employer> findEmployersByFirstname(String firstname);
    List<Employer> findEmployersByLastname(String lastname);
    Employer findByUsername(String username);
}
