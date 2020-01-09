package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

	private static final Long ID = 1L;
	private static final String EMAIL = "teste@teste.com";
	private static final String NAME = "User test";
	private static final String PASS = "1234567";
	private static final String URL = "/user";

	@MockBean
	UserService userService;

	@Autowired
	MockMvc mvc;

	@Test
	public void testSave() throws Exception {

		BDDMockito.given(this.userService.save(Mockito.any(User.class))).willReturn(getMockUser());

		mvc.perform(MockMvcRequestBuilders.post(URL).content(this.getJsonPayload(ID, NAME, PASS, EMAIL))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.email").value(EMAIL))
				.andExpect(jsonPath("$.data.name").value(NAME))
				.andExpect(jsonPath("$.data.password").doesNotExist())
				.andExpect(jsonPath("$.data.id").value(ID));
	}

	@Test
	public void testSaveInvalidUser() throws JsonProcessingException, Exception {
		BDDMockito.given(this.userService.save(Mockito.any(User.class))).willReturn(getMockUser());

		mvc.perform(MockMvcRequestBuilders.post(URL).content(this.getJsonPayload(ID, NAME, PASS, "emaill"))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("Email inválido!"));
	}

	public User getMockUser() {
		User user = new User();
		user.setId(ID);
		user.setEmail(EMAIL);
		user.setName(NAME);
		user.setPassword(PASS);

		return user;
	}

	public String getJsonPayload(Long id, String name, String pass, String email) throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setEmail(email);
		dto.setName(name);
		dto.setPassword(pass);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);

	}
}
