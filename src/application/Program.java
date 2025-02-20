package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao dao = DaoFactory.createSellerDao();
		
		Seller seller = dao.findById(3);
		
		System.out.println(seller);
		
		System.out.println("===========================================");
		
		Department department = new Department(2, null);
		List<Seller> list = dao.findByDepartment(department);
		for (Seller sellerList : list) {
			System.out.println(sellerList);
		}
	
		
		System.out.println("===========================================");
		
		
		List<Seller> listAll = dao.findAll();
		for (Seller sellerList : listAll) {
			System.out.println(sellerList);
		}

	}

}
