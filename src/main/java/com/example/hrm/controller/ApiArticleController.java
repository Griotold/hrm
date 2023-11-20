package com.example.hrm.controller;

import com.example.hrm.domain.Article;
import com.example.hrm.dto.ArticleForm;
import com.example.hrm.repository.ArticleRepository;
import com.example.hrm.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article findOne(@PathVariable Long id) {
        return articleService.findOne(id);
    }

    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm form) {
        return articleService.create(form);
    }
    @Transactional
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm form) {

        Article article = articleService.update(id, form);
        if(article == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(article);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        boolean isDeleted = articleService.delete(id);
        if(!isDeleted) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제할 항목을 찾을 수 없습니다.");
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");

    }

}
