package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.update;

import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create.AccountTypeCreatedEvent;

import lombok.Data;

@Data
public class AccountTypeUpdatedEvent {
	private  String accountTypeId;
	private  String accountName; 
	private  double price;
	private  String description;
}
