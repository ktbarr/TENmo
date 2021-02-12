package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.AccountsS;
import com.techelevator.tenmo.model.User;

public interface AccountDAO {
	
    User getUser();
    
    double getAccountBalance(int id);

	double addToBalance(double amount, int accountTo);

	double subtractFromBalance(double amount, int accountFrom);

	AccountsS findAccountById(int id);
    
}
