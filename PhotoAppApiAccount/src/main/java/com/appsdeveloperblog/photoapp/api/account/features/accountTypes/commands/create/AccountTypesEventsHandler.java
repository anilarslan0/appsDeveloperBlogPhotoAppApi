package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.photoapp.api.account.domain.AccountType;
import com.appsdeveloperblog.photoapp.api.account.persistence.AccountTypeRepository;

@Component //belleğe atılacağını belirtiyoruz.İlk build edileceği anda
public class AccountTypesEventsHandler {

	private AccountTypeRepository accountTypeRepository;

	public AccountTypesEventsHandler(AccountTypeRepository accountTypeRepository) {
		super();
		this.accountTypeRepository = accountTypeRepository;
	}
	
	@EventHandler
	public void on(AccountTypeCreatedEvent accountTypeCreatedEvent) {
		AccountType accountType=new AccountType();
		BeanUtils.copyProperties(accountTypeCreatedEvent, accountType);
		this.accountTypeRepository.save(accountType);
	}
	
	
}
