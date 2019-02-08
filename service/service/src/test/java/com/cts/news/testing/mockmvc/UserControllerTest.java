package com.cts.news.testing.mockmvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	
	
	
	@Test
	public void testUserDetailsSaveSuccessfully() throws Exception {

		String EMPLOYEE_REQUEST = "  {\r\n" + 
				"  \r\n" + 
				"        \"name\": \"soumya_soumya1\",\r\n" + 
				"        \"password\": \"1234\",\r\n" + 
				"        \"email\" : \"soumya_soumya1@gmail.com\",\r\n" + 
				"        \"role\": {\r\n" + 
				"            \"id\": 2\r\n" + 
				"        },\r\n" + 
				"        \"favouriteArticle\": null,\r\n" + 
				"        \"language\": {\r\n" + 
				"            \"id\": 1\r\n" + 
				"        },\r\n" + 
				"        \"blacklisted\": false\r\n" + 
				"    }";

		mockMvc.perform(post("/user/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());

	}

	@Test
	public void testPreExistingEmailId() throws Exception {
		// exceptionRule.expect(NestedServletException.class);
		String EMPLOYEE_REQUEST = "    {\r\n" + 
				"        \"name\": \"abcd\",\r\n" + 
				"        \"password\": \"1234\",\r\n" + 
				"        \"email\" : \"soumya@gmail.com\",\r\n" + 
				"        \"role\": {\r\n" + 
				"            \"id\": 2\r\n" + 
				"        },\r\n" + 
				"        \"favouriteArticle\": null,\r\n" + 
				"        \"language\": {\r\n" + 
				"            \"id\": 1\r\n" + 
				"        },\r\n" + 
				"        \"blacklisted\": false\r\n" + 
				"    }";

		mockMvc.perform(post("/user/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());/* .andExpect(jsonPath("$.signupStatus").value("true")); */

	}

//	@Test
//	public void testWrongUrl() throws Exception {
//
//		String EMPLOYEE_REQUEST = "{\r\n" + 
//				"  \r\n" + 
//				"        \"name\": \"af\",\r\n" + 
//				"        \"password\": \"1234\",\r\n" + 
//				"        \"email\" : \"asdjhjf@gmail.com\",\r\n" + 
//				"        \"role\": {\r\n" + 
//				"            \"id\": 2\r\n" + 
//				"        },\r\n" + 
//				"        \"favouriteArticle\": null,\r\n" + 
//				"        \"language\": {\r\n" + 
//				"            \"id\": 1\r\n" + 
//				"        },\r\n" + 
//				"        \"blacklisted\": false\r\n" + 
//				"    }";
//
//		mockMvc.perform(post("/user/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
//				.andExpect(status().is4xxClientError());
//
//	}
	
	@Test
	public void testUserFavouriteArticleAddition() throws Exception {

		String EMPLOYEE_REQUEST = "{\r\n" + 
				"    \"email\": \"sarbo@gmail.com\",\r\n" + 
				"    \"favouriteArticle\": [\r\n" + 
				"            {\r\n" + 
				"                \r\n" + 
				"                \"title\": \"backend testing\",\r\n" + 
				"                \"description\": \"end to end\",\r\n" + 
				"                \"language\": {\r\n" + 
				"                    \"id\": 3\r\n" + 
				"                }\r\n" + 
				"            }\r\n" + 
				"        ]\r\n" + 
				"}";

		mockMvc.perform(post("/user/saveArticleList").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
		
	}
	
	@Test
	public void testUserFavouriteArticleDeletion() throws Exception {

		String EMPLOYEE_REQUEST = "{\r\n" + 
				"    \"email\": \"sarbo@gmail.com\",\r\n" + 
				"    \"favouriteArticle\": [{\r\n" + 
				"                \"title\": \"backend testing\"\r\n" + 
				"}]}";

		mockMvc.perform(post("/user/deleteArticle").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
		

	}
	
	@Test
	public void testAdminBlacklistAUser() throws Exception {
		mockMvc.perform(get("/user/blacklist/2").contentType("application/json;charset=UTF-8")).andExpect( status().is2xxSuccessful() );
	}

	@Test
	public void testPasswordValidation() throws Exception {

		String EMPLOYEE_REQUEST = "    {\r\n" + 
				"   \"id\" : null,\r\n" + 
				"        \"name\": \"asdf\",\r\n" + 
				"        \"password\": \"1\",\r\n" + 
				"        \"email\" : \"aaaasdf@gmail.com\",\r\n" + 
				"        \"role\": {\r\n" + 
				"            \"id\": 2\r\n" + 
				"        },\r\n" + 
				"        \"favouriteArticle\": null,\r\n" + 
				"        \"language\": {\r\n" + 
				"            \"id\": 1\r\n" + 
				"        },\r\n" + 
				"        \"blacklisted\": false\r\n" + 
				"    }";

		mockMvc.perform(post("/user/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Password must be 3 to 20 characters"));
	}

	@Test
	public void testUserNameValidation() throws Exception {

		String EMPLOYEE_REQUEST = "    {\r\n" + 
				"   \"id\" : null,\r\n" + 
				"        \"name\": \"a\",\r\n" + 
				"        \"password\": \"1234\",\r\n" + 
				"        \"email\" : \"aaaaasdf@gmail.com\",\r\n" + 
				"        \"role\": {\r\n" + 
				"            \"id\": 2\r\n" + 
				"        },\r\n" + 
				"        \"favouriteArticle\": null,\r\n" + 
				"        \"language\": {\r\n" + 
				"            \"id\": 1\r\n" + 
				"        },\r\n" + 
				"        \"blacklisted\": false\r\n" + 
				"    }";

		mockMvc.perform(post("/user/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Name must be 3 to 20 characters"));
		
	}

	@Test
	public void testNullUserName() throws Exception {

		String EMPLOYEE_REQUEST = "    {\r\n" + 
				"   \"id\" : null,\r\n" + 
				
				"        \"password\": \"1234\",\r\n" + 
				"        \"email\" : \"aasdfsasdf@gmail.com\",\r\n" + 
				"        \"role\": {\r\n" + 
				"            \"id\": 2\r\n" + 
				"        },\r\n" + 
				"        \"favouriteArticle\": null,\r\n" + 
				"        \"language\": {\r\n" + 
				"            \"id\": 1\r\n" + 
				"        },\r\n" + 
				"        \"blacklisted\": false\r\n" + 
				"    }";

		mockMvc.perform(post("/user/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: User Name cannot be empty"));

	}

	@Test
	public void testNullEmailAddress() throws Exception {

		String EMPLOYEE_REQUEST = "    {\r\n" + 
				"   \"id\" : null,\r\n" + 
				"        \"name\": \"asdf\",\r\n" + 
				"        \"password\": \"1234\",\r\n" + 
				
				"        \"role\": {\r\n" + 
				"            \"id\": 2\r\n" + 
				"        },\r\n" + 
				"        \"favouriteArticle\": null,\r\n" + 
				"        \"language\": {\r\n" + 
				"            \"id\": 1\r\n" + 
				"        },\r\n" + 
				"        \"blacklisted\": false\r\n" + 
				"    }";

		mockMvc.perform(post("/user/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Email cannot be empty"));

	}

	@Test
	public void testNullPassword() throws Exception {

		String EMPLOYEE_REQUEST = "    {\r\n" + 
				"   \"id\" : null,\r\n" + 
				"        \"name\": \"asdf\",\r\n" + 
				
				"        \"email\" : \"asdfgreasdf@gmail.com\",\r\n" + 
				"        \"role\": {\r\n" + 
				"            \"id\": 2\r\n" + 
				"        },\r\n" + 
				"        \"favouriteArticle\": null,\r\n" + 
				"        \"language\": {\r\n" + 
				"            \"id\": 1\r\n" + 
				"        },\r\n" + 
				"        \"blacklisted\": false\r\n" + 
				"    }";

		mockMvc.perform(post("/user/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: Password cannot be empty"));
	}

	@Test
	public void testShortEmailAddress() throws Exception {

		String EMPLOYEE_REQUEST = "    {\r\n" + 
				"   \"id\" : null,\r\n" + 
				"        \"name\": \"asdf\",\r\n" + 
				"        \"password\": \"1234\",\r\n" + 
				"        \"email\" : \"a\",\r\n" + 
				"        \"role\": {\r\n" + 
				"            \"id\": 2\r\n" + 
				"        },\r\n" + 
				"        \"favouriteArticle\": null,\r\n" + 
				"        \"language\": {\r\n" + 
				"            \"id\": 1\r\n" + 
				"        },\r\n" + 
				"        \"blacklisted\": false\r\n" + 
				"    }";

		mockMvc.perform(post("/user/save").content(EMPLOYEE_REQUEST).contentType("application/json;charset=UTF-8"))
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.errorMessage").value("Input validation failed: email must be 8 to 50 characters"));

	}
	
}