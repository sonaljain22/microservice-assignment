/**
 * 
 */
package com.sample.assignment.data.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.assignment.data.bean.TransactionMessageBean;
import com.sample.assignment.mongo.repo.ITransactionMessageRepo;

/**
 * @author sonal
 *
 */
@Service
public class TransactionDaoService {

	private final Logger logger = LoggerFactory.getLogger(TransactionDaoService.class);

	@Autowired
	private ITransactionMessageRepo transactionMessageRepo;

	public Optional<TransactionMessageBean> findById(String id) {
		return transactionMessageRepo.findById(id);
	}

	public Iterable<TransactionMessageBean> findAll() {
		return transactionMessageRepo.findAll();
	}
}
