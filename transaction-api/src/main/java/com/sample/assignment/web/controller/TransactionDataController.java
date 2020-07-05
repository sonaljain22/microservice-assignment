/**
 * 
 */
package com.sample.assignment.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sample.assignment.data.bean.TransactionMessageBean;
import com.sample.assignment.data.service.TransactionDaoService;
import com.sample.assignment.web.dto.TransactionMessageDto;

/**
 * @author sonal
 *
 */
@RestController
@RequestMapping(value = "/api/transaction")
public class TransactionDataController {

	@Autowired
	private TransactionDaoService transactionDaoService;

	@GetMapping(value = "/{id}")
	public TransactionMessageDto findOne(@PathVariable String id) {
		TransactionMessageBean entity = transactionDaoService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return convertTransactionEntityToDto(entity);
	}

	@GetMapping
	public Collection<TransactionMessageDto> findAll() {
		Iterable<TransactionMessageBean> entities = this.transactionDaoService.findAll();
		List<TransactionMessageDto> entityList = new ArrayList<>();
		entities.forEach(txn -> entityList.add(convertTransactionEntityToDto(txn)));
		return entityList;
	}

	private TransactionMessageDto convertTransactionEntityToDto(TransactionMessageBean entity) {
		return new TransactionMessageDto(entity);
	}
}
