package com.freelanceworld.accounts.service;

import com.freelanceworld.accounts.domain.model.Freelancer;
import com.freelanceworld.accounts.domain.model.background.Skill;
import com.freelanceworld.accounts.domain.repository.FreelancerRepository;
import com.freelanceworld.accounts.domain.repository.SkillRepository;
import com.freelanceworld.accounts.domain.service.SkillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SkillServiceImplTest {
    @MockBean
    private SkillRepository skillRepository;
    @Autowired
    private SkillService skillService;
    @MockBean
    private FreelancerRepository freelancerRepository;
    @TestConfiguration
    static class SkillServiceImplTestConfiguration {
        @Primary
        @Bean(name = "skillServiceTest")
        public SkillService SkillService(){ return new SkillServiceImpl(); }
    }

    @Test
    void whenGetAllSkillByFreelancerId() {
        //Arrange
        Long idFreelancer = 1L;
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        freelancer.setFirstname("Juan");

        List<Skill> skillList = new ArrayList<>();

        Long id = 2L;
        Skill skill = new Skill();
        skill.setId(2L);
        skill.setName("java");
        skill.setFreelancer(freelancer);
        skillList.add(skill);

        Long id1 = 3L;
        Skill skill1 = new Skill();
        skill1.setId(3L);
        skill1.setName("mysql");
        skill1.setFreelancer(freelancer);
        skillList.add(skill1);

        when(skillRepository.findByFreelancerId(idFreelancer)).thenReturn(skillList);

        //Act
        List<Skill> foundSkills = skillService.getAllSkillsByFreelancerId(idFreelancer);

        //Assert
        assertThat(foundSkills.get(0).getFreelancer()).isEqualTo(freelancer);
        assertThat(foundSkills.size()).isEqualTo(2);
    }
}