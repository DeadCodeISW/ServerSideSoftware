package com.freelanceworld.locations.service;
import com.freelanceworld.locations.domain.model.District;
import com.freelanceworld.locations.domain.model.Province;
import com.freelanceworld.locations.domain.repository.DistrictRepository;
import com.freelanceworld.locations.domain.repository.ProvinceRepository;
import com.freelanceworld.locations.domain.service.DistrictService;
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
public class DistrictServiceImplTest {
    @MockBean
    private DistrictRepository districtRepository;
    @Autowired
    private DistrictService districtService;
    @MockBean
    private ProvinceRepository provinceRepository;

    @TestConfiguration
    static class DistrictServiceImplTestConfiguration{
        @Bean
        public DistrictService DistrictService(){
            return new DistrictServiceImpl();
        }
    }

    @Test
    public void WhenGetAllDistrictsByProvinceIdWithValidIdThenReturnsDistricts(){
        // Arrange
        Long id = 1L;
        Province province1 = new Province();
        province1.setId(id);
        province1.setName("Ayacucho");

        List<District> districtList = new ArrayList<>();

        Long districtId1 = 1L;
        District district1 = new District();
        district1.setId(districtId1);
        district1.setDistrictname("Cayma");
        district1.setProvince(province1);
        districtList.add(district1);

        Long districtId2 = 2L;
        District district2 = new District();
        district2.setId(districtId2);
        district2.setDistrictname("Characato");
        district2.setProvince(province1);
        districtList.add(district2);

        when(districtRepository.findAllByProvinceId(id)).thenReturn(districtList);

        // Act
        List<District> foundDistricts = districtService.getAllDistrictsByProvinceId(id);

        // Assert
        assertThat(foundDistricts.get(0).getProvince()).isEqualTo(province1);
        assertThat(foundDistricts.size()).isEqualTo(2);
    }

    @Test
    void whenGetDistrictByDistrictIdAndProvinceIdWithValidIdThenReturnsDistrict() {
        //Arrange
        Long id = 1L;
        District district = new District();
        district.setId(id);
        district.setDistrictname("test");

        Province province = new Province();
        province.setId(id);
        province.setName("asdasd");
        when(districtRepository.findByIdAndProvinceId(id,id)).thenReturn(Optional.of(district));
        //Act
        Optional<District> foundDistrict = districtService.getDistrictByIdAndProvinceId(id,id);
        //Assert
        assertThat(foundDistrict.get().getId()).isEqualTo(id);
    }

}