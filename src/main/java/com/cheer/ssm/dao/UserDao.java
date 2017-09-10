package com.cheer.ssm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cheer.ssm.domain.User;
@Service
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(final User user) {
		jdbcTemplate.execute("insert into tbl_user(user_name,credits,password) values(?,?,?)",
				new PreparedStatementCallback<User>() {
					@Override
					public User doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ps.setString(1, user.getUserName());
						ps.setInt(2, 0);
						ps.setString(3, user.getPassword());
						ps.execute();
						return null;
					}
				});
	}

	public void delete(final User user) {
		jdbcTemplate.execute("delete from tbl_user where id=?", new PreparedStatementCallback<Void>() {

			public Void doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setLong(1, user.getId());
				ps.execute();
				return null;
			}
		});
	}

	public void update(final User user) {
		jdbcTemplate.execute("update tbl_user set user_name=?,credits=?,password=?,last_login=?,last_ip=? where id=?",
				new PreparedStatementCallback<Void>() {
					public Void doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
						ps.setString(1, user.getUserName());
						ps.setInt(2, user.getCredite());
						ps.setString(3, user.getPassword());
						ps.setTimestamp(4, new Timestamp(user.getLastLogin().getTime()));
						ps.setString(5, user.getLastIp());
						ps.setLong(6, user.getId());
						ps.execute();
						return null;
					}
				});
	}

	public User findForUserName(String userName) {  //密码登录与数据库匹配
		List<User> list = jdbcTemplate.query("SELECT * FROM ssm.tbl_user where user_name = '"+userName+"'",
				new RowMapper<User>() {   //检查传入名字与传入的密码箱匹配

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user = new User();
				user.setId(rs.getLong(1));
				user.setUserName(rs.getString(2));
				user.setCredite(rs.getInt(3));
				user.setPassword(rs.getString(4));
				user.setLastLogin(rs.getTimestamp(5));
				user.setLastIp(rs.getString(6));
				return user;
			}

		});
		
		if(list!=null&& !list.isEmpty()) {
			return list.get(0);
		}
		
		return null;

	}

}
