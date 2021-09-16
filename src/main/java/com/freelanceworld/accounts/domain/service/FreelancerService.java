package com.freelanceworld.accounts.domain.service;

import com.freelanceworld.accounts.domain.model.Freelancer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FreelancerService{
    List<Freelancer> getAllFreelancers();
    Freelancer createFreelancer(Freelancer freelancer);
    Freelancer updateFreelancer(Long freelancerId, Freelancer freelancerDetails);
    ResponseEntity<?> deleteFreelancer(Long freelancerId);
    Optional<Freelancer> getFreelancerById(Long freelancerId);
    List<Freelancer> getFreelancersByProfession(String profession);
    List<Freelancer> getFreelancersByFirstname(String firstName);
    List<Freelancer> getFreelancersByLastname(String lastName);
}
