package com.transaction.fund.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.transaction.fund.model.Account;

/**
 * @author Deepak
 *
 */
@Repository
public class AccountDaoImpl implements AccountDao {

	private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void withdrawAmount(Long accountFrom, Double amount) {
		Session session = this.sessionFactory.getCurrentSession();
		Account account = (Account) session.load(Account.class, new Integer(1));
		account.setAmount(account.getAmount() - amount);
		session.update(account);
		logger.info("Account updated successfully, Account Details=" + account);

	}

	public void depositAmount(Long accountTo, Double amount) {
		Session session = this.sessionFactory.getCurrentSession();
		Account account = (Account) session.load(Account.class, new Integer(2));
		account.setAmount(account.getAmount() + amount);
		session.update(account);
		logger.info("Account updated successfully, Account Details=" + account);

	}

	public Account createAccount(Account account) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(account);

		logger.info("Person saved successfully,  Account Details=" + account);
		return account;
	}

}
