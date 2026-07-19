package com.cognizant.springlearn.service;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

	private List<Country> countries;

	@SuppressWarnings("unchecked")
	public CountryService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		countries = context.getBean("countryList", List.class);
	}

	public Country getCountry(String code) throws CountryNotFoundException {
		return countries.stream()
				.filter(c -> c.getCode().equalsIgnoreCase(code))
				.findFirst()
				.orElseThrow(CountryNotFoundException::new);
	}

}
