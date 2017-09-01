package com.testgroup.dao;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testgroup.model.Product;

@Repository
public class ProdDAOImp implements ProdDAOInt {

	@Autowired
	private SessionFactory sessionFactory;
	

	public void addproduct(Product p) 
	{
		Session ses=sessionFactory.openSession();
		p.setProductid(generateproductid());
		ses.save(p);
		ses.flush();
		ses.close();
		saveproductImage(p);
		
	}
	
	
	
	
	
	
	
	
	public List<Product> getAllProducts()
	
	{

		Session ses=sessionFactory.openSession();
		Query qr=ses.createQuery("from Product");
		List<Product> data=qr.list();
		ses.close();
		return data;
	}









	public void deleteproduct(String pid)
	{
		Session ses=sessionFactory.openSession();
		Product prd=ses.get(Product.class,pid);
		ses.delete(prd);
		ses.flush();
		ses.close();
		
	}
	
	
	
	


	public Product getProductbyid(String pid)
	{
		Session ses=sessionFactory.openSession();
		Product prd=ses.get(Product.class, pid);
		ses.close();
		return prd;
	}
	
	
	
	
	

	public void editproduct(Product prd) 
	{
		Session ses=sessionFactory.openSession();
		ses.update(prd);
		ses.flush();
		ses.close();
	}
	








	private String generateproductid(){
		String newpid="";		
		Session s = sessionFactory.openSession();
		Query qr = s.createQuery("from Product");
		List<Product> data = qr.list();
		s.close();
		if(data.size()==0){ // if table is empty
			newpid="PRD00001";
		}
		else {		// if table is not empty	
			Session ss = sessionFactory.openSession();		
			Query q = ss.createQuery("select max(productid) from Product");
			String prevId = q.list().get(0).toString();
			System.out.print("\nExisting : "+prevId);
			int id = Integer.parseInt(prevId.substring(3));
			System.out.print("\nExisting id : "+id);		
			id=id+1;
			if(id<=9)
				newpid="PRD0000"+id;
			else if(id<=99)
				newpid="PRD000"+id;
			else if(id<=999)
				newpid="PRD00"+id;
			else if(id<=9999)
				newpid="PRD0"+id;
			else
				newpid="PRD"+id;		
			System.out.print("\nGenerated : "+newpid);		
			ss.close();
		}	
		return newpid;		
	
}
	
	
	
	
	
	
	
	
	
	private void saveproductImage(Product ud)
	{
		
		System.out.print("\nprd img : " + ud.getProductimage());	
			
		try{				    	
			if (ud.getProductimage() != null )
			{	
				Path path=Paths.get("C:/Test-ws/testproject/src/main/webapp/resources/images/product/"+ud.getProductid()+".jpg");
	           	ud.getProductimage().transferTo(new File(path.toString()));           	
	            System.out.print("\nProduct  Image saved");	            
	        }				
		}
		catch(Exception ex){
			System.out.print("\nProduct Image not saved...");
		}				
	}	
	
	
}


