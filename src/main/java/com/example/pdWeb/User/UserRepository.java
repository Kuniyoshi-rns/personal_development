package com.example.pdWeb.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public User findByUser(LoginForm loginForm) {
        var param = new MapSqlParameterSource();
        param.addValue("login_id",loginForm.getLoginId());
        param.addValue("pass",loginForm.getPassword());
        var list = jdbcTemplate.query("select users.id,login_id,password,users.name u_name,companies.name c_name from users join companies on users.company_id=companies.id where login_id = :login_id and password = :pass",param,new DataClassRowMapper<>(User.class));
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public void insert(CreateForm createForm) {
        var param = new MapSqlParameterSource();
        param.addValue("login_id",createForm.getLoginId());
        param.addValue("pass",createForm.getPassword());
        param.addValue("name",createForm.getUserName());
        param.addValue("company_id",Integer.parseInt(createForm.getCompanyId()));
        jdbcTemplate.update("insert into users(login_id,name,company_id,password) values(:login_id,:name,:company_id,:pass)",param);
    }
}
