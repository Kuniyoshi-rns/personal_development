package com.example.pdWeb.Post;

import com.example.pdWeb.Company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository implements IPostRepository{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("select posts.id,title,body,users.name author, author_id from posts join users on posts.author_id=users.id order by created_at desc",new DataClassRowMapper<>(Post.class));
    }

    @Override
    public List<Post> findByTitleOrBody(String find) {
        var param = new MapSqlParameterSource();
        param.addValue("likeFind","%"+find+"%");
        return jdbcTemplate.query("select posts.id,title,body,users.name author, author_id from posts join users on posts.author_id=users.id where title like :likeFind or body like :likeFind order by created_at desc",param,new DataClassRowMapper<>(Post.class));
    }

    @Override
    public Post findById(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        var list =  jdbcTemplate.query("select posts.id,title,body,users.name author,author_id from posts join users on posts.author_id=users.id where posts.id = :id",param,new DataClassRowMapper<>(Post.class));
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public void insert(PostForm postForm, int id) {
        var param = new MapSqlParameterSource();
        param.addValue("author_id",id);
        param.addValue("title",postForm.getTitle());
        param.addValue("body",postForm.getBody());
        jdbcTemplate.update("insert into posts(title,body,author_id,created_at,updated_at) values(:title,:body,:author_id,now(),now())",param);
    }

    @Override
    public List<Post> findMyAll(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        return jdbcTemplate.query("select posts.id,title,body,users.name author, author_id from posts join users on posts.author_id=users.id where author_id = :id",param,new DataClassRowMapper<>(Post.class));
    }

    @Override
    public void update(int id, PostForm postForm) {
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        param.addValue("title",postForm.getTitle());
        param.addValue("body",postForm.getBody());
        jdbcTemplate.update("update posts set title=:title, body=:body, updated_at=now() where id=:id",param);
    }

    @Override
    public void delete(int id) {
        var param = new MapSqlParameterSource();
        param.addValue("id",id);
        jdbcTemplate.update("delete from posts where id=:id",param);
    }
}
