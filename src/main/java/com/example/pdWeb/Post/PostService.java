package com.example.pdWeb.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService{
    @Autowired
    IPostRepository postRepository;
    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findByTitleOrBody(String find) {
        return postRepository.findByTitleOrBody(find);
    }

    @Override
    public Post findById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public void insert(PostForm postForm, int id) {
        postRepository.insert(postForm,id);
    }

    @Override
    public List<Post> findMyAll(int id) {
        return postRepository.findMyAll(id);
    }

    @Override
    public void update(int id, PostForm postForm) {
        postRepository.update(id, postForm);
    }

    @Override
    public void delete(int id) {
        postRepository.delete(id);
    }
}
