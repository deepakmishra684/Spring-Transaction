package com.transaction.fund.service;

/**
 * @author Deepak
 *
 */
public interface AccountService {
	void createAccount(Long accountNumber, Double amount);

	public void depositAmount(Long accountNumber, Double amount);

	void fundTransfer(Long accountFrom, Long accountTo, Double amount);

}
