package com.example.hrm.controller;

import com.example.hrm.domain.Article;
import com.example.hrm.dto.ArticleForm;
import com.example.hrm.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
        // 1. Dto를 Entity 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB로 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = {}", id);

        Article article = articleRepository.findById(id)
                .orElse(null);

        model.addAttribute("article", article);

        return "articles/show";
    }

    @GetMapping("")
    public String list(Model model) {
        List<Article> all = articleRepository.findAll();
        model.addAttribute("list", all);
        return "articles/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);
        return "articles/edit";
    }
    @Transactional
    @PostMapping("/update")
    public String update(ArticleForm form) {
        log.info("form = {}", form);

        Article articleEntity = form.toEntity();
        log.info("entity = {}", articleEntity.toString());

        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        log.info("target = {}", target);

        if(target != null) {
            target.setTitle(form.getTitle());
            target.setContent(form.getContent());
        }
        return "redirect:/articles/" + articleEntity.getId();
    }
}
