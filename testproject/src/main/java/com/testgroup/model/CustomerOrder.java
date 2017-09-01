package com.testgroup.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;


@Entity
public class CustomerOrder 
{
	@Id
	private String oderid;
	
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	
	
	@OneToMany(mappedBy="customerOrder", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<CustomerOrderHistory>customerOrdersHistory;	
	
	private String shippedAddress;
	
	private Date orderDate;
	
	
	public String getOderid() {
		return oderid;
	}
	public void setOderid(String oderid) {
		this.oderid = oderid;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<CustomerOrderHistory> getCustomerOrdersHistory() {
		return customerOrdersHistory;
	}
	public void setCustomerOrdersHistory(List<CustomerOrderHistory> customerOrdersHistory) {
		this.customerOrdersHistory = customerOrdersHistory;
	}
	public String getShippedAddress() {
		return shippedAddress;
	}
	public void setShippedAddress(String shippedAddress) {
		this.shippedAddress = shippedAddress;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}	
	
	
	
	