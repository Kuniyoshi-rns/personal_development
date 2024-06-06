package com.example.pdWeb.Post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostForm {
    @NotBlank(message = "タイトルは必須です")
    private String title;

    private String body;
}
