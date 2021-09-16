package com.freelanceworld.accounts.controller;

import com.freelanceworld.accounts.domain.model.background.Skill;
import com.freelanceworld.accounts.domain.service.SkillService;
import com.freelanceworld.accounts.resource.Skill.SkillResource;
import com.freelanceworld.accounts.resource.Skill.SaveSkillResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FreelancerSkillsController {
    @Autowired
    private SkillService skillService;
    @Autowired
    private ModelMapper mapper;

    @Operation(tags = "Skills")
    @GetMapping("/freelancers/{freelancerId}/skills")
    public List<SkillResource> getSkillResources(@PathVariable Long freelancerId){
        List<Skill> skillList = skillService.getAllSkillsByFreelancerId(freelancerId);
        return skillList.stream().map(this::convertToResource).collect(Collectors.toList());
    }
    @Operation(tags = "Skills")
    @PostMapping("/freelancers/{freelancerId}/skills")
    public SkillResource createSkill(@PathVariable Long freelancerId, @Valid @RequestBody SaveSkillResource resource){

        Skill skill = convertToEntity(resource);
        return convertToResource(skillService.createSkill(freelancerId, skill));
    }
    @Operation(tags = "Skills")
    @PutMapping("/freelancers/{freelancerId}/skills/{skillId}")
    public SkillResource updateSkill(@PathVariable Long freelancerId, @PathVariable Long skillId, @Valid @RequestBody SaveSkillResource resource){
        Skill skill = convertToEntity(resource);
        return convertToResource(skillService.updateSkill(freelancerId, skillId, skill));
    }

    @Operation(tags = "Skills")
    @DeleteMapping("/freelancers/{freelancerId}/skills/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long freelancerId, @PathVariable Long skillId){
        return skillService.deleteSkill(freelancerId, skillId);
    }

    private SkillResource convertToResource(Skill skill){
        return mapper.map(skill, SkillResource.class);
    }

    private Skill convertToEntity(SaveSkillResource resource){
        return mapper.map(resource, Skill.class);
    }

}


