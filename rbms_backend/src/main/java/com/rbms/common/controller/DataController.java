package com.rbms.common.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rbms.common.dao.impl.CustomerDaoImpl;
import com.rbms.common.dao.impl.DiscountDaoImpl;
import com.rbms.common.dao.impl.EmployeeDaoImpl;
import com.rbms.common.dao.impl.LogsDaoImpl;
import com.rbms.common.dao.impl.ProductDaoImpl;
import com.rbms.common.dao.impl.PurchaseDaoImpl;
import com.rbms.common.dao.impl.SupplierDaoImpl;
import com.rbms.common.dao.impl.SupplyDaoImpl;
import com.rbms.common.vo.CustomerVo;
import com.rbms.common.vo.EmployeeVo;
import com.rbms.common.vo.PurchaseVo;

@CrossOrigin
@RequestMapping("/rbms")
@Controller
public class DataController {

	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) {

		model.addAttribute("movie", name);
		return "list";

	}
	
	@RequestMapping(value="/customer/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllCustomers(ModelMap model) {

		CustomerDaoImpl allCustInfo = new CustomerDaoImpl();
		JSONObject json = allCustInfo.getAllCustomers();
		System.out.println(json.toString());
		model.addAttribute("allCustomers", json);
		return json.toString();
	}
	
	@RequestMapping(value="/product/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllProducts(ModelMap model) {

		ProductDaoImpl allProductInfo = new ProductDaoImpl();
		JSONObject json = allProductInfo.getAllProducts();
		System.out.println(json.toString());
		model.addAttribute("allProducts", json);
		return json.toString();
	}
	
	@RequestMapping(value="/purchase/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllPurchases(ModelMap model) {

		PurchaseDaoImpl allPurchaseInfo = new PurchaseDaoImpl();
		JSONObject json = allPurchaseInfo.getAllPurchases();
		System.out.println(json.toString());
		model.addAttribute("allPurchases", json);
		return json.toString();
	}
	
	@RequestMapping(value="/employee/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllEmployees(ModelMap model) {

		EmployeeDaoImpl allEmpInfo = new EmployeeDaoImpl();
		JSONObject json = allEmpInfo.getAllEmployees();
		System.out.println(json.toString());
		model.addAttribute("allEmployees", json);
		return json.toString();
	}
	
	@RequestMapping(value="/supplier/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllSuppliers(ModelMap model) {

		SupplierDaoImpl allSupplierInfo = new SupplierDaoImpl();
		JSONObject json = allSupplierInfo.getAllSuppiers();
		System.out.println(json.toString());
		model.addAttribute("allSuppliers", json);
		return json.toString();
	}
	
	@RequestMapping(value="/supply/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllSupplies(ModelMap model) {

		SupplyDaoImpl allSupplyInfo = new SupplyDaoImpl();
		JSONObject json = allSupplyInfo.getAllSuppies();
		System.out.println(json.toString());
		model.addAttribute("allSupplies", json);
		return json.toString();
	}
	
	@RequestMapping(value="/logs/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllLogs(ModelMap model) {

		LogsDaoImpl allLogsInfo = new LogsDaoImpl();
		JSONObject json = allLogsInfo.getAllLogs();
		System.out.println(json.toString());
		model.addAttribute("allLogs", json);
		return json.toString();
	}
	
	@RequestMapping(value="/discount/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllDiscounts(ModelMap model) {

		DiscountDaoImpl allDiscountInfo = new DiscountDaoImpl();
		JSONObject json = allDiscountInfo.getAllDiscounts();
		System.out.println(json.toString());
		model.addAttribute("allDiscounts", json);
		return json.toString();
	}
	
	@RequestMapping(value="/customer/add", method = RequestMethod.POST, headers = {"Accept=application/json"})
	@ResponseBody
	public ResponseEntity<String> addCustomer(@RequestBody CustomerVo customer, ModelMap model) {

		CustomerDaoImpl addCustomer = new CustomerDaoImpl();
		System.out.println(customer.getCid());
		System.out.println(customer.getName());
		System.out.println(customer.getTelephone());
		String result = addCustomer.addCustomer(customer);
		model.addAttribute("result", result);
		if(result.equals("Customer Added Successfully"))
		{
			JSONObject json = new JSONObject();
			json.put("SuccessMessage", result);
			return new ResponseEntity<String>(json.toString(),HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(value="/purchase/add", method = RequestMethod.POST, headers = {"Accept=application/json"})
	public ResponseEntity<String> addPurchase(@RequestBody PurchaseVo purchase, ModelMap model) {

		PurchaseDaoImpl addPurchase = new PurchaseDaoImpl();
		System.out.println(purchase.getEid());
		System.out.println(purchase.getCid());
		System.out.println(purchase.getPid());
		System.out.println(purchase.getQuantity());
		String result = addPurchase.addPurchase(purchase);
		model.addAttribute("result", result);
		if(result.equals("Purchase Added Successfully"))
		{
			JSONObject json = new JSONObject();
			json.put("SuccessMessage", result);
			return new ResponseEntity<String>(json.toString(),HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/purchase/delete", method = RequestMethod.POST, headers = {"Accept=application/json"})
	public ResponseEntity<String> deletePurchase(@RequestBody PurchaseVo purchase, ModelMap model) {

		PurchaseDaoImpl deletePurchase = new PurchaseDaoImpl();
		System.out.println(purchase.getPur());
		String result = deletePurchase.deletePurchase(purchase);
		model.addAttribute("result", result);
		if(result.equals("Purchase Deleted Successfully"))
		{
			JSONObject json = new JSONObject();
			json.put("SuccessMessage", result);
			return new ResponseEntity<String>(json.toString(),HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/purchase/saving", method = RequestMethod.POST, headers = {"Accept=application/json"})
	@ResponseBody
	public String getPurchaseSaving(@RequestBody PurchaseVo purchase) {

		PurchaseDaoImpl purchaseSaving = new PurchaseDaoImpl();
		System.out.println(purchase.getPur());
		JSONObject json = purchaseSaving.getPurchaseSaving(purchase);
		System.out.println(json.toString());
		return json.toString();
	}
	
	@RequestMapping(value="/employee/monthlySaleActivity", method = RequestMethod.POST, headers = {"Accept=application/json"})
	@ResponseBody
	public String getMonthlySaleActivity(@RequestBody EmployeeVo employee) {

		EmployeeDaoImpl mntSaleAct = new EmployeeDaoImpl();
		JSONObject json = mntSaleAct.getMonthlySaleActivity(employee);
		System.out.println(json.toString());
		return json.toString();
	}
	
	@RequestMapping(value="/general/count", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getCount() {

		SupplyDaoImpl getCount = new SupplyDaoImpl();
		JSONObject json = getCount.getCount();
		System.out.println(json.toString());
		return json.toString();
	}
}