package com.rahman.webflux_playground.sec02_r2dbc;

import com.rahman.webflux_playground.sec02_r2dbc.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
