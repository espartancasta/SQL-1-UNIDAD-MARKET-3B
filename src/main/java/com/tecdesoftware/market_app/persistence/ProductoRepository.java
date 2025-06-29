package com.tecdesoftware.market_app.persistence;


import com.tecdesoftware.market.persistance.crud.ProductoCrudRepository;
import com.tecdesoftware.market.persistance.entity.Productos;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    //Me va a dar todos mis productos de mi BD
    public List<Productos> getAll() {
        //Convirtiendo un Iterable <T> a una lista de productos List<Producto>
        return (List<Productos>) productoCrudRepository.findAll();
    }

}
