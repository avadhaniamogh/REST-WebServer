package org.example.rest.root.presenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.root.database.DatabaseHelper;
import org.example.root.model.User;

public class UserPresenter {

	public UserPresenter() {
		// TODO Auto-generated constructor stub
	}

	public List<User> retrieveAllUsers() {
		List<User> users = new ArrayList<>();
		DatabaseHelper helper = new DatabaseHelper();
		Connection connection = helper.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT id,firstname,lastname, age FROM user ORDER BY id ASC");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setAge(rs.getInt("age"));
				users.add(user);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean insertUser(User user) {
		boolean result = false;
		DatabaseHelper helper = new DatabaseHelper();
		Connection connection = helper.getConnection();
		String query = "INSERT INTO user (id, firstname, lastname, age) VALUES ('" 
				+ user.getId() + "','" + user.getFirstName() + "','" + user.getLastName() + "','" 
				+ user.getAge() + "');";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			int value = ps.executeUpdate();
			if(value > 0) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
