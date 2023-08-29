package com.studyproject.webproject.controller;

import com.studyproject.webproject.entity.Article;
import com.studyproject.webproject.service.ArticleService;
import com.studyproject.webproject.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("")
    public List<Article> articles() {
        return articleService.allArticles();
    }

    @GetMapping("/userarticles")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getArticlesByUser(Principal principal) {
        var articles = articleService.getArticlesByUser(userInfoService, principal).stream().filter(Objects::nonNull).toList();
        return String.join("\n", articles.toString());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String createArticle(@RequestBody Article article, Principal principal) {
        articleService.saveArticle(article, userInfoService,principal);
        return "Article added!";
    }
}
