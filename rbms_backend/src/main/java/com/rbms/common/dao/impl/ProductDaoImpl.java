package com.rbms.common.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rbms.common.connection.ConnectionToDatabase;

import oracle.jdbc.OracleTypes;

public class ProductDaoImpl {

	public JSONObject getAllProducts()
	{
		Connection con;
		JSONObject json = new JSONObject();
		try {
			con = ConnectionToDatabase.makeConnection();

			CallableStatement cs = con.prepareCall("begin ? := project2.show_Products(); end;");

			// register the out parameter (the first parameter)
			cs.registerOutParameter(1, OracleTypes.CURSOR);

			// execute and retrieve the result set
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);

			//make json
			JSONArray array = new JSONArray();
			
			// print the results
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
						+ "\t" + rs.getDouble(5) + "\t" + rs.getString(6));
				
				JSONObject item = new JSONObject();
				item.put("pid", rs.getString(1));
				item.put("name", rs.getString(2));
				item.put("qoh", rs.getString(3));
				item.put("qoh_threshold", rs.getString(4));
				item.put("original_price", rs.getString(5));
				item.put("disct_cat", rs.getString(6));
				array.put(item);
			}

			json.put("allProducts", array);
			cs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
}
