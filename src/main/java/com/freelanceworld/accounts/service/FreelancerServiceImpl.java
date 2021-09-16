package com.freelanceworld.accounts.service;

import com.freelanceworld.accounts.domain.model.Freelancer;
import com.freelanceworld.accounts.domain.repository.FreelancerRepository;
import com.freelanceworld.accounts.domain.service.FreelancerService;
import com.freelanceworld.common.AccountType;
import com.freelanceworld.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreelancerServiceImpl implements FreelancerService {
    @Autowired
    private FreelancerRepository freelancerRepository;
//    @Autowired
//    private DistrictRepository districtRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    @Override
    public Freelancer createFreelancer(Freelancer freelancer) {
        if(freelancerRepository.findByUsername(freelancer.getUsername())!=null)
            return freelancerRepository.findByUsername(freelancer.getUsername());
//        District district = districtRepository.findById(districtId).orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId));
        freelancer.setPassword(encoder.encode(freelancer.getPassword()));
        freelancer.setAccountType(AccountType.FREELANCER);
//        freelancer.setDistrict(district);
        Freelancer saved = freelancerRepository.save(freelancer);
        return saved;
    }

    @Override
    public Freelancer updateFreelancer(Long freelancerId, Freelancer freelancerDetails) {
        return freelancerRepository.findById(freelancerId).map(freelancer -> {
            if(freelancerDetails.getPhone() != null){
                freelancer.setPhone(freelancerDetails.getPhone());
            }
            if(freelancerDetails.getDescription() != null){
                freelancer.setDescription(freelancerDetails.getDescription());
            }
            if(freelancerDetails.getProfession() != null){
                freelancer.setProfession(freelancerDetails.getProfession());
            }
            if(freelancerDetails.getWebPage() != null){
                freelancer.setWebPage(freelancerDetails.getWebPage());
            }
            if(freelancerDetails.getFacebookLink() != null){
                freelancer.setFacebookLink(freelancerDetails.getFacebookLink());
            }
            if(freelancerDetails.getInstagramLink() != null){
                freelancer.setInstagramLink(freelancerDetails.getInstagramLink());
            }
            if(freelancerDetails.getTwitterLink() != null){
                freelancer.setTwitterLink(freelancerDetails.getTwitterLink());
            }
            if(freelancerDetails.getImageUrl() != null){
                freelancer.setImageUrl(freelancerDetails.getImageUrl());
            }
            return freelancerRepository.save(freelancer);

        }).orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", freelancerId));
    }

    @Override
    public ResponseEntity<?> deleteFreelancer(Long freelancerId) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", freelancerId));
        freelancerRepository.delete(freelancer);
        return ResponseEntity.ok().build();
    }

    @Override
    public Optional<Freelancer> getFreelancerById(Long freelancerId) {
        return freelancerRepository.findById(freelancerId);
    }

    @Override
    public List<Freelancer> getFreelancersByProfession(String profession) {
        return freelancerRepository.findAllByProfession(profession);
    }

    @Override
    public List<Freelancer> getFreelancersByFirstname(String firstName) {
        return freelancerRepository.findAllByFirstname(firstName);
    }

    @Override
    public List<Freelancer> getFreelancersByLastname(String lastName) {
        return freelancerRepository.findAllByLastname(lastName);
    }
}
