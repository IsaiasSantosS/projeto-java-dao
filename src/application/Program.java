package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Department obj = new Department(1, "test");
		System.out.println(obj);
		
		Seller seller = new Seller(1, "eu", "eu@gmail.com", new Date(), 3000.0, obj);
		System.out.println(seller);
		
		
		SellerDao dao = DaoFactory.createSellerDao();
	}

}
