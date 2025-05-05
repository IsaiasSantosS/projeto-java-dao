package application;

import java.util.Date;
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
		
		System.out.println("===========================================");
		//Seller newSeller = new Seller(null, "Isaias", "isaias@gmail.com", new Date(), 4000.0, department);
		//dao.insert(newSeller);
		//System.out.println("Inserted! New id = " + newSeller.getId());
		
		System.out.println("===========================================");
		seller = dao.findById(9);
		seller.setName("Isaac");
		seller.setEmail("isaac@gmail.com");
		dao.update(seller);
		System.out.println("Update Completed");
		
	}

}
