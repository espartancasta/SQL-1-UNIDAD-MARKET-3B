package com.tecdesoftware.market_app.presistence.crud;


import com.tecdesoftware.market.persistance.entity.Productos;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository extends CrudRepository<Productos, Integer> {

}
