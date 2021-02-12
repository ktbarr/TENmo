package com.techelevator.tenmo.models;

public class TransferC
{
	private int transferId;
	private int transferTypeId;
	private int transferStatusId;
	private int accountFrom;
	private int accountTo;
	private double amount;
	private String transferStatus;
	private String transferType;
	private String usernameReceive;
	private String usernameSend;
	
	public TransferC()
	{
		
	}
	
	public TransferC(int accountFrom, int accountTo, double amount)
	{
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
	}
	public int getTransferId()
	{
		return transferId;
	}
	public void setTransferId(int transferId)
	{
		this.transferId = transferId;
	}
	public int getTransferTypeId()
	{
		return transferTypeId;
	}
	public void setTransferTypeId(int transferTypeId)
	{
		this.transferTypeId = transferTypeId;
	}
	public int getTransferStatusId()
	{
		return transferStatusId;
	}
	public void setTransferStatusId(int transferStatusId)
	{
		this.transferStatusId = transferStatusId;
	}
	public int getAccountFrom()
	{
		return accountFrom;
	}
	public void setAccountFrom(int accountFrom)
	{
		this.accountFrom = accountFrom;
	}
	public int getAccountTo()
	{
		return accountTo;
	}
	public void setAccountTo(int accountTo)
	{
		this.accountTo = accountTo;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public String getTransferStatus()
	{
		return transferStatus;
	}
	public void setTransferStatus(String transferStatus)
	{
		this.transferStatus = transferStatus;
	}
	public String getTransferType()
	{
		return transferType;
	}
	public void setTransferType(String transferType)
	{
		this.transferType = transferType;
	}
	public String getUsernameReceive()
	{
		return usernameReceive;
	}
	public void setUsernameReceive(String usernameReceive)
	{
		this.usernameReceive = usernameReceive;
	}
	public String getUsernameSend()
	{
		return usernameSend;
	}
	public void setUsernameSend(String usernameSend)
	{
		this.usernameSend = usernameSend;
	}

}
