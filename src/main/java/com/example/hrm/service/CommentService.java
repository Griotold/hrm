package com.example.hrm.service;

import com.example.hrm.domain.Article;
import com.example.hrm.domain.Comment;
import com.example.hrm.dto.CommentDto;
import com.example.hrm.repository.ArticleRepository;
import com.example.hrm.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        List<Comment> comments = commentRepository.findByArticle(article);

        return comments.stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없음"));

        Comment entity = Comment.createComment(dto, article);

        Comment created = commentRepository.save(entity);

        return CommentDto.createCommentDto(created);

    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없음"));

        target.patch(dto);

        return CommentDto.createCommentDto(target);
    }

    @Transactional
    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 없음"));

        commentRepository.delete(target);

        return CommentDto.createCommentDto(target);
    }
}
