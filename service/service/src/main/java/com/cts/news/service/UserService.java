package com.cts.news.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.news.bean.Article;
import com.cts.news.bean.ArticleStatus;
import com.cts.news.bean.Language;
import com.cts.news.bean.Role;
import com.cts.news.bean.SignupStatus;
import com.cts.news.bean.User;
import com.cts.news.repo.ArticleRepository;
import com.cts.news.repo.LanguageRepository;
import com.cts.news.repo.RoleRepository;
import com.cts.news.repo.UserRepository;

@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	/*@Autowired
	private UserDao userDao;*/
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Transactional
	public SignupStatus saveUser(User user) {
		
		LOGGER.info("UserService (saveUser): START ");
		LOGGER.debug("Incoming User Details :" + user);
		Language language = languageRepository.getOne(user.getLanguage().getId());
		user.setLanguage(language);
		Role role = roleRepository.getOne(user.getRole().getId());
		user.setRole(role);
		SignupStatus signupStatus = new SignupStatus();
		if(userRepository.findByEmail(user.getEmail()) == null) {
			userRepository.save(user);
			signupStatus.setEmailExist(false);
			signupStatus.setSignupStatus(true);
			signupStatus.setMessage("Sign up successfull!");
		} else {
			LOGGER.info("Email Id already Exists. ");
			signupStatus.setEmailExist(true);
			signupStatus.setSignupStatus(false);
			signupStatus.setMessage("Sign up failed!");
		}
		
		LOGGER.info("UserService (saveUser) : END ");
		return signupStatus;
	}
	
	@Transactional
	public List<User> getAllUser() {
		LOGGER.info("UserService (getAllUser): START ");
		List<User> users = userRepository.findAll();
		LOGGER.info("UserService (getAllUser) : END ");
		return users;
	}
	
	@Transactional
	public List<Language> getAllLanguage() {
		LOGGER.info("UserService (getAllLanguage): START ");
		List<Language> languages = languageRepository.findAll();
		LOGGER.info("UserService (getAllLanguage) : END ");
		return languages;
	}
	
	@Transactional
	public User getOneUser(int userId) {
		LOGGER.info("UserService (getOneUser): START ");
		User user = userRepository.getOne(userId);
		LOGGER.info("UserService (getOneUser) : END ");
		return user;
	}
	
	@Transactional
	public User blacklistAUser(User user) {
		LOGGER.info("UserService (blacklistAUser): START ");
		user = userRepository.getOne(user.getId());
		user.setBlacklisted(true);
		userRepository.save(user);
		LOGGER.info("UserService (blacklistAUser) : END ");
		return user;
	}
	
	@Transactional
	public ArticleStatus setArticleListForOneUser(User user) {
		LOGGER.info("UserService (setArticleListForOneUser): START ");
		boolean isExistInList=true;
		ArticleStatus articleStatus = new ArticleStatus();
		Article incomingArticle = user.getFavouriteArticle().get(0);
		user=userRepository.findByEmail(user.getEmail());
		List<Article> articles = user.getFavouriteArticle();
		
		incomingArticle.setLanguage(languageRepository.getOne(incomingArticle.getLanguage().getId()));
		if(articleRepository.findByUrl(incomingArticle.getUrl())  == null) {
		 	articleRepository.save(incomingArticle);
		}
		incomingArticle=articleRepository.findByUrl(incomingArticle.getUrl());
		for(Article a : articles) {
			if(a.getUrl().equals(incomingArticle.getUrl())) {
				articleStatus.setUserFavourite(true);
				articleStatus.setMessege("Already Exist As User Favourite Article.");
				
				isExistInList=false;
				break;
			}
		}
		if(isExistInList) {
			articles.add(incomingArticle);
			articleStatus.setUserFavourite(false);
			articleStatus.setMessege("Saved As User Favourite Article.");
		}
		articleStatus.setArticleDeleted(false);
		user.setFavouriteArticle(articles);
		LOGGER.debug("User details with favourite article :"+user);
		userRepository.save(user);
		LOGGER.info("UserService (setArticleListForOneUser) : END ");
		return articleStatus;
	}
	
	@Transactional
	public ArticleStatus deleteArticleForOneUser(User user) {
		LOGGER.info("UserService (deleteArticleForOneUser): START ");
		ArticleStatus status=new ArticleStatus();
		Article deleteArticle = articleRepository.findByUrl(user.getFavouriteArticle().get(0).getUrl());
		
		user=userRepository.findByEmail(user.getEmail());
		List<Article> previousArticles = user.getFavouriteArticle();
		
		previousArticles.remove(deleteArticle);
		status.setArticleDeleted(true);
		status.setMessege("Article deleted successfully");
		status.setUserFavourite(false);
		
		user.setFavouriteArticle(previousArticles);
		LOGGER.debug("User details with favourite article :"+user);
		userRepository.save(user);
		LOGGER.info("UserService (deleteArticleForOneUser) : END ");
		return status;
	}
	
	
	@Transactional
	public Language getLanguageDeatilsById(int id) {
		LOGGER.info("UserService (getLanguageDeatilsById): START ");
		Language language = languageRepository.getOne(id);
		LOGGER.debug("Language is : "+language);
		LOGGER.info("UserService (getLanguageDeatilsById) : END ");
		return language;
	}
	
	@Transactional
	public User findByEmail(String email) {
		LOGGER.info("UserService (findByEmail): START ");
		User user = userRepository.findByEmail(email);
		LOGGER.info("UserService (findByEmail): END ");
		return user;
	}
	
	
	/*@Transactional
	public boolean checkArticleForOneUser(User user) {
		LOGGER.info("UserService (checkArticleForOneUser): START ");
		boolean isArticlePresent=false;
		Article article = user.getFavouriteArticle().get(0);
		user=userRepository.findByEmail(user.getEmail());
		List<Article> articles = user.getFavouriteArticle();
		for(Article a : articles) {
			if(a.getUrl().equals(article.getUrl())) {
				isArticlePresent=true;
				break;
			}
		}
		LOGGER.debug(" Article is present : "+isArticlePresent);
		LOGGER.info("UserService (checkArticleForOneUser): END ");
		return isArticlePresent;
	}*/
}
