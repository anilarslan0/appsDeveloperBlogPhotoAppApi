package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountTypeCommand {
	
	@TargetAggregateIdentifier
	private final String accountTypeId;
	private final String accountName; 
	private final double price;
	private final String description;
}
