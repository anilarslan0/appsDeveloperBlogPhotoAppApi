package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CreateAccountTypeAggregate {
	
	//Buraya koyduklarımız evente konu olan datalarımız
	@AggregateIdentifier
	private  String accountTypeId;
	private  String accountName; 
	private  double price;
	private  String description;
	
	public CreateAccountTypeAggregate() {
		
	}

	@CommandHandler
	public CreateAccountTypeAggregate(CreateAccountTypeCommand createAccountTypeCommand) {
		//Validation
		if (createAccountTypeCommand.getPrice()<0) {
			throw new IllegalArgumentException("Fiyat 0'dan kucuk olamaz.");
		}
		
		//Business
		
		//Event fire
		AccountTypeCreatedEvent accountTypeCreatedEvent=new AccountTypeCreatedEvent();
		//model mapper,builder,bean utils alternatifleri kullanılabilir
		BeanUtils.copyProperties(createAccountTypeCommand, accountTypeCreatedEvent);
		
		AggregateLifecycle.apply(accountTypeCreatedEvent);
	}
	
	@EventSourcingHandler  //aggregate'i fırlatma
	public void on(AccountTypeCreatedEvent accountTypeCreatedEvent) {
		this.accountTypeId=accountTypeCreatedEvent.getAccountTypeId();
		this.accountName=accountTypeCreatedEvent.getAccountName();
		this.description=accountTypeCreatedEvent.getDescription();
		this.price=accountTypeCreatedEvent.getPrice();
	}
}
