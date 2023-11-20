package com.example.hrm;

import com.example.hrm.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class ArticleInitDb {

    private final ArticleInitService articleInitService;

    @PostConstruct
    public void init() {
        articleInitService.insertDummyData();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class ArticleInitService {

        private final EntityManager em;

        public void insertDummyData() {
            Article article1 = createArticle("가가가가", "1111");
            em.persist(article1);

            Article article2 = createArticle("나나나나", "2222");
            em.persist(article2);

            Article article3 = createArticle("다다다다", "3333");
            em.persist(article3);
        }

        private Article createArticle(String title, String content) {
            Article article = new Article();
            article.setTitle(title);
            article.setContent(content);
            return article;
        }
    }
}
