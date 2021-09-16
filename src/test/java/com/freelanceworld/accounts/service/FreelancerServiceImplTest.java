package com.freelanceworld.accounts.service;

import com.freelanceworld.accounts.domain.model.Freelancer;
import com.freelanceworld.accounts.domain.repository.FreelancerRepository;
import com.freelanceworld.accounts.domain.service.FreelancerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class FreelancerServiceImplTest {
    @MockBean
    private FreelancerRepository freelancerRepository;
    @Autowired
    private FreelancerService freelancerService;

    @TestConfiguration
    static class FreelancerServiceImplTestConfiguration{

        @Bean
        public FreelancerService freelancerService(){
            return new FreelancerServiceImpl();
        }
    }
    @Test
    public void whenGetFreelancerByIdWithValidIdThenReturnsFreelancer(){
        // Arrange
        Long id = 1L;
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        freelancer.setFirstname("Juan");
        System.out.println(freelancer);
        when(freelancerRepository.findById(id))
                .thenReturn(Optional.of(freelancer));
        // Act
        Optional<Freelancer> foundFreelancer = freelancerService.getFreelancerById(id);
        // Assert
        assertThat(foundFreelancer.get().getId()).isEqualTo(id);
    }

    @Test
    public void whenGetFreelancerByIdWithInvalidIdThenReturnsNull(){
        // Arrange
        Long id = 2L;
        when(freelancerRepository.findById(id))
                .thenReturn(null);
        // Act
        Optional<Freelancer> foundFreelancer = freelancerService.getFreelancerById(id);
        // Assert
        assertThat(foundFreelancer).isEqualTo(null);
    }

    @Test
    public void whenGetFreelancersByProfessionWithValidProfessionThenReturnsFreelancer(){
        // Arrange
        String profession = "Diseñador";
        List<Freelancer> freelancerList = new ArrayList<Freelancer>();
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        freelancer.setFirstname("Jose");
        freelancer.setProfession(profession);
        freelancerList.add(freelancer);
        System.out.println(freelancerList);
        when(freelancerRepository.findAllByProfession(profession))
                .thenReturn(freelancerList);
        // Act
        List<Freelancer> foundFreelancer = freelancerService.getFreelancersByProfession(profession);
        // Assert
        assertThat(foundFreelancer.get(0).getProfession()).isEqualTo(profession);
    }

    @Test
    public void whenGetFreelancersByProfessionWithInvalidProfessionThenReturnsNull(){
        // Arrange
        String profession = "Diseñador";
        when(freelancerRepository.findAllByProfession(profession))
                .thenReturn(null);
        // Act
        List<Freelancer> foundFreelancer = freelancerService.getFreelancersByProfession(profession);
        // Assert
        assertThat(foundFreelancer).isEqualTo(null);
    }

    @Test
    public void whenGetFreelancersByFirstnameWithValidFirstnameThenReturnsFreelancer(){
        String firstname = "Pedro";
        List<Freelancer> freelancerList = new ArrayList<Freelancer>();
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        freelancer.setFirstname(firstname);
        freelancerList.add(freelancer);
        System.out.println(freelancerList);
        when(freelancerRepository.findAllByFirstname(firstname))
                .thenReturn(freelancerList);
        //Act
        List<Freelancer> foundFreelancer = freelancerService.getFreelancersByFirstname(firstname);
        //Assert
        assertThat(foundFreelancer.get(0).getFirstname()).isEqualTo(firstname);
    }

    @Test
    public void whenGetFreelancersByFirstnameWithInvalidFirstnameThenReturnsNull(){
        String firstname = "Pedro";
        when(freelancerRepository.findAllByFirstname(firstname))
                .thenReturn(null);
        // Act
        List<Freelancer> foundFreelancer = freelancerService.getFreelancersByFirstname(firstname);
        // Assert
        assertThat(foundFreelancer).isEqualTo(null);
    }

    @Test
    public void whenGetFreelancersByLastnameWithValidLastnameThenReturnsFreelancer(){
        // Arrange
        String lastname = "Rodriguez";
        List<Freelancer> freelancerList = new ArrayList<Freelancer>();
        Freelancer freelancer = new Freelancer();
        freelancerList.add(freelancer);
        freelancer.setId(1L);
        freelancer.setFirstname("Pedro");
        freelancer.setLastname(lastname);
        when(freelancerRepository.findAllByLastname(lastname))
                .thenReturn(freelancerList);
        // Act
        List<Freelancer> foundFreelancer = freelancerService.getFreelancersByLastname(lastname);
        // Assert
        assertThat(foundFreelancer.get(0).getLastname()).isEqualTo(lastname);
    }

    @Test
    public void whenGetFreelancersByLastnameWithInvalidLastnameThenReturnsNull(){
        String lastname = "Rodriguez";
        when(freelancerRepository.findAllByLastname(lastname))
                .thenReturn(null);
        // Act
        List<Freelancer> foundFreelancer = freelancerService.getFreelancersByLastname(lastname);
        // Assert
        assertThat(foundFreelancer).isEqualTo(null);
    }
}
