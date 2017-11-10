package com.transaction.fund.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.fund.dao.AccountDao;
import com.transaction.fund.model.Account;
/**
 * @author Deepak
 *
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao;

	public void setPersonDAO(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public void createAccount(Long accountNumber, Double amount) {
		Account account = new Account(new Long(accountNumber), "Saving", "Deepak", amount == null ? null : amount);
		account = accountDao.createAccount(account);
		System.out.println("Account = " + account);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public void fundTransfer(Long accountFrom, Long accountTo, Double amount) {
		accountDao.withdrawAmount(accountFrom, amount);
		accountDao.depositAmount(accountTo, amount);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public void depositAmount(Long accountNumber, Double amount) {
		accountDao.depositAmount(accountNumber, amount);
	}
}
