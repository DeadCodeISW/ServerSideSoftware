package com.freelanceworld.accounts.domain.service;

import com.freelanceworld.accounts.domain.model.background.Skill;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SkillService {
    List<Skill> getAllSkillsByFreelancerId(Long freelancerId);
    Skill getSkillByIdAndFreelancerId(Long skillId, Long freelancerId);
    Skill createSkill(Long freelancerId, Skill skill);
    Skill updateSkill(Long freelancerId, Long skillId, Skill skill);
    ResponseEntity<?> deleteSkill(Long freelancerId, Long skillId);
}
