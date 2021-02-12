package com.techelevator.tenmo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.AccountsS;
import com.techelevator.tenmo.model.User;

@Component
public class AccountSqlDAO implements AccountDAO
{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private User user;
	private double balance;

	public AccountSqlDAO(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public User getUser()
	{
		return user;
	}

	public double getBalance()
	{
		return balance;
	}

	public double getAccountBalance(int id)
	{
		return jdbcTemplate.queryForObject("SELECT balance\r\n" + "FROM accounts\r\n" + "WHERE user_id = ?;",
				double.class, id);
	}

	@Override
	public double addToBalance(double amountToAdd, int id)
	{
		AccountsS account = findAccountById(id);
		
		double newBalance = account.getBalance() + amountToAdd;
				
		String sql = "UPDATE accounts\r\n" + 
							"SET balance = ?\r\n" + 
							"WHERE user_id = ?;";
		try
		{
			jdbcTemplate.update(sql, newBalance, id);
		} catch (DataAccessException e)
		{
			System.out.println("Error accessing data");
		}
		return account.getBalance();
	}

	@Override
	public double subtractFromBalance(double amountToSubtract, int id)
	{
		AccountsS account = findAccountById(id);
		
		double newBalance = account.getBalance() - amountToSubtract;
		
		String sql = "UPDATE accounts\r\n" + 
						"SET balance = ?\r\n" + 
						"WHERE user_id = ?;";
		try
		{
			jdbcTemplate.update(sql, newBalance, id);
		} catch (DataAccessException e)
		{
			System.out.println("Error accessing data");
		}
		return account.getBalance();
	}

	@Override
	public AccountsS findAccountById(int id)
	{
		AccountsS account = null;
		
		String sql = "SELECT *\r\n" + 
					"FROM accounts\r\n" + 
					"WHERE account_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		
		if (results.next())
		{
			account = mapRowToAccount(results);
		}
		
		return account;
	}

	private AccountsS mapRowToAccount(SqlRowSet result)
	{
		AccountsS account = new AccountsS();
		
		account.setBalance(result.getDouble("balance"));
		account.setAccountId(result.getInt("account_id"));
		account.setUserId(result.getInt("user_id"));
		
		return account;
	}

}
