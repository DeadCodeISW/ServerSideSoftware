package com.freelanceworld.accounts.service;

import com.freelanceworld.accounts.domain.model.Employer;
import com.freelanceworld.accounts.domain.repository.EmployerRepository;
import com.freelanceworld.accounts.domain.service.EmployerService;
import com.freelanceworld.common.AccountType;
import com.freelanceworld.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    @Override
    public Employer createEmployer(Employer employer) {
        if(employerRepository.findByUsername(employer.getUsername()) != null)
            return employerRepository.findByUsername(employer.getUsername());
        employer.setPassword(encoder.encode(employer.getPassword()));
        employer.setAccountType(AccountType.EMPLOYER);
        return employerRepository.save(employer);
    }

    @Override
    public Employer updateEmployer(Long employerId, Employer employerDetails) {
        return employerRepository.findById(employerId).map(employer -> {
            employer.setFirstname(employerDetails.getFirstname());
            employer.setLastname(employerDetails.getFirstname());
            employer.setCompanyName(employerDetails.getCompanyName());
            employer.setDescription(employerDetails.getDescription());
            employer.setPersonalPhone(employerDetails.getPersonalPhone());
            employer.setContactCompanyEmail(employerDetails.getContactCompanyEmail());
            employer.setWebPage(employerDetails.getWebPage());
            employer.setFacebookLink(employerDetails.getFacebookLink());
            employer.setInstagramLink(employerDetails.getInstagramLink());
            employer.setTwitterLink(employerDetails.getTwitterLink());
            employer.setImageUrl(employerDetails.getImageUrl());
            return employerRepository.save(employer);
        }).orElseThrow(() -> new ResourceNotFoundException("Employer", "Id", employerId));
    }

    @Override
    public ResponseEntity<?> deleteEmployer(Long employerId) {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("Employer", "Id", employerId));
        employerRepository.delete(employer);
        return ResponseEntity.ok().build();
    }

    @Override
    public Optional<Employer> getEmployerById(Long employerId) {
        return employerRepository.findById(employerId);
    }

    @Override
    public List<Employer> getEmployerByCompanyName(String companyName) {
        return employerRepository.findEmployersByCompanyName(companyName);
    }

    @Override
    public List<Employer> getEmployersByFirstname(String firstName) {
        return employerRepository.findEmployersByFirstname(firstName);
    }

    @Override
    public List<Employer> getEmployersByLastname(String lastName) {
        return employerRepository.findEmployersByLastname(lastName);
    }
}
