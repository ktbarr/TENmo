package com.techelevator.tenmo.models;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

import javax.swing.text.html.parser.Entity;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Accounts
{
	private int accountId;
	private int userId;
	private double balance;
	private RestTemplate restTemplate;
	
	
	public int getAccountId()
	{
		return accountId;
	}
	public void setAccountId(int accountId)
	{
		this.accountId = accountId;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	
	
	
	
	
}
