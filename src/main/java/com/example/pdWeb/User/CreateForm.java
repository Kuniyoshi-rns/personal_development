package com.example.pdWeb.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateForm {
    @NotBlank(message = "ログインIDは必須です")
    private String loginId;

    @NotBlank(message = "ユーザー名は必須です")
    private String userName;

    private String companyId;

    @NotBlank(message = "パスワードは必須です")
    private String password;

    private String subPassword; //確認用
}
