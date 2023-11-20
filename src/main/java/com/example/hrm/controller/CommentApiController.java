package com.example.hrm.controller;

import com.example.hrm.domain.Comment;
import com.example.hrm.dto.CommentDto;
import com.example.hrm.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {
    private final CommentService commentService;

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        List<CommentDto> dtos = commentService.comments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long articleId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto createdDto = commentService.create(articleId, commentDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto) {
        CommentDto updatedDto = commentService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        CommentDto deleted = commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
