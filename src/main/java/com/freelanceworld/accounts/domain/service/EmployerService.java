package com.freelanceworld.accounts.domain.service;

import com.freelanceworld.accounts.domain.model.Employer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployerService {
    List<Employer> getAllEmployers();
    Employer createEmployer(Employer employer);
    Employer updateEmployer(Long employerId, Employer employerDetails);
    ResponseEntity<?> deleteEmployer(Long employerId);

    Optional<Employer> getEmployerById(Long employerId);
    List<Employer> getEmployerByCompanyName(String companyName);
    List<Employer> getEmployersByFirstname(String firstName);
    List<Employer> getEmployersByLastname(String lastName);
}
