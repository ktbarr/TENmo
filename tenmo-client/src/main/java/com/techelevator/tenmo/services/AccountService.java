package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Accounts;
import com.techelevator.tenmo.models.AuthenticatedUser;

public class AccountService
{
	private String BASE_URL;
	private RestTemplate restTemplate = new RestTemplate();
	private AuthenticatedUser currentUser;
	private double balance;

	public AccountService(String url, AuthenticatedUser currentUser)
	{
		this.BASE_URL = url + "accounts";
		this.currentUser = currentUser;
	}

	public double getAccountBalanceRequest()
	{
		Accounts accounts = new Accounts();

		try
		{
			double balance = restTemplate.exchange(BASE_URL + "/" + currentUser.getUser().getId() + "/balance",
					HttpMethod.GET, makeAuthEntity(), double.class).getBody();

			accounts.setBalance(balance);
			return accounts.getBalance();

//			System.out.println("Here is your balance: $" + accounts.getBalance());
			
		} catch (RestClientResponseException ex)
		{
			System.err.println("Sorry, that didn't go as planned!");
		}
		return accounts.getBalance();
	}

	/**
	 * Returns an {HttpEntity} with the `Authorization: Bearer:` header
	 *
	 * @return {HttpEntity}
	 */
	private HttpEntity makeAuthEntity()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(currentUser.getToken());
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}

}
