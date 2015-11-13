package com.spring_cookbook.dao;

import com.spring_cookbook.domain.Users;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	public void add(Users user) {
		String sql = "insert into users (first_name, age) values (?, ?)";
		jdbcTemplate.update(sql, user.getFirstName(), user.getAge());
	}
        
        
        public Users findById(Long id) {
		String sql = "select * from users where id=?";
		Users user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
		return user;
	}

	private class UserMapper implements RowMapper<Users> {
		public Users mapRow(ResultSet row, int rowNum) throws SQLException {
			Users user = new Users();

			user.setId(row.getLong("id"));
			user.setFirstName(row.getString("first_name"));
			user.setAge(row.getInt("age"));
			
			return user;
		}
	}
}

