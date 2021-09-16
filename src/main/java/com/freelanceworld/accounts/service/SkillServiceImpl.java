package com.freelanceworld.accounts.service;

import com.freelanceworld.accounts.domain.model.background.Skill;
import com.freelanceworld.accounts.domain.repository.FreelancerRepository;
import com.freelanceworld.accounts.domain.repository.SkillRepository;
import com.freelanceworld.accounts.domain.service.SkillService;
import com.freelanceworld.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private FreelancerRepository freelancerRepository;

    @Override
    public List<Skill> getAllSkillsByFreelancerId(Long freelancerId) {
        return skillRepository.findByFreelancerId(freelancerId);
    }

    @Override
    public Skill getSkillByIdAndFreelancerId(Long skillId, Long freelancerId) {
        return skillRepository.findByIdAndFreelancerId(skillId, freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Skill not found with Id" + skillId +
                                "and FreelancerId" + freelancerId));
    }

    @Override
    public Skill createSkill(Long freelancerId, Skill skill) {
        return freelancerRepository.findById(freelancerId).map(freelancer -> {
            skill.setFreelancer(freelancer);
            return skillRepository.save(skill);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer", "Id", freelancerId));
    }

    @Override
    public Skill updateSkill(Long freelancerId, Long skillId, Skill skillDetails) {
       if(!freelancerRepository.existsById(freelancerId)) {
           throw new ResourceNotFoundException(
                   "Freelancer", "Id", freelancerId
           );
       }
       return skillRepository.findById(skillId)
                   .map(skill ->{
                       skill.setName(skillDetails.getName());
                       skill.setDescription(skillDetails.getDescription());
                       skill.setLevel(skillDetails.getLevel());
                       return skillRepository.save(skill);
                   } )
                   .orElseThrow(() -> new ResourceNotFoundException(
                           "Skill", "Id", skillId
                   ));

    }

    @Override
    public ResponseEntity<?> deleteSkill(Long freelancerId, Long skillId) {
        if(!freelancerRepository.existsById(freelancerId)) {
            throw new ResourceNotFoundException(
                    "Freelancer", "Id", freelancerId
            );
        }
        return skillRepository.findById(skillId)
                .map(skill ->{
                    skillRepository.delete(skill);
                    return ResponseEntity.ok().build();
                } )
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Skill", "Id", skillId
        ));
    }
}
