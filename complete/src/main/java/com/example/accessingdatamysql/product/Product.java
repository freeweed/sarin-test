package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer product_id;
    private String product_name;
    private String product_detail;

	public Integer getId() {
		return product_id;
	}

	public void setId(Integer product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return product_name;
	}

	public void setName(String product_name) {
		this.product_name = product_name;
    }
    
    public String getDetail() {
		return product_detail;
	}

	public void setDetail(String product_detail) {
		this.product_detail = product_detail;
	}
}
