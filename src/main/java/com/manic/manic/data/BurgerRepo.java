package com.manic.manic.data;

import com.manic.manic.Burger;

import org.springframework.data.repository.CrudRepository;

public interface BurgerRepo extends CrudRepository<Burger, Long>  {
}
