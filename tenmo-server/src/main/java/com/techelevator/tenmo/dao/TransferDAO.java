package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.TransferS;
import com.techelevator.tenmo.model.User;

public interface TransferDAO
{
	List<User> getUsers();
	
	String createTransfer(int accountFrom, int accountTo, double amount);

	List<TransferS> getAllTransfers(int id);

}
