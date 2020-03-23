package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "stock_history")
public class Stock {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "sh_id")
    private Integer stock_id;

    @Column(name = "sh_pb_id")
    private Integer stock_product_branch_id;

    @Column(name = "sh_amout")
    private Integer stock_amount;

    @Column(name = "sh_user_id")
    private Integer stock_user_id;

    @Column(name = "operator_type")
    private Integer stock_operator;

    /* private Integer stock_id; */
	public Integer getId() {
		return stock_id;
	}
	public void setId(Integer stock_id) {
		this.stock_id = stock_id;
    }
    
    /*  private Integer stock_product_branch_id; */
    public Integer getPbId() {
		return stock_product_branch_id;
	}
	public void setPbId(Integer stock_product_branch_id) {
		this.stock_product_branch_id = stock_product_branch_id;
    }

    
    /*  private Integer stock_amount; */ 
    public Integer getAmount() {
		return stock_amount;
	}
	public void setAmount(Integer stock_amount) {
		this.stock_amount = stock_amount;
    }
    
    /*  private Integer stock_user_id; */ 
    public Integer getUserId() {
		return stock_user_id;
	}
	public void setUserId(Integer stock_user_id) {
		this.stock_user_id = stock_user_id;
    }

        
    /*  private Integer stock_user_name; */ 
    public Integer getOperator() {
		return stock_operator;
	}
	public void setOperator(Integer stock_operator) {
		this.stock_operator = stock_operator;
	}
}
