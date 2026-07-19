package com.cognizant.springlearn.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@RestController
public class CountryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	private Country indiaCountry;

	private List<Country> countries;

	@Autowired
	private CountryService countryService;

	@SuppressWarnings("unchecked")
	public CountryController() {
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		this.indiaCountry = context.getBean("in", Country.class);
		this.countries = context.getBean("countryList", List.class);
	}

	@GetMapping("/country")
	public Country getCountryIndia() {
		LOGGER.info("START");
		LOGGER.info("END");
		return indiaCountry;
	}

	@GetMapping("/countries")
	public List<Country> getAllCountries() {
		LOGGER.info("START");
		LOGGER.info("END");
		return countries;
	}

	@GetMapping("/countries/{code}")
	public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
		LOGGER.info("START");
		Country country = countryService.getCountry(code);
		LOGGER.info("END");
		return country;
	}

	@PostMapping("/countries")
	public Country addCountry(@RequestBody @Valid Country country) {
		LOGGER.info("START");
		LOGGER.info("END");
		return country;
	}

}
