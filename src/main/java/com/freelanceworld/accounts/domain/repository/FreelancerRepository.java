package com.freelanceworld.accounts.domain.repository;

import com.freelanceworld.accounts.domain.model.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    Freelancer findByFirstname(String firstname);
    Freelancer findByLastname(String lastname);
    Freelancer findByProfession(String profession);
    Freelancer findByUsername(String username);
    List<Freelancer> findAllByFirstname(String firstname);
    List<Freelancer> findAllByLastname(String lastname);
    List<Freelancer> findAllByProfession(String lastname);
}
