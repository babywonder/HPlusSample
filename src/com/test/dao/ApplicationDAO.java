package com.test.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.beans.Product;

public class ApplicationDAO {

	public List<Product> searchProducts(String searchString) {

		Product product = null;
		List<Product> listProducts = new ArrayList<>();


		try {
			Connection connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from products where product_name like '%"+searchString+"%'");
			while (resultSet.next()) {
				product = new Product();
//				product.setProductId(resultSet.getInt("product_id"));
//				product.setProductName(resultSet.getString("product_name"));
//				product.setImagePath(resultSet.getString("image_path"));
			
				product.setProductId(resultSet.getInt(1));
				product.setProductName(resultSet.getString(2));
				product.setImagePath(resultSet.getString(3));
				listProducts.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listProducts;
	}

}
