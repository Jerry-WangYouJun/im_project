package com.hyg.im.beans;

import java.util.Date;

public class Order {
	 private Integer id ;
	 private Integer customId ;
	 private Integer productId;
	 private String orderCode;
	 private Integer quantity;
	 private Date deliveryDate;
	 private String deliveryDateStr;
	 private Date planStartDate;
	 private Date planFinishDate;
	 private String RFIDCode ;
	 private Integer createUserId ;
	 private Date createDate ;
	 private String description;
	 private Product product;
	 private Customer customer;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomId() {
		return customId;
	}
	public void setCustomId(Integer customId) {
		this.customId = customId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryDateStr() {
		return deliveryDateStr;
	}
	public void setDeliveryDateStr(String deliveryDateStr) {
		this.deliveryDateStr = deliveryDateStr;
	}
	public Date getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	public Date getPlanFinishDate() {
		return planFinishDate;
	}
	public void setPlanFinishDate(Date planFinishDate) {
		this.planFinishDate = planFinishDate;
	}
	public String getRFIDCode() {
		return RFIDCode;
	}
	public void setRFIDCode(String rFIDCode) {
		RFIDCode = rFIDCode;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Product getProduct() {
		if(product == null ){
			product = new Product();
		}
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Customer getCustomer() {
		if(customer == null){
			customer = new Customer();
		}
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	 
	 
}
