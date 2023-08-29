package com.studyproject.webproject.repository;

import com.studyproject.webproject.entity.Article;
import com.studyproject.webproject.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByUser(UserInfo user);
}
