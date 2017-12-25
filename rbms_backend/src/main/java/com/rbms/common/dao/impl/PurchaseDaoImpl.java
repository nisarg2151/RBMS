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
import com.rbms.common.vo.PurchaseVo;

import oracle.jdbc.OracleTypes;

public class PurchaseDaoImpl {
	public JSONObject getAllPurchases()
	{
		Connection con;
		JSONObject json = new JSONObject();
		try {
			con = ConnectionToDatabase.makeConnection();

			CallableStatement cs = con.prepareCall("begin ? := project2.show_Purchases(); end;");

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
						+ "\t" + rs.getInt(5) + "\t" + rs.getString(6) + "\t" + rs.getDouble(7));
				
				JSONObject item = new JSONObject();
				item.put("pur", rs.getString(1));
				item.put("eid", rs.getString(2));
				item.put("pid", rs.getString(3));
				item.put("cid", rs.getString(4));
				item.put("qty", rs.getInt(5));
				item.put("ptime", rs.getString(6));
				item.put("total_price", rs.getDouble(7));
				array.put(item);
			}

			json.put("allPurchases", array);
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
	
	public JSONObject getPurchaseSaving(PurchaseVo purchase) {
		
		Connection con;
		JSONObject json = new JSONObject();
		try {
			con = ConnectionToDatabase.makeConnection();
			CallableStatement cs = con.prepareCall("{? = call project2.purchase_saving(?)}");
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.setInt(2, Integer.parseInt(purchase.getPur()));
			
	        //execute the stored procedure
			cs.execute();
			BigDecimal saving = (BigDecimal) cs.getObject(1);
			
			// print the results
			System.out.println(saving);
				
			json.put("saving", saving);

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
	
	public String addPurchase(PurchaseVo purchase) {
		String purchaseAdded = null;
		Connection con;
		
		try {
			con = ConnectionToDatabase.makeConnection();
			CallableStatement cs = con.prepareCall("begin project2.add_purchases(:1, :2, :3, :4); end;");
			cs.setString(1, purchase.getEid());
			cs.setString(2, purchase.getPid());
			cs.setString(3, purchase.getCid());
			cs.setInt(4, purchase.getQuantity());
	        
	        //execute the stored procedure
	        int success = cs.executeUpdate();
	        if(success >= 0)
	        	purchaseAdded = "Purchase Added Successfully";
	        
	        cs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			/*if(e.getErrorCode() == 20503)
				purchaseAdded = "Insufficient Stock";
			else if(e.getErrorCode() == 20507)
				purchaseAdded = "PID does not exist";
			else if(e.getErrorCode() == 20508)
				purchaseAdded = "CID does not exist";
			else if(e.getErrorCode() == 20509)
				purchaseAdded = "EID does not exist";
			else*/
				purchaseAdded = e.getMessage();
			e.printStackTrace();
		}
		return purchaseAdded;
	}
	
	public String deletePurchase(PurchaseVo purchase) {
		String purchaseAdded = null;
		Connection con;
		
		try {
			con = ConnectionToDatabase.makeConnection();
			CallableStatement cs = con.prepareCall("begin project2.delete_purchase(:1); end;");
			cs.setString(1, purchase.getPur());
	        
	        //execute the stored procedure
	        int success = cs.executeUpdate();
	        if(success >= 0)
	        	purchaseAdded = "Purchase Deleted Successfully";
	        
	        cs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			/*if(e.getErrorCode() == 20505)
				purchaseAdded = "pur# does not exist.";
			else*/
				purchaseAdded = e.getMessage();
			e.printStackTrace();
		}
		return purchaseAdded;
	}
}
