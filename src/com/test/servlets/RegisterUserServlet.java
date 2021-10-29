package com.test.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.User;
import com.test.dao.UserRegisterDao;

/**
 * @author gary
 *
 */

@WebServlet("/registerUser")
public class RegisterUserServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		int age = Integer.parseInt(req.getParameter("age"));
		String activity = req.getParameter("activity");

		User user = new User(userName, password, firstName, lastName, age, activity);

		//
		UserRegisterDao userRegisterDao = new UserRegisterDao();
		int rows = userRegisterDao.registerUser(user);

		String infoMessage = "";
		if (rows == 0) {
			infoMessage = "Sorry, an error occurred!";
		} else {
			infoMessage = "User registered Successfully!";
		}

		//
		String page = getHtmlPage(req.getServletContext().getRealPath("html/register.html"), infoMessage);
		resp.getWriter().write(page);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = getHtmlPage(req.getServletContext().getRealPath("html/register.html"), "");
		resp.getWriter().write(page);
	}

	public String getHtmlPage(String filepath, String infoMessage) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
		String line = "";
		StringBuffer stringBuffer = new StringBuffer();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
		}
		String page = stringBuffer.toString();
		bufferedReader.close();
		page = MessageFormat.format(page, infoMessage);
		return page;
	}

}
