package com.test.servlets;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Product;
import com.test.dao.ApplicationDAO;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchString = req.getParameter("search");

		//
		ApplicationDAO applicationDAO = new ApplicationDAO();

		List<Product> productList = applicationDAO.searchProducts(searchString);

		//

		String pageString = getHTMLString(req.getServletContext().getRealPath("/html/search.html"), productList);
		resp.getWriter().write(pageString);
	}

	public String getHTMLString(String filePath, List<Product> productList) throws IOException {
		System.out.println(filePath);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
		String lineString = "";
		StringBuffer buffer = new StringBuffer();
		while ((lineString = bufferedReader.readLine()) != null) {
			buffer.append(lineString);
		}

		bufferedReader.close();
		String pageString = buffer.toString();

		pageString = MessageFormat.format(pageString, productList.get(0).getImagePath(),
				productList.get(1).getImagePath(), productList.get(2).getImagePath(),
				productList.get(0).getProductName(), productList.get(1).getProductName(),
				productList.get(2).getProductName(), 0);

		return pageString;

	}
}
