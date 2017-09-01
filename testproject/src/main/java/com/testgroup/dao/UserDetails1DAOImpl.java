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

import com.testgroup.model.UserDetails1;


@Repository
public class UserDetails1DAOImpl implements UserDeatils1DAOInt {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public String addUser(UserDetails1 ud) {
		Session ses=sessionFactory.openSession();
		ud.setUserid(generateUserId());
		ses.save(ud);
		ses.flush();
		ses.close();
		//saveUserImage(ud);
		return ud.getUserid();

	}
	
	
	public UserDetails1 loginCheck(UserDetails1 ud) {
		Session ses = sessionFactory.openSession();
		//String qs = "from UserDetails where userid = '"+ud.getUserid()+"' and password = '"+ud.getPassword()+"'";
		String qs = "from UserDetails1 where userid = ? and password = ? and enabled=true";		
		Query qr = ses.createQuery(qs);
		qr.setParameter(0, ud.getUserid());
		qr.setParameter(1, ud.getPassword());
		UserDetails1 result;
		result = (UserDetails1)qr.uniqueResult();
		ses.close();		
		return result;
	}
	
	




	
	private String generateUserId(){
		String newUid="";		
		Session s = sessionFactory.openSession();
		Query qr = s.createQuery("from UserDetails1");
		List<UserDetails1> data = qr.list();
		s.close();
		if(data.size()==0){ // if table is empty
			newUid="USR00001";
		}
		else {		// if table is not empty	
			Session ss = sessionFactory.openSession();		
			Query q = ss.createQuery("select max(userid) from UserDetails1");
			String prevId = q.list().get(0).toString();
			System.out.print("\nExisting : "+prevId);
			int id = Integer.parseInt(prevId.substring(3));
			System.out.print("\nExisting id : "+id);		
			id=id+1;
			if(id<=9)
				newUid="USR0000"+id;
			else if(id<=99)
				newUid="USR000"+id;
			else if(id<=999)
				newUid="USR00"+id;
			else if(id<=9999)
				newUid="USR0"+id;
			else
				newUid="USR"+id;		
			System.out.print("\nGenerated : "+newUid);		
			ss.close();
		}	
		return newUid;		
		
}	
	
	private void saveUserImage(UserDetails1 ud)
	{
		
		//System.out.print("\nprd img : " + ud.getUserImage());	
			
		try{				    	
			//if (ud.getUserImage() != null )
			{	
				Path path=Paths.get("C:/Test-ws/testproject/src/main/webapp/resources/images/users//"+ud.getUserid()+".jpg");
	           //	ud.getUserImage().transferTo(new File(path.toString()));           	
	            System.out.print("\nUser Image saved");	            
	        }				
		}
		catch(Exception ex){
			System.out.print("\nUser Image not saved...");
		}				
	}	
	
	


	
}
