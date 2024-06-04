package com.example.pdWeb.User;

public interface IUserService {
    User findByUser(LoginForm loginForm);
    void insert (CreateForm createForm);
}
