package com.cts.news.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.news.bean.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer>{

}
