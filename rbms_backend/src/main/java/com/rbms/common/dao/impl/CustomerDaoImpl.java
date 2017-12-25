package com.rbms.common.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rbms.common.connection.ConnectionToDatabase;
import com.rbms.common.vo.CustomerVo;

import oracle.jdbc.OracleTypes;

public class CustomerDaoImpl {
	public JSONObject getAllCustomers()
	{
		Connection con;
		JSONObject json = new JSONObject();
		try {
			con = ConnectionToDatabase.makeConnection();

			CallableStatement cs = con.prepareCall("begin ? := project2.show_Customers(); end;");

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
						+ "\t" + rs.getString(5));
				
				JSONObject item = new JSONObject();
				item.put("cid", rs.getString(1));
				item.put("name", rs.getString(2));
				item.put("telephone", rs.getString(3));
				item.put("visits_made", rs.getString(4));
				item.put("last_visit_made", rs.getString(5));
				array.put(item);
			}

			json.put("allCustomers", array);
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
	
	public String addCustomer(CustomerVo customer) {
		String customerAdded = null;
		Connection con;
		
		try {
			con = ConnectionToDatabase.makeConnection();
			CallableStatement cs = con.prepareCall("begin project2.add_customer(:1, :2, :3); end;");
			cs.setString(1, customer.getCid());
			cs.setString(2, customer.getName());
			cs.setString(3, customer.getTelephone());
	        
	        //execute the stored procedure
	        int success = cs.executeUpdate();
	        if(success >= 0)
	        	customerAdded = "Customer Added Successfully";
	        
	        cs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			/*if(e.getErrorCode() == 20501)
				customerAdded = "Customer Already Exists";
			else */
				customerAdded = e.getMessage();
			e.printStackTrace();
		
		}
		return customerAdded;
	}
}
