package com.example.pdWeb.Post;

import java.util.List;

public interface IPostService {
    List<Post> findAll();

    List<Post> findByTitleOrBody(String find);

    Post findById(int id);

    void insert(PostForm postForm,int id);

    List<Post> findMyAll(int id);

    void update(int id,PostForm postForm);

    void delete(int id);
}
