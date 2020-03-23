package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Branch {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer branch_id;
    private String branch_name;

	public Integer getId() {
		return branch_id;
	}

	public void setId(Integer branch_id) {
		this.branch_id = branch_id;
	}

	public String getName() {
		return branch_name;
	}

	public void setName(String branch_name) {
		this.branch_name = branch_name;
    }
}
