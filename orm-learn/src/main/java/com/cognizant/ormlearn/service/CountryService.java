package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Transactional
	public List<Country> getAllCountries(){
		List<Country> countries = countryRepository.findAll();	
		return countries;
	}
	
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException{
		Country country = null;
		Optional<Country> result = countryRepository.findById(countryCode);
		if (result.isPresent()) {
			country = result.get();
		}
		else {
			throw new CountryNotFoundException("No Such Country In Database");
		}
		return country;
		
	}
	@Transactional
	public void addCountry(Country country) {
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(String code , String name) throws CountryNotFoundException {
		Optional<Country> res = countryRepository.findById(code);
		if(!res.isPresent()) {
			throw new CountryNotFoundException("No Such Country In Database");
		}
		else {
			Country c = res.get();
			c.setName(name);
			countryRepository.save(c);
		}
		
	}
	
}
