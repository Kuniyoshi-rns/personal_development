package com.example.pdWeb.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository implements ICompanyRepository{
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<Company> findAll() {
        return jdbcTemplate.query("select * from companies",new DataClassRowMapper<>(Company.class));
    }
}
