package Andy.Project.CIMS.ui;

import Andy.Project.CIMS.bean.Customer;
import Andy.Project.CIMS.service.CustomerList;
import Andy.Project.CIMS.util.CMUtility;

public class CustomerView {
	private CustomerList customerList = new CustomerList(10);

	// 空參constructor
	public CustomerView() {
		Customer customer = new Customer("小吳", '男', 23, "0985-532634", "wu@gmail");
		customerList.addCustomer(customer);
	}

	public void enterMainMenu() {
		boolean isFlag = true;
		while (isFlag) {
			System.out.println("\n-----------------客户資訊管理系統-----------------\n");
			System.out.println("                   1 增加新客戶");
			System.out.println("                   2 修改現有客戶的資訊");
			System.out.println("                   3 刪除現有客戶的資訊");
			System.out.println("                   4 客戶列表");
			System.out.println("                   5 退出系統\n");
			System.out.print("                   請選擇(1-5)：");

			char menu = CMUtility.readMenuSelection();
			switch (menu) {
			case '1':
				addNewCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listAllCustomers();
				break;
			case '5':
				System.out.println("確認是否退出(Y/N):");
				char isExit = CMUtility.readConfirmSelection();
				if (isExit == 'Y') {
					isFlag = false;
				}
				break;
			}
		}

	}

	private void addNewCustomer() {
		System.out.println("---------------------添加客戶---------------------");
		System.out.println("姓名: ");
		String name = CMUtility.readString(13);
		System.out.println("性別");
		char gender = CMUtility.readChar();
		System.out.println("年齡");
		int age = CMUtility.readInt();
		System.out.println("電話");
		String phone = CMUtility.readString(13);
		System.out.println("信箱");
		String email = CMUtility.readString(30);

		// 將上述資訊封裝到同一個customer對象中
		Customer customer = new Customer(name, gender, age, phone, email);
		boolean isSuccess = customerList.addCustomer(customer);
		if (isSuccess) {
			System.out.println("---------------------添加完成---------------------");
		} else {
			System.out.println("---------------------客戶目錄已滿，添加失敗---------------------");
		}

	}

	private void modifyCustomer() {
		System.out.println("---------------------修改客戶---------------------");
		Customer cust = null;
		int number = 0;
		for (;;) {
			System.out.println("請選擇待修改客戶的編號(-1退出)");
			number = CMUtility.readInt();
			if (number == -1) {
				return;
			}
			cust = customerList.getCustomer(number - 1);
			if (cust == null) {
				System.out.println("無法找到指定客戶!");

			} else {
				break;
			}
		}
		// 修改客戶訊息
		System.out.println("姓名(" + cust.getName() + "):");
		String name = CMUtility.readString(13, cust.getName());
		System.out.println("性別(" + cust.getGender() + "):");
		char gender = CMUtility.readChar(cust.getGender());
		System.out.println("年齡(" + cust.getAge() + "):");
		int age = CMUtility.readInt(cust.getAge());
		System.out.println("電話(" + cust.getPhone() + "):");
		String phone = CMUtility.readString(13, cust.getPhone());
		System.out.println("信箱(" + cust.getEmail() + "):");
		String email = CMUtility.readString(30, cust.getEmail());

		Customer newCust = new Customer(name, gender, age, phone, email);
		boolean isReplaced = customerList.replaceCustomer(number - 1, newCust);
		if (isReplaced) {
			System.out.println("---------------------修改完成---------------------");
		} else {
			System.out.println("---------------------修改失敗---------------------");
		}

	}

	private void deleteCustomer() {
		System.out.println("---------------------------刪除客户---------------------------");
		int number;
		for (;;) {
			System.out.println("請選擇待修改客戶的編號(-1退出)");
			number = CMUtility.readInt();
			if (number == -1) {
				return;
			}
			Customer customer = customerList.getCustomer(number - 1);
			if (customer == null) {
				System.out.println("無法找到指定客戶!");
			}else {
				break;
			}
		}
		
		//找到了指定的客戶
		System.out.println("確認是否刪除(Y/N): ");
		char isDelete = CMUtility.readConfirmSelection();
		if(isDelete == 'Y') {
			boolean deleteSuccess = customerList.deleteCustomer(number-1);
			if(deleteSuccess) {
				System.out.println("---------------------------刪除完成---------------------------");
			}else {
				System.out.println("---------------------------刪除失敗---------------------------");
			}
		}else {
			return;
		}
	}

	private void listAllCustomers() {
		System.out.println("---------------------------客户列表---------------------------");
		int total = customerList.getTotal();
		if (total == 0) {
			System.out.println("沒有客戶紀錄");
		} else {
			System.out.println("編號\t姓名\t性別\t年齡\t電話\t信箱");
			Customer[] custs = customerList.getAllCustomers();
			for (int i = 0; i < custs.length; i++) {
				Customer cust = custs[i]; // 直接造一個變數，就不用每次get東西都要custs[i].getXXX
				System.out.println((i + 1) + "\t" + cust.getName() + "\t" + cust.getGender() + "\t" + cust.getAge()
						+ "\t" + cust.getPhone() + "\t" + cust.getEmail());
			}
		}
		System.out.println("---------------------------客户列表完成---------------------------");

	}

	public static void main(String[] args) {
		CustomerView view = new CustomerView();
		view.enterMainMenu();

	}

}
