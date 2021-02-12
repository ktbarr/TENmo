package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.TransferS;
import com.techelevator.tenmo.model.User;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController
{
	@Autowired
	private TransferDAO transferDAO;
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private UserDAO userDAO;
	
	public TransferController(TransferDAO transferDAO, AccountDAO accountDAO, UserDAO userDAO)
	{
		this.transferDAO = transferDAO;
		this.accountDAO = accountDAO;
		this.userDAO = userDAO;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsersList() 
	{
		List<User> users = userDAO.findAll();
		
		return users;
	}
	
	@ResponseStatus (HttpStatus.CREATED)
	@RequestMapping(path = "/transfers", method = RequestMethod.POST)
	public String createTransfer(@RequestBody TransferS trasfer)
	{
		String results = transferDAO.createTransfer(trasfer.getAccountFrom(), trasfer.getAccountTo(), trasfer.getAmount());
		
		return results;
	}
	
	@RequestMapping(value = "account/transfers/{id}", method = RequestMethod.GET)
	public List<TransferS> getAllMyTransfers(@PathVariable int id)
	{
		List<TransferS> transfers = transferDAO.getAllTransfers(id);
	
		return transfers;
	}
	

}
