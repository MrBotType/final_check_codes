package com.cts.news.testing.mokito;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cts.news.bean.Language;
import com.cts.news.bean.Role;
import com.cts.news.bean.SignupStatus;
import com.cts.news.bean.User;
import com.cts.news.repo.ArticleRepository;
import com.cts.news.repo.LanguageRepository;
import com.cts.news.repo.RoleRepository;
import com.cts.news.repo.UserRepository;
import com.cts.news.service.UserService;



public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	@Mock
	private RoleRepository roleRepository;
	@Mock
	private LanguageRepository languageRepository;
	@Mock
	private ArticleRepository articleRepository;
	
	@InjectMocks
	private UserService userservice;
	
	
	
    @Before
	    public void setUp() throws Exception {
	         MockitoAnnotations.initMocks(this);
	    }
    
    @Test
    public void testSaveUserDetails() {    
    	User nullUser = null;
        User user = new User();
        Role role = new Role();
        Language language = new Language();
        user.setEmail("Soumyadeep123654789@cogni.com");
        user.setPassword("12345678");
        user.setName("Soumyadeep");
        user.setFavouriteArticle(null);
        user.setBlacklisted(false);
        role.setId(2);
        user.setRole(role);
        language.setId(7);
        user.setLanguage(language);
        Mockito.when(userRepository.findByEmail(user.getEmail()) ).thenReturn(nullUser);
        SignupStatus status=userservice.saveUser(user);
        assertEquals(true, status.isSignupStatus() && !status.isEmailExist());
               
    }
    
    @Test
    public void testDuplicateUserEmail() {
        User user = new User();
        Role role = new Role();
        Language language = new Language();
        user.setEmail("soumya@gmail.com");
        user.setPassword("12345678");
        user.setName("Soumyadeep");
        user.setFavouriteArticle(null);
        user.setBlacklisted(false);
        role.setId(2);
        user.setRole(role);
        language.setId(7);
        user.setLanguage(language);
        User existedUser = userRepository.findByEmail(user.getEmail());
        Mockito.when(userRepository.findByEmail(user.getEmail()) ).thenReturn(existedUser);
        SignupStatus status=userservice.saveUser(user);
        assertEquals(false, !status.isSignupStatus() && status.isEmailExist());
               
               
    }
}
