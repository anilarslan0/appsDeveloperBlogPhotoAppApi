package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.update;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.photoapp.api.account.domain.AccountType;
import com.appsdeveloperblog.photoapp.api.account.persistence.AccountTypeRepository;

@Component
public class AccountTypesEventsHandlerForUpdate {

	private AccountTypeRepository accountTypeRepository;

	public AccountTypesEventsHandlerForUpdate(AccountTypeRepository accountTypeRepository) {
		super();
		this.accountTypeRepository = accountTypeRepository;
	}

	@EventHandler
	public void on(AccountTypeUpdatedEvent accountTypeUpdatedEvent) {
		AccountType accountType=new AccountType();
		BeanUtils.copyProperties(accountTypeUpdatedEvent, accountType);
		this.accountTypeRepository.save(accountType);
	}
}
