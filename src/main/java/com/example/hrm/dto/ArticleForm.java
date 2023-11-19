package com.example.hrm.dto;

import com.example.hrm.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleForm {
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(null, title, content);
    }
}
