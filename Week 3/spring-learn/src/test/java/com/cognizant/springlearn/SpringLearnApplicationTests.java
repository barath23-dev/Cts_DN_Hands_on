package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.cognizant.springlearn.controller.CountryController;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class SpringLearnApplicationTests {

	@Autowired
	private CountryController countryController;

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {
		assertNotNull(countryController);
	}

	@Test
	public void testGetCountry() throws Exception {
		ResultActions actions = mvc.perform(get("/country"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.code").exists());
		actions.andExpect(jsonPath("$.code").value("IN"));
		actions.andExpect(jsonPath("$.name").exists());
		actions.andExpect(jsonPath("$.name").value("India"));
	}

	@Test
	public void testGetCountryException() throws Exception {
		ResultActions actions = mvc.perform(get("/countries/az"));
		actions.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateEmployeeException() throws Exception {
		String employeeJson = "{\"id\":99,\"name\":\"No One\",\"salary\":0.0,\"permanent\":true,\"dateOfBirth\":\"01/01/2000\",\"department\":{\"id\":1,\"name\":\"CSE\"},\"skills\":[]}";
		ResultActions actions = mvc.perform(put("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeeJson));
		actions.andExpect(status().isNotFound());
	}

	@Test
	public void testDeleteEmployeeException() throws Exception {
		ResultActions actions = mvc.perform(delete("/employees/99"));
		actions.andExpect(status().isNotFound());
	}

}
