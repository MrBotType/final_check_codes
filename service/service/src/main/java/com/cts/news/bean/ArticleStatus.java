package com.cts.news.bean;

public class ArticleStatus {
	private boolean userFavourite;
	private boolean articleDeleted;
	private String messege;
	public ArticleStatus() {
		super();
	}
	public boolean isUserFavourite() {
		return userFavourite;
	}
	public void setUserFavourite(boolean userFavourite) {
		this.userFavourite = userFavourite;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	public boolean isArticleDeleted() {
		return articleDeleted;
	}
	public void setArticleDeleted(boolean articleDeleted) {
		this.articleDeleted = articleDeleted;
	}
	@Override
	public String toString() {
		return "ArticleStatus [userFavourite=" + userFavourite + ", articleDeleted=" + articleDeleted + ", messege="
				+ messege + "]";
	}
	
	
}
