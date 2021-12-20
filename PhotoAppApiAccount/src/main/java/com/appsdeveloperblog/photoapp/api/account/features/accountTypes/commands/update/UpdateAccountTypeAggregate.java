package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.update;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create.AccountTypeCreatedEvent;

@Aggregate
public class UpdateAccountTypeAggregate {

	@AggregateIdentifier
	String identifier=UUID.randomUUID().toString();
	
	
	private String accountTypeId;
	private String accountName;
	private double price;
	private String description;

	
	public UpdateAccountTypeAggregate() {
		
	}

	@CommandHandler
	public UpdateAccountTypeAggregate(UpdateAccountTypeCommand updateAccountTypeCommand) {

		AccountTypeUpdatedEvent accountTypeUpdatedEvent = new AccountTypeUpdatedEvent();

		BeanUtils.copyProperties(updateAccountTypeCommand, accountTypeUpdatedEvent);

		AggregateLifecycle.apply(accountTypeUpdatedEvent);
	}
	
	@EventSourcingHandler  
	public void on(AccountTypeUpdatedEvent accountTypeUpdatedEvent) {
		this.accountTypeId=accountTypeUpdatedEvent.getAccountTypeId();
		this.accountName=accountTypeUpdatedEvent.getAccountName();
		this.description=accountTypeUpdatedEvent.getDescription();
		this.price=accountTypeUpdatedEvent.getPrice();
	}

}
