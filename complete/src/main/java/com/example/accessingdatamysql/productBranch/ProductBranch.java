package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class ProductBranch {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer pb_id;
    private Integer pb_branch_id;
	private Integer pb_product_id;
	private Integer pb_stock;

	public Integer getPbId() {
		return pb_id;
	}

	public void setPbId(Integer pb_id) {
		this.pb_id = pb_id;
	}

	public Integer getPbBranchId() {
		return pb_branch_id;
	}

	public void setPbBranchId(Integer pb_branch_id) {
		this.pb_branch_id = pb_branch_id;
    }
    
    public Integer getPbProductId() {
		return pb_product_id;
	}

	public void setPbProductId(Integer pb_product_id) {
		this.pb_product_id = pb_product_id;
	}

	public Integer getPbStock(){
		return pb_stock;
	}

	public void setPbStock(Integer pb_stock){
		this.pb_stock = pb_stock;
	}
}


