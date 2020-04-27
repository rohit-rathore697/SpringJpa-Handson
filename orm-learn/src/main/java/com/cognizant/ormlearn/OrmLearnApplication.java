package com.cognizant.ormlearn;

import java.util.List; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
@ComponentScan("com.*")
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;

	private static void testAddCountry() throws CountryNotFoundException {
		Country c = new Country();
		c.setCode("dl");
		c.setName("Delhi");
		countryService.addCountry(c);
		countryService.findCountryByCode("dl");
		
	}
	
	private static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");

	}
	private static void getAllCountriesTest() throws CountryNotFoundException {
		Country country = countryService.findCountryByCode("IN");
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");

	}
	
	private static void testUpdateCountry() throws CountryNotFoundException {
		countryService.updateCountry("dl", "New Delhi");
	}
	
	
	public static void main(String[] args) throws CountryNotFoundException {
		
			ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
			countryService = (CountryService)context.getBean(CountryService.class);
			testGetAllCountries();
			getAllCountriesTest();
			testAddCountry();
			testUpdateCountry();
	}

}
