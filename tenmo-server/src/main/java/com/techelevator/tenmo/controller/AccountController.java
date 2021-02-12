package com.techelevator.tenmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDAO;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController
{
	
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private UserDAO userDAO;
	
	public AccountController(AccountDAO accountDAO, UserDAO userDAO)
	{
		this.accountDAO = accountDAO;
		this.userDAO = userDAO;
	}
	
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/accounts/{id}/balance", method = RequestMethod.GET)
    public double getAccountBalance(@PathVariable int id)
    {
    	
    	double balance = accountDAO.getAccountBalance(id);
    	
    	return balance;
    }

}
