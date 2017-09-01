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

import com.testgroup.model.Supplier;


@Repository
public class SuppDaoImp implements SuppDaoInt {
	

	@Autowired
	private SessionFactory sessionFactory;
	


	public void addsupplier(Supplier su) 
	
	{
		Session ses=sessionFactory.openSession();
		su.setSupplierid(generatesupplierid());
		ses.save(su);
		ses.flush();
		ses.close();
		savesupplierImage(su);
		
		
	}
	
	
	public List<Supplier> getAllSuppliers()
	{
		Session ses=sessionFactory.openSession();
		Query qr=ses.createQuery("from Supplier");
		List<Supplier> data=qr.list();
		ses.close();
		return data;
		
	}

	
	
	
	
	
	
	
	public void deletesupplier(String sid)
	{
		Session ses=sessionFactory.openSession();
		Supplier sup=ses.get(Supplier.class,sid);
		ses.delete(sup);
		ses.flush();
		ses.close();
		
	}

	
	

	public Supplier getSupplierbyid(String sid)
	{
		Session ses=sessionFactory.openSession();
		Supplier sup=ses.get(Supplier.class, sid);
		ses.close();
		return sup;
	}
	
	
	
	
	

	public void editSupplier(Supplier sup)
	{
		
		Session ses=sessionFactory.openSession();
		ses.update(sup);
		ses.flush();
		ses.close();
	}

	
	

	private String generatesupplierid(){
		String newsid="";		
		Session s = sessionFactory.openSession();
		Query qr = s.createQuery("from Supplier");
		List<Supplier> data = qr.list();
		s.close();
		if(data.size()==0){ // if table is empty
			newsid="SUP00001";
		}
		else {		// if table is not empty	
			Session ss = sessionFactory.openSession();		
			Query q = ss.createQuery("select max(supplierid) from Supplier");
			String prevId = q.list().get(0).toString();
			System.out.print("\nExisting : "+prevId);
			int id = Integer.parseInt(prevId.substring(3));
			System.out.print("\nExisting id : "+id);		
			id=id+1;
			if(id<=9)
				newsid="SUP0000"+id;
			else if(id<=99)
				newsid="SUP000"+id;
			else if(id<=999)
				newsid="SUP00"+id;
			else if(id<=9999)
				newsid="SUP0"+id;
			else
				newsid="SUP"+id;		
			System.out.print("\nGenerated : "+newsid);		
			ss.close();
		}	
		return newsid;		
	
}
	
	
	
	
	

	private void savesupplierImage(Supplier su)
	{
		
		System.out.print("\nprd img : " + su.getSupplierImage());	
			
		try{				    	
			if (su.getSupplierImage() != null )
			{	
				Path path=Paths.get("C:/Test-ws/testproject/src/main/webapp/resources/images/supplier//"+su.getSupplierid()+".jpg");
	           	su.getSupplierImage().transferTo(new File(path.toString()));           	
	            System.out.print("\nSupplier Image saved");	            
	        }				
		}
		catch(Exception ex){
			System.out.print("\nSupplier Image not saved...");
		}				
	}	
}

