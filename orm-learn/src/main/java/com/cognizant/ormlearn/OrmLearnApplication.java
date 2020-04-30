package com.cognizant.ormlearn;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
@ComponentScan("com.*")
public class OrmLearnApplication {

	private static StockService stockService;
	private static CountryService countryService;
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static void testStock() {
		LOGGER.info("Start");
		List<Stock> stocks = stockService.getStocksInNov();
		LOGGER.debug("stock={}", stocks);
		LOGGER.info("End");

	}

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

	public static void testGetDepartment() {
		LOGGER.info("Start");
		Department department = departmentService.get(1);
		LOGGER.debug("Employee:{}", department.getEmployeeList());
		LOGGER.info("End");
	}

	public static void testUpdateEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(3);
		Department department = departmentService.get(1);
		employee.setDepartment(department);
		employeeService.save(employee);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.info("End");
	}

	public static void testAddEmployee() {
		LOGGER.info("Start");
		Employee emp = new Employee();
		emp.setId(5);
		emp.setName("Tom");
		emp.setDateOfBirth(new Date(1985 - 05 - 07));
		emp.setSalary(1400000);
		emp.setPermanent(true);
		Department department = departmentService.get(1);
		emp.setDepartment(department);
		employeeService.save(emp);
		LOGGER.debug("Employee:{}", emp);
		LOGGER.debug("Department:{}", emp.getDepartment());
		LOGGER.info("End");
	}

	public static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.info("End");
	}

	public static void testDeleteCountry() {
		countryService.deleteCountry("DL");
	}

	public static void main(String[] args) throws CountryNotFoundException {

		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = (CountryService) context.getBean(CountryService.class);
		employeeService = context.getBean(EmployeeService.class);
		departmentService = context.getBean(DepartmentService.class);
		testGetAllCountries();
		getAllCountriesTest();
		testAddCountry();
		testUpdateCountry();
		testDeleteCountry();
		testGetEmployee();
		testAddEmployee();
		testUpdateEmployee();
		testGetDepartment();
		LOGGER.info("Inside main");
	}

}
