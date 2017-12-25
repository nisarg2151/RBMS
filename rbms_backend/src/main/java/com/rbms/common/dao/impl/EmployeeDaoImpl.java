package com.rbms.common.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rbms.common.connection.ConnectionToDatabase;
import com.rbms.common.vo.EmployeeVo;

import oracle.jdbc.OracleTypes;

public class EmployeeDaoImpl {
	public JSONObject getAllEmployees() {
		Connection con;
		JSONObject json = new JSONObject();
		try {
			con = ConnectionToDatabase.makeConnection();

			CallableStatement cs = con.prepareCall("begin ? := project2.show_Employees(); end;");

			// register the out parameter (the first parameter)
			cs.registerOutParameter(1, OracleTypes.CURSOR);

			// execute and retrieve the result set
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);

			// make json
			JSONArray array = new JSONArray();

			// print the results
			while (rs.next()) {
				System.out.println(
						rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));

				JSONObject item = new JSONObject();
				item.put("eid", rs.getString(1));
				item.put("name", rs.getString(2));
				item.put("telephone", rs.getString(3));
				item.put("email", rs.getString(4));
				array.put(item);
			}

			json.put("allEmployees", array);
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

	public JSONObject getMonthlySaleActivity(EmployeeVo employee) {
		Connection con;
		JSONObject json = new JSONObject();
		try {
			con = ConnectionToDatabase.makeConnection();

			CallableStatement cs = con.prepareCall("begin project2.monthly_sale_activities(:1, :2); end;");
			cs.setString(1, employee.getEID());
			cs.registerOutParameter(2, OracleTypes.CURSOR);

			// execute and retrieve the result set
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(2);

			// make json
			JSONArray array = new JSONArray();

			// print the results
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
						+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7));

				JSONObject item = new JSONObject();
				item.put("eid", rs.getString(1));
				item.put("name", rs.getString(4));
				item.put("month", rs.getString(2));
				item.put("year", rs.getString(3));
				item.put("#sales", rs.getString(5));
				item.put("quantity_sold", rs.getString(6));
				item.put("amount_sold", rs.getString(7));
				array.put(item);
			}

			json.put("salesPerEmployee", array);
			cs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			/*if(e.getErrorCode() == 20506)
				json.put("salesPerEmployee", "Employee id does not exist.");
			else*/
				json.put("salesPerEmployee", e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
}
