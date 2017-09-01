package com.testgroup.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.testgroup.model.Cart;
import com.testgroup.model.CartItem;
import com.testgroup.model.CustomerOrder;
import com.testgroup.model.CustomerOrderHistory;
import com.testgroup.model.Product;
import com.testgroup.model.XMAP_Product_Supplier;


@Repository
public class CustomerOrderDAOImpl implements CustomerOrderDAOInt {
		
		
		@Autowired
		private SessionFactory sessionFactory;
		
		
		public void addCustomerOrder(Cart cart) {//throws MessagingException {
			CustomerOrder customerOrder=new CustomerOrder();
			customerOrder.setOderid(generateOrderId());
	
			customerOrder.setCustomer(cart.getCustomer());

			String shipadd = cart.getCustomer().getShippingAddress().getHouseno();
			shipadd += "\n"+cart.getCustomer().getShippingAddress().getStreet();
			shipadd += "\n"+cart.getCustomer().getShippingAddress().getArea();
			shipadd += "\n"+cart.getCustomer().getShippingAddress().getCity();
			shipadd += "\n"+cart.getCustomer().getShippingAddress().getState();
			
			customerOrder.setShippedAddress(shipadd);
			
			Date date = new Date();
			customerOrder.setOrderDate(date);
			//Insert the data in CustomerOrder table
			Session session=sessionFactory.openSession();
			session.save(customerOrder);
			session.flush();
			session.close();
			//To update grandtotal in Cart table

			List<CartItem> cartItems=cart.getCartItems();
			
			Session soh = sessionFactory.openSession();
			Product prd;
			for(CartItem cartItem:cartItems){
				CustomerOrderHistory orderHistory = new CustomerOrderHistory();
				orderHistory.setOrderhistoryid(generateOrderHistoryId());
				orderHistory.setCustomerOrder(customerOrder);
				orderHistory.setPrice(cartItem.getXmap_product_supplier().getProductsupplierprice());
				orderHistory.setProductid(cartItem.getXmap_product_supplier().getProductid());
				orderHistory.setSupplierid(cartItem.getXmap_product_supplier().getSupplierid());
				orderHistory.setQuantity(cartItem.getQuantity());
				orderHistory.setTotal(cartItem.getItemwisetotal());
				orderHistory.setReviewgiven(false);
				orderHistory.setCustomerid(customerOrder.getCustomer().getCustomerid());
				orderHistory.setRating((float)0.0);
				orderHistory.setComments("NA");
				
				
				
				soh.save(orderHistory);		
				soh.flush();
				XMAP_Product_Supplier xps = cartItem.getXmap_product_supplier();
				xps.setProductsupplierstock(xps.getProductsupplierstock()-cartItem.getQuantity());
				soh.update(xps);
				soh.flush();	
				
				prd = soh.get(Product.class,xps.getProductid());
				prd.setNoitemssold(prd.getNoitemssold()+cartItem.getQuantity());
				soh.update(prd);
				soh.flush();
				
			}
			
			soh.close();		
			
			Session soc = sessionFactory.openSession();
			cart.setTotalbill(0.0);
			soc.update(cart);
			soc.flush();
			soc.close();
	}

	public List<CustomerOrder> custOrders(String custid) {
		// TODO Auto-generated method stub
		return null;
	}

	public float getRating(String productid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public CustomerOrderHistory getCustomerOrderHistoryByOrderHistoryId(String ordhistid) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNoOfReviews(String productid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNoOfItemsSold(String productid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void updateOrderHistory(CustomerOrderHistory coh) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	

	private String generateOrderId(){
		
		String newOid="";
		
		Session s = sessionFactory.openSession();
		Query qr = s.createQuery("from CustomerOrder");
		List<Product> data = qr.list();
		s.close();
		if(data.size()==0){
			newOid="ORD00001";
		}
		else{
			Session ss = sessionFactory.openSession();		
			Query q = ss.createQuery("select max(oderid) from CustomerOrder");
			String prevId = q.list().get(0).toString();
			System.out.print("\nExisting : "+prevId);
			int id = Integer.parseInt(prevId.substring(3));
			System.out.print("\nExisting id : "+id);		
			id=id+1;
			if(id<=9)
				newOid="ORD0000"+id;
			else if(id<=99)
				newOid="ORD000"+id;
			else if(id<=999)
				newOid="ORD00"+id;
			else if(id<=9999)
				newOid="ORD0"+id;
			else
				newOid="ORD"+id;		
			System.out.print("\nGenerated : "+newOid);		
			ss.close();		
		}
			return newOid;
	}
	
	
	
private String generateOrderHistoryId(){
		
		String newOHid="";
		
		Session s = sessionFactory.openSession();
		Query qr = s.createQuery("from CustomerOrderHistory");
		List<Product> data = qr.list();
		s.close();
		if(data.size()==0){
			newOHid="ORDH00001";
		}
		else{
			Session ss = sessionFactory.openSession();		
			Query q = ss.createQuery("select max(orderhistoryid) from CustomerOrderHistory");
			String prevId = q.list().get(0).toString();
			System.out.print("\nExisting : "+prevId);
			int id = Integer.parseInt(prevId.substring(4));
			System.out.print("\nExisting id : "+id);		
			id=id+1;
			if(id<=9)
				newOHid="ORDH0000"+id;
			else if(id<=99)
				newOHid="ORDH000"+id;
			else if(id<=999)
				newOHid="ORDH00"+id;
			else if(id<=9999)
				newOHid="ORDH0"+id;
			else
				newOHid="ORDH"+id;		
			System.out.print("\nGenerated : "+newOHid);		
			ss.close();		
		}
			return newOHid;
	}
	
	
	

}
