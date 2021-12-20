package com.appsdeveloperblog.photoapp.api.account.controllers;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create.CreateAccountTypeCommand;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.delete.DeleteAccountTypeCommand;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.update.UpdateAccountTypeCommand;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.queries.findAll.FindAccountTypesQuery;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.queries.getById.GetIdAccountTypesQuery;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.restModels.CreateAccountTypeRestModel;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.restModels.DeleteAccountTypeRestModel;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.restModels.UpdateAccountTypeRestModel;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.restModels.AccountTypeListRestModel;

@RestController
@RequestMapping("/accounttypes")
public class AccountTypesController {

	private CommandGateway commandGateway;
	private QueryGateway queryGateway;

	@Autowired
	public AccountTypesController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}

	@PostMapping
	public String createAccountType(@RequestBody CreateAccountTypeRestModel createAccountTypeRestModel) {
		// rest modeli command'e çevir
		// axon cqrs bus ile yaşam döngüsüne sok.

		CreateAccountTypeCommand command = CreateAccountTypeCommand.builder()
				.price(createAccountTypeRestModel.getPrice()).accountName(createAccountTypeRestModel.getAccountName())
				.description(createAccountTypeRestModel.getDescription()).accountTypeId(UUID.randomUUID().toString())
				.build();

		String returnValue = this.commandGateway.sendAndWait(command);

		return returnValue;

	}

	@PutMapping
	public String updateAccountType(@RequestBody UpdateAccountTypeRestModel updateAccountTypeRestModel) {
		UpdateAccountTypeCommand command = UpdateAccountTypeCommand.builder()
				.price(updateAccountTypeRestModel.getPrice()).accountName(updateAccountTypeRestModel.getAccountName())
				.description(updateAccountTypeRestModel.getDescription())
				.accountTypeId(updateAccountTypeRestModel.getAccountTypeId()).build();

		String returnValue = this.commandGateway.sendAndWait(command);

		return returnValue;

	}

	@DeleteMapping
	public String deleteAccountType(@RequestBody DeleteAccountTypeRestModel deleteAccountTypeRestModel) {
		DeleteAccountTypeCommand command = DeleteAccountTypeCommand.builder()
				.accountTypeId(deleteAccountTypeRestModel.getAccountTypeId()).build();

		String returnValue = this.commandGateway.sendAndWait(command);
		return returnValue;
	}

	@GetMapping
	public List<AccountTypeListRestModel> listAccountTypes() {
		FindAccountTypesQuery findAccountTypesQuery = new FindAccountTypesQuery();
		List<AccountTypeListRestModel> accountTypes = this.queryGateway
				.query(findAccountTypesQuery, ResponseTypes.multipleInstancesOf(AccountTypeListRestModel.class)).join();

		return accountTypes;
	}

	@GetMapping("/getById")
	public AccountTypeListRestModel getById(@RequestBody GetIdAccountTypesQuery getIdAccountTypesQuery) {

		AccountTypeListRestModel accountType = this.queryGateway
				.query(getIdAccountTypesQuery, ResponseTypes.instanceOf(AccountTypeListRestModel.class)).join();

		return accountType;
	}

}
