package com.cts.news.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "us_id", "us_name", "us_password", "us_email" }) })
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="us_id")
	private int id;

	@NotNull(message = "User Name cannot be empty")
	@Size(min = 3, max = 20, message = "Name must be 3 to 20 characters")
	@Column(name="us_name")
	private String name;

	@NotNull(message = "Password cannot be empty")
	@Size(min = 3, max = 20, message = "Password must be 3 to 20 characters")
	@Column(name="us_password")
	private String password;

	@NotNull(message = "Email cannot be empty")
	@Size(min = 8, max = 50, message = "email must be 8 to 50 characters")
	@Column(name="us_email")
	private String email;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="us_ro_id")
	private Role role;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_favourite_article", joinColumns = { @JoinColumn(name = "fa_us_id") }, inverseJoinColumns = {
			@JoinColumn(name = "fa_ar_id") })
	private List<Article> favouriteArticle;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="us_la_id")
	private Language language;
	
	@Column(name="us_blacklisted")
	private boolean blacklisted;
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<Article> getFavouriteArticle() {
		return favouriteArticle;
	}
	public void setFavouriteArticle(List<Article> favouriteArticle) {
		this.favouriteArticle = favouriteArticle;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public boolean isBlacklisted() {
		return blacklisted;
	}
	public void setBlacklisted(boolean blacklisted) {
		this.blacklisted = blacklisted;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", role=" + role
				+ ", favouriteArticle=" + favouriteArticle + ", language=" + language + ", blacklisted=" + blacklisted
				+ "]";
	}
	
} 
