package com.cts.news.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.news.bean.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{
	public Article findByUrl(String url);
}
