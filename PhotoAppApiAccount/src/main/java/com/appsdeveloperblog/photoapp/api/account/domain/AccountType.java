package com.appsdeveloperblog.photoapp.api.account.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "accountTypes")
@Data
public class AccountType {

	@Id
	@Column(name = "id", unique = true)
	private String accountTypeId;

	@Column(name = "accountName")
	private String accountName;

	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String description;
}
