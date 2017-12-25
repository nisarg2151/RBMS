package com.rbms.common.dao.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rbms.common.connection.ConnectionToDatabase;

import oracle.jdbc.OracleTypes;

public class SupplyDaoImpl {
	public JSONObject getAllSuppies()
	{
		Connection con;
		JSONObject json = new JSONObject();
		try {
			con = ConnectionToDatabase.makeConnection();

			CallableStatement cs = con.prepareCall("begin ? := project2.show_Supplies(); end;");

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
						+ "\t" + rs.getDouble(5));
				
				JSONObject item = new JSONObject();
				item.put("sup", rs.getString(1));
				item.put("pid", rs.getString(2));
				item.put("sid", rs.getString(3));
				item.put("sdate", rs.getString(4));
				item.put("quantity", rs.getString(5));
				array.put(item);
			}

			json.put("allSupplies", array);
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
	
public JSONObject getCount() {
		
		Connection con;
		JSONObject json = new JSONObject();
		try {
			con = ConnectionToDatabase.makeConnection();
			CallableStatement cs = con.prepareCall("begin get_count(?,?,?,?); end;");
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.registerOutParameter(3, OracleTypes.NUMBER);
			cs.registerOutParameter(4, OracleTypes.NUMBER);
			
	        //execute the stored procedure
			cs.execute();
			BigDecimal c_count = (BigDecimal) cs.getObject(1);
			BigDecimal e_count = (BigDecimal) cs.getObject(2);
			BigDecimal s_count = (BigDecimal) cs.getObject(3);
			BigDecimal p_count = (BigDecimal) cs.getObject(4);
			
			// print the results
			System.out.println(c_count);
			System.out.println(e_count);
			System.out.println(s_count);
			System.out.println(p_count);
				
			json.put("customer", c_count);
			json.put("employee", e_count);
			json.put("supplier", s_count);
			json.put("purchase", p_count);

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
