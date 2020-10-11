package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from user", Integer.class);
    }

    @Override
    public int save(User user) {
        return jdbcTemplate.update(
                "insert into user (username, password, rule) values(?,?,?)",
                user.getUsername(), user.getPassword(), user.getRule());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(
                "update user set username = ?, password = ?, rule =? where id = ?",
                user.getUsername(), user.getPassword(), user.getRule(), user.getId());
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update(
                "delete user where id = ?",
                id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "select * from user",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("rule")
                        );
                    }
                }
        );
//        return jdbcTemplate.query(
//                "select * from user",
//                (rs, rowNum) ->
//                        new User(
//                                rs.getInt("id"),
//                                rs.getString("username"),
//                                rs.getString("password"),
//                                rs.getString("rule")
//                        )
//        );
    }

    @Override
    public User findUserByUsername(String username) {
        return jdbcTemplate.queryForObject(
                "select * from user where username = ?",
                new Object[]{username},
                (rs, rowNum) ->
                        new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("rule")
                        )
        );
    }

    @Override
    public User findUserById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from user where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("rule")
                        )
        );
    }
}
