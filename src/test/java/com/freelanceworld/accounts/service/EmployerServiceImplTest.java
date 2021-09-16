package com.freelanceworld.accounts.service;

import com.freelanceworld.accounts.domain.model.Employer;
import com.freelanceworld.accounts.domain.repository.EmployerRepository;
import com.freelanceworld.accounts.domain.service.EmployerService;
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
public class EmployerServiceImplTest {
    @MockBean
    private EmployerRepository employerRepository;
    @Autowired
    private EmployerService employerService;

    @TestConfiguration
    static class EmployerServiceImplTestConfiguration{
        @Bean
        public EmployerService employerService(){
            return new EmployerServiceImpl();
        }
    }
    @Test
    public void whenGetEmployerByIdWithValidIdThenReturnsEmployer(){
        // Arrange
        Long id = 1L;
        Employer employer = new Employer();
        employer.setId(1L);
        employer.setFirstname("Juan");
        System.out.println();
        when(employerRepository.findById(id))
                .thenReturn(Optional.of(employer));
        // Act
        Optional<Employer> foundEmployer = employerService.getEmployerById(id);
        // Assert
        assertThat(foundEmployer.get().getId()).isEqualTo(id);
    }

    @Test
    public void whenGetEmployerByIdWithInValidIdThenReturnsNull(){
        // Arrange
        Long id = 2L;
        when(employerRepository.findById(id))
                .thenReturn(null);
        // Act
        Optional<Employer> foundEmployer = employerService.getEmployerById(id);
        // Assert
        assertThat(foundEmployer).isEqualTo(null);
    }

    @Test
    public void whenGetEmployerByCompanyNameWithValidCompanyNameThenReturnsEmployer(){
        // Arrange
        String companyName = "Movistar";
        List<Employer> employerList = new ArrayList<Employer>();
        Employer employer = new Employer();
        employer.setId(1L);
        employer.setFirstname("Juan");
        employer.setCompanyName(companyName);
        employerList.add(employer);
        System.out.println();
        when(employerRepository.findEmployersByCompanyName(companyName))
                .thenReturn(employerList);

        // Act
        List<Employer> foundEmployer = employerService.getEmployerByCompanyName(companyName);

        // Assert
        assertThat(foundEmployer.get(0).getCompanyName()).isEqualTo(companyName);
    }

    @Test
    public void whenGetEmployerByCompanyNameWithInvalidCompanyNameThenReturnsNull(){
        // Arrange
        String companyName = "Movistar";
        when(employerRepository.findEmployersByCompanyName(companyName))
                .thenReturn(null);
        // Act
        List<Employer> foundEmployer = employerService.getEmployerByCompanyName(companyName);
        // Assert
        assertThat(foundEmployer).isEqualTo(null);
    }

    @Test
    public void whenGetEmployerByFirstnameWithValidFirstnameThenReturnsEmployer(){
        // Arrange
        String firstname = "Juan";
        List<Employer> employerList = new ArrayList<Employer>();
        Employer employer = new Employer();
        employer.setId(1L);
        employer.setFirstname(firstname);
        employerList.add(employer);
        System.out.println();
        when(employerRepository.findEmployersByFirstname(firstname))
                .thenReturn(employerList);
        // Act
        List<Employer> foundEmployer = employerService.getEmployersByFirstname(firstname);
        // Assert
        assertThat(foundEmployer.get(0).getFirstname()).isEqualTo(firstname);
    }

    @Test
    public void whenGetEmployerByFirstnameWithInvalidFirstnameThenReturnsNull(){
        // Arrange
        String firstname = "Juan";
        when(employerRepository.findEmployersByFirstname(firstname))
                .thenReturn(null);
        // Act
        List<Employer> foundEmployer = employerService.getEmployersByFirstname(firstname);
        // Assert
        assertThat(foundEmployer).isEqualTo(null);
    }

    @Test
    public void whenGetEmployerByLastnameWithValidLastnameThenReturnsEmployer(){
        // Arrange
        String lastname = "Perez";
        List<Employer> employerList = new ArrayList<Employer>();
        Employer employer = new Employer();
        employer.setId(1L);
        employer.setFirstname("Juan");
        employer.setLastname(lastname);
        employerList.add(employer);
        System.out.println();
        when(employerRepository.findEmployersByLastname(lastname))
                .thenReturn(employerList);
        // Act
        List<Employer> foundEmployer = employerService.getEmployersByLastname(lastname);
        // Assert
            assertThat(foundEmployer.get(0).getLastname()).isEqualTo(lastname);
    }

    @Test
    public void whenGetEmployerByLastnameWithInvalidLastnameThenReturnsNull(){
        // Arrange
        String lastname = "Perez";
        when(employerRepository.findEmployersByLastname(lastname))
                .thenReturn(null);
        // Act
        List<Employer> foundEmployer = employerService.getEmployersByLastname(lastname);
        // Assert
        assertThat(foundEmployer).isEqualTo(null);
    }
}
