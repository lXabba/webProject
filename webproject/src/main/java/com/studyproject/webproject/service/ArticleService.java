package com.studyproject.webproject.service;

import com.studyproject.webproject.entity.Article;
import com.studyproject.webproject.entity.UserInfo;
import com.studyproject.webproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> allArticles()
    {
        return articleRepository.findAll();
    }

    public void saveArticle(Article article, UserInfoService userInfoService, Principal principal) {
        UserInfo userInfo = userInfoService.getCurrentUserInfo(principal);
        article.setUser(userInfo);
        articleRepository.save(article);
    }

    public List<Article> getArticlesByUser(UserInfoService userInfoService, Principal principal) {
        return articleRepository.findByUser(userInfoService.getCurrentUserInfo(principal));
    }
}
