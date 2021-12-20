package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.queries.getById;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.photoapp.api.account.domain.AccountType;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.queries.findAll.FindAccountTypesQuery;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.restModels.AccountTypeListRestModel;
import com.appsdeveloperblog.photoapp.api.account.persistence.AccountTypeRepository;

@Component
public class GetIdAccountTypeQueryHandler {

	private AccountTypeRepository accountTypeRepository;

	@Autowired
	public GetIdAccountTypeQueryHandler(AccountTypeRepository accountTypeRepository) {
	
		this.accountTypeRepository = accountTypeRepository;
	}

	@QueryHandler
	public AccountTypeListRestModel accountTypeListRestModel(GetIdAccountTypesQuery getIdAccountTypesQuery) {

		AccountType accountType = this.accountTypeRepository.getById(getIdAccountTypesQuery.getAccountTypeId());
		AccountTypeListRestModel accountTypeListRestModel = new AccountTypeListRestModel();
		BeanUtils.copyProperties(accountType, accountTypeListRestModel);

		return accountTypeListRestModel;
	}

}
