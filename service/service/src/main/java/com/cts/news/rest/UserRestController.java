package com.cts.news.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.news.bean.ArticleStatus;
import com.cts.news.bean.AuthenticationStatus;
import com.cts.news.bean.Language;
import com.cts.news.bean.SignupStatus;
import com.cts.news.bean.User;
import com.cts.news.service.UserService;


@RestController
@RequestMapping("/user")
public class UserRestController  extends ExceptionController{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public SignupStatus registerUser(@RequestBody User user) {
		LOGGER.info("UserController (registerUser): START ");
		SignupStatus signupStatus=userService.saveUser(user);
		LOGGER.info("UserController (registerUser): END ");
		return signupStatus;
	}
	
	@GetMapping("/user/getAll")
	public List<User> getAllUser(){
		LOGGER.info("UserController (getAllUser): START ");
		List<User> users = userService.getAllUser();
		LOGGER.info("UserController (getAllUser): END ");
		return users;
	}
	
	@PostMapping("/getOneUser")
	public User getOneUserByEmail(@RequestBody User user){
		LOGGER.info("UserController (getOneUserByEmail): START ");
		user = userService.findByEmail(user.getEmail());
		LOGGER.info("UserController (getOneUserByEmail): END ");
		return user;
	}
	
	@GetMapping("/language/getAll")
	public List<Language> getAllLanguage(){
		LOGGER.info("UserController (getAllLanguage): START ");
		List<Language> languages = userService.getAllLanguage();
		LOGGER.info("UserController (getAllLanguage): END ");
		return languages;
	}
	
	@GetMapping("/language/{id}")
	public Language getLanguageById(@PathVariable int id){
		LOGGER.info("UserController (getLanguageById): START ");
		Language language = userService.getLanguageDeatilsById(id);
		LOGGER.info("UserController (getLanguageById): END ");
		return language;
	}
	
	@GetMapping("/blacklist/{userId}")
	public User blacklistAUser(@PathVariable int userId) {
		LOGGER.info("UserController (blacklistAUser): START ");
		User user = userService.getOneUser(userId);
		user = userService.blacklistAUser(user);
		LOGGER.info("UserController (blacklistAUser): END ");
		return user;
	}
	
	@PostMapping("/saveArticleList")
	public ArticleStatus setArticleListForOneUser(@RequestBody User user) {
		LOGGER.info("UserController (setArticleListForOneUser): START ");
		ArticleStatus articleStatus = userService.setArticleListForOneUser(user);
		LOGGER.info("UserController (setArticleListForOneUser): END ");
		return articleStatus;
	}
	
	@PostMapping("/deleteArticle")
	public ArticleStatus deleteArticleForOneUser(@RequestBody User user) {
		LOGGER.info("UserController (deleteArticleForOneUser): START ");
		ArticleStatus status = userService.deleteArticleForOneUser(user);
		LOGGER.info("UserController (deleteArticleForOneUser): END ");
		return status;
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationStatus> authenticateValidUser(@RequestBody User user) {
		LOGGER.info("UserController (authenticateValidUser): START ");
		
		LOGGER.debug("From request (user) : {}", user);
		LOGGER.debug("From USerName (user) : {}", user.getEmail());
		String userEmail = user.getEmail();
		String incomingPassword = user.getPassword();
		LOGGER.debug("Value of IncomingPassword: {} ", incomingPassword);
		
		//String actualUserEmail = "";
		AuthenticationStatus status = new AuthenticationStatus();
		status.setAuthenticated(false);
		status.setAdmin(false);
		user = userService.findByEmail(userEmail);

		LOGGER.debug("From request (actualUser) : {}", user);
		String actualPassword = user.getPassword();
		if (user != null && (actualPassword.equals(incomingPassword)) ) {
			
			if (user.getRole().getId() == 1) {
				status.setAdmin(true);
				//actualUserEmail = actualUser.getEmail();
				status.setUser(user);
				status.setAuthenticated(true);
			} else {
				status.setAdmin(false);
				//actualUserEmail = actualUser.getEmail();
				status.setUser(user);
				status.setAuthenticated(true);
			}
		}
		LOGGER.debug("Value of actualPassword: {} ", actualPassword);
		LOGGER.debug("Value of status: {} ", status);
		
		LOGGER.info("UserController (authenticateValidUser): END ");
		return new ResponseEntity<AuthenticationStatus>(status, HttpStatus.OK);
	}
	/*@PostMapping("/checkArticle")
	public boolean checkArticleForOneUser(@RequestBody User user) {
		LOGGER.info("UserController (checkArticleForOneUser): START ");
		boolean isArticlePresent=userService.checkArticleForOneUser(user);
		LOGGER.info("UserController (checkArticleForOneUser): END ");
		return isArticlePresent;
	}*/
	
}
