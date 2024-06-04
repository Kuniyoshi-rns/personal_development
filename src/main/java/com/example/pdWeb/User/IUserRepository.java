package com.example.pdWeb.User;

public interface IUserRepository {
    User findByUser(LoginForm loginForm);
    void insert(CreateForm createForm);
}
