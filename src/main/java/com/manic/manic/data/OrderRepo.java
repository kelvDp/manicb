package com.manic.manic.data;

import com.manic.manic.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {
}
