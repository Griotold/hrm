package com.example.hrm;

import com.example.hrm.domain.Article;
import com.example.hrm.domain.Comment;
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

            Article article4 = createArticle("당신의 인생 영화는?", "댓글 ㄱ");
            em.persist(article4);

            Article article5 = createArticle("당신의 소울 푸드는?", "댓글 ㄱㄱ");
            em.persist(article5);

            Article article6 = createArticle("당신의 취미는?", "댓글 ㄱㄱㄱ");
            em.persist(article6);

            Comment comment1 = createComment(article4, "Kim", "굿 윌 헌팅");
            Comment comment2 = createComment(article4, "Park", "아이 엠 샘");
            Comment comment3 = createComment(article4, "choi", "쇼생크 탈출");
            em.persist(comment1);
            em.persist(comment2);
            em.persist(comment3);

            Comment comment4 = createComment(article5, "Kim", "떡볶이");
            Comment comment5 = createComment(article5, "Park", "순대");
            Comment comment6 = createComment(article5, "choi", "어묵");
            em.persist(comment4);
            em.persist(comment5);
            em.persist(comment6);

            Comment comment7 = createComment(article6, "Kim", "독서");
            Comment comment8 = createComment(article6, "Park", "헬스");
            Comment comment9 = createComment(article6, "choi", "산책");
            em.persist(comment7);
            em.persist(comment8);
            em.persist(comment9);


        }

        private Article createArticle(String title, String content) {
            Article article = new Article();
            article.setTitle(title);
            article.setContent(content);
            return article;
        }

        private Comment createComment(Article article, String nickname, String body) {
            Comment comment = new Comment();
            comment.setArticle(article);
            comment.setNickname(nickname);
            comment.setBody(body);
            return comment;
        }
    }
}
