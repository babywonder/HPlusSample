package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.test.beans.User;

public class UserRegisterDao {

	public int registerUser(User user) {

		Connection connection = null;
		PreparedStatement statement = null;
		String sqlString = "insert into users values(?,?,?,?,?,?);";
		int rowsAffect = 0;

		try {
			connection = DBConnection.getConnection();
			statement = connection.prepareStatement(sqlString);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getUserName());

			rowsAffect = statement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return rowsAffect;

	}
}
