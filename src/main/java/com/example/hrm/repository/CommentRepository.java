package com.example.hrm.repository;

import com.example.hrm.domain.Article;
import com.example.hrm.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 모든 댓글 조회
    List<Comment> findByArticle(Article article);

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);

}
