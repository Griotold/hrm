package com.example.hrm.controller;

import com.example.hrm.domain.Article;
import com.example.hrm.dto.ArticleForm;
import com.example.hrm.repository.ArticleRepository;
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

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article findOne(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm form) {
        Article entity = form.toEntity();
        return articleRepository.save(entity);
    }
    @Transactional
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm form) {
        Article entity = form.toEntity();
        log.info("id : {}, article : {}", id, entity);

        Article target = articleRepository.findById(id).orElse(null);

        if (target == null || id != entity.getId()) {
            log.info("id : {}, article : {}", id, entity);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        target.setTitle(entity.getTitle());
        target.setContent(entity.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(target);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제할 항목을 찾을 수 없습니다.");
        }

        articleRepository.delete(target);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
