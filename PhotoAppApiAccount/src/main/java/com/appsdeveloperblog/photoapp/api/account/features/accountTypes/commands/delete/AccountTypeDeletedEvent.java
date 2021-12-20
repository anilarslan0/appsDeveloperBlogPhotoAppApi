package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.delete;

import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create.AccountTypeCreatedEvent;

import lombok.Data;

@Data
public class AccountTypeDeletedEvent {
	private  String accountTypeId;
}
