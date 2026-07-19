package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrmLearnApplicationTests {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        assertFalse(countries.isEmpty());
    }

    @Test
    void testFindCountryByCode() throws CountryNotFoundException {
        Country india = countryService.findCountryByCode("IN");
        assertEquals("India", india.getName());
    }

    @Test
    void testAddAndDeleteCountry() throws CountryNotFoundException {
        Country testCountry = new Country("XX", "TestNation");
        countryService.addCountry(testCountry);
        Country fetched = countryService.findCountryByCode("XX");
        assertEquals("TestNation", fetched.getName());

        countryService.deleteCountry("XX");
        assertThrows(CountryNotFoundException.class, () -> {
            countryService.findCountryByCode("XX");
        });
    }

    @Test
    void testSearchByNameContaining() {
        List<Country> results = countryService.findByNameContaining("ou");
        assertFalse(results.isEmpty());
    }
}
