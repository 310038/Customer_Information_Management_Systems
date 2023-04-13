package Andy.Project.CIMS.service;

import Andy.Project.CIMS.bean.Customer;

/**
 * 為customer對象的管理模塊，內部使用array管理一組Customer對象
 * @Description
 * @author AndyWu
 * @version
 * 2023年2月23日上午9:17:05
 */
public class CustomerList {
	private Customer[] customers;
	private int total = 0;
	
	public CustomerList(int totalCustomer) {
		customers = new Customer[totalCustomer];
		
	}
	
	//將指定的客戶添加到array中,true成功，false失敗
	public boolean addCustomer(Customer customer) {
		if(total >= customers.length) {
			return false;
		}else {
			customers[total] = customer; 
			total++;
			return true;
		}
	}
	
	//修改指定索引位置的客戶信息，true成功
	public boolean replaceCustomer(int index, Customer cust) {
		if(index < 0 || index >= total) {
			return false;
		}else {
			customers[index] = cust; // 小心index的意義，因為客戶沒有index從0開始的觀念
			return true;
		}
		
	}
	
	//刪除指定索引位置上的客戶，true成功
	public boolean deleteCustomer(int index) {
		if(index < 0 || index >= total) {
			return false;
		}else {
			for(int i = index;i < total-1; i++) {
				customers[i] = customers[i+1];
			}
			customers[total] = null;
			total--;
			return true;
		}
		
	}
	
	//獲取所有客戶訊息
	public Customer[] getAllCustomers() {
		Customer[] custs = new Customer[total];
		for(int i = 0; i < total; i++) {
			custs[i] = customers[i]; 
		}
		return custs;
	}
	
	//獲取指定索引的客戶訊息，返回null代表沒找到
	public Customer getCustomer(int index) {
		if(index < 0 || index >= total) {
			return null;
		}else {
			return customers[index];
			
		}
	}
	
	//獲取array(資料庫)中所存取的客戶數量
	public int getTotal() {
		return total;
	}


}
