package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.TransferS;
import com.techelevator.tenmo.model.User;

@Component
public class TransferSqlDAO implements TransferDAO
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private UserDAO userDAO;
	
	
	public TransferSqlDAO(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<User> getUsers() 
	{
		List<User> users = new ArrayList<User>();
		String sql = "SELECT user_id\r\n" + 
				"        ,username\r\n" + 
				"FROM users;";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		while (rows.next()) 
		{
			 User user = mapRowToUser(rows);
	            users.add(user);
		}
		
		return users;
	}
	
	@Override
	public String createTransfer(int accountFrom, int accountTo, double amount)
	{				
		String sql = "INSERT INTO transfers\r\n" + 
							"(\r\n" + 
							"        transfer_type_id\r\n" + 
							"        , transfer_status_id\r\n" + 
							"        , account_from\r\n" + 
							"        , account_to\r\n" + 
							"        , amount\r\n" + 
							")\r\n" + 
							"VALUES\r\n" + 
							"(\r\n" + 
							"        2\r\n" + 
							"        , 2\r\n" + 
							"        , ?\r\n" + 
							"        , ?\r\n" + 
							"        , ?\r\n" + 
							");";
		
		jdbcTemplate.update(sql, accountFrom, accountTo, amount);
		
		accountDAO.addToBalance(amount, accountTo);
		accountDAO.subtractFromBalance(amount, accountFrom);
		
		return "Transfer Complete!!!";
	}
	
	@Override
	public List<TransferS> getAllTransfers(int userId)
	{
		List<TransferS> allTransfers = new ArrayList<>();
	
		String sql = "SELECT t.*\r\n" + 
							"        , u.username AS user_from\r\n" + 
							"        , us.username AS user_to\r\n" + 
							"FROM transfers AS t\r\n" + 
							"INNER JOIN accounts AS a\r\n" + 
							"        ON t.account_from = a.account_id\r\n" + 
							"INNER JOIN accounts AS ac\r\n" + 
							"        ON t.account_to = ac.account_id\r\n" + 
							"INNER JOIN users AS u\r\n" + 
							"        ON a.user_id = u.user_id\r\n" + 
							"INNER JOIN users AS us\r\n" + 
							"        ON ac.user_id = us.user_id\r\n" + 
							"WHERE a.user_id = ?\r\n" + 
							"        OR ac.user_id = ?;";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, userId, userId);
		
		while (rows.next())
		{
			TransferS transfer = mapRowToTransfer(rows);
			allTransfers.add(transfer);
		}

		return allTransfers;
	}
	
	private User mapRowToUser(SqlRowSet rs)
	{
        User user = new User();
    
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        user.setAuthorities("ROLE_USER");
        
        return user;
    }
	
	private TransferS mapRowToTransfer(SqlRowSet rs)
	{
        TransferS transfer = new TransferS();
    
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getDouble("amount"));

        return transfer;
    }
	
}
