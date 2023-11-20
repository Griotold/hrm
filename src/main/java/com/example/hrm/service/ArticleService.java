package com.example.hrm.service;

import com.example.hrm.domain.Article;
import com.example.hrm.dto.ArticleForm;
import com.example.hrm.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article findOne(Long id) {
        return articleRepository.findById(id).orElse(null);
    }
    @Transactional
    public Article create(ArticleForm form) {
        Article entity = form.toEntity();
        return articleRepository.save(entity);
    }
    @Transactional
    public Article update(Long id, ArticleForm form) {
        Article entity = form.toEntity();
        log.info("id : {}, article : {}", id, entity);

        Article target = articleRepository.findById(id).orElse(null);

        if (target == null || id != entity.getId()) {
            log.info("id : {}, article : {}", id, entity);
            return null;
        }

        target.setTitle(entity.getTitle());
        target.setContent(entity.getContent());
        return target;
    }

    @Transactional
    public boolean delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null) {
            return false;
        }

        articleRepository.delete(target);

        return true;
    }
}
