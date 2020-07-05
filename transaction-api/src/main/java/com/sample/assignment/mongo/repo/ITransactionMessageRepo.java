/**
 * 
 */
package com.sample.assignment.mongo.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sample.assignment.data.bean.TransactionMessageBean;

/**
 * @author sonal
 *
 */
public interface ITransactionMessageRepo extends PagingAndSortingRepository<TransactionMessageBean, String> {

}
