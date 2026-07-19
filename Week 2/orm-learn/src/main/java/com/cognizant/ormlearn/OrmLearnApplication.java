package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.*;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.*;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;
    private static StockRepository stockRepository;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);
        stockRepository = context.getBean(StockRepository.class);

        LOGGER.info("Inside main");

        // Country tests
        testGetAllCountries();
        testFindCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testSearchByNameContaining();
        testSearchByNameStartingWith();

        // Stock tests
        testFacebookStocksSeptember();
        testGoogleStocksAbove1250();
        testTopVolumeStocks();
        testLowestNetflixStocks();

        // Employee / Department / Skill tests
        testGetEmployee();
        testAddEmployee();
        testGetDepartment();
        testGetAllPermanentEmployees();
        testGetAverageSalary();
        testGetAllEmployeesNative();
    }

    // ===== Country Test Methods =====

    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }

    private static void testFindCountryByCode() {
        LOGGER.info("Start");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testAddCountry() {
        LOGGER.info("Start");
        Country country = new Country("ZZ", "TestLand");
        countryService.addCountry(country);
        try {
            Country fetched = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Added Country:{}", fetched);
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start");
        try {
            countryService.updateCountry("ZZ", "TestLand Updated");
            Country updated = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Updated Country:{}", updated);
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("ZZ");
        try {
            countryService.findCountryByCode("ZZ");
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Country ZZ deleted successfully");
        }
        LOGGER.info("End");
    }

    private static void testSearchByNameContaining() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findByNameContaining("ou");
        countries.forEach(c -> LOGGER.debug("{}  {}", c.getCode(), c.getName()));
        LOGGER.info("End");
    }

    private static void testSearchByNameStartingWith() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findByNameStartingWith("Z");
        countries.forEach(c -> LOGGER.debug("{}  {}", c.getCode(), c.getName()));
        LOGGER.info("End");
    }

    // ===== Stock Test Methods =====

    private static void testFacebookStocksSeptember() {
        LOGGER.info("Start - Facebook Stocks September 2019");
        List<Stock> stocks = stockRepository.findByCodeAndDateBetween("FB",
                LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 30));
        stocks.forEach(s -> LOGGER.debug("{}", s));
        LOGGER.info("End");
    }

    private static void testGoogleStocksAbove1250() {
        LOGGER.info("Start - Google stocks above 1250");
        List<Stock> stocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL",
                new BigDecimal("1250"));
        stocks.forEach(s -> LOGGER.debug("{}", s));
        LOGGER.info("End");
    }

    private static void testTopVolumeStocks() {
        LOGGER.info("Start - Top 3 volume stocks");
        List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
        stocks.forEach(s -> LOGGER.debug("{}", s));
        LOGGER.info("End");
    }

    private static void testLowestNetflixStocks() {
        LOGGER.info("Start - Lowest 3 Netflix stocks");
        List<Stock> stocks = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        stocks.forEach(s -> LOGGER.debug("{}", s));
        LOGGER.info("End");
    }

    // ===== Employee / Department / Skill Test Methods =====

    private static void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End");
    }

    private static void testAddEmployee() {
        LOGGER.info("Start");
        Employee emp = new Employee();
        emp.setName("John Doe");
        emp.setSalary(45000);
        emp.setPermanent(true);
        emp.setDateOfBirth(new Date());
        Department dept = departmentService.get(1);
        emp.setDepartment(dept);
        employeeService.save(emp);
        LOGGER.debug("Employee after save:{}", emp);
        LOGGER.info("End");
    }

    private static void testGetDepartment() {
        LOGGER.info("Start");
        Department dept = departmentService.get(1);
        LOGGER.debug("Department:{}", dept);
        LOGGER.debug("Employees:{}", dept.getEmployeeList());
        LOGGER.info("End");
    }

    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
    }

    private static void testGetAverageSalary() {
        LOGGER.info("Start");
        double avg = employeeService.getAverageSalary();
        LOGGER.debug("Average salary: {}", avg);
        double avgDept = employeeService.getAverageSalaryByDepartment(1);
        LOGGER.debug("Average salary in dept 1: {}", avgDept);
        LOGGER.info("End");
    }

    private static void testGetAllEmployeesNative() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.debug("Native query employees:{}", employees);
        LOGGER.info("End");
    }
}
