package com.cts.news.dao;

import javax.persistence.EntityManagerFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.news.bean.User;

@Component
public class UserDao {
	private SessionFactory hibernateFactory;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

	@Autowired
	public void setSessionFactory(EntityManagerFactory emFactory) {
		this.hibernateFactory = emFactory.unwrap(SessionFactory.class);
	}

	public void save(User user) {
		LOGGER.info("UserDao SAVE method : START ");
		try {
			Session session = hibernateFactory.openSession();
			session.save(user);
			session.close();
			LOGGER.info("\n\n Details Added \n");
		} catch (HibernateException e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
			LOGGER.info("error");
		}
		LOGGER.info("UserDao SAVE method : END ");
	}
}
