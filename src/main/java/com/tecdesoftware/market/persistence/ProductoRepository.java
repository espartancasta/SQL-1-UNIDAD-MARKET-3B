package com.tecdesoftware.market.persistence;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.repository.ProductRepository;
import com.tecdesoftware.market.persistence.crud.ProductoCrudRepository;
import com.tecdesoftware.market.persistence.entity.Producto;
import com.tecdesoftware.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProductoRepository implements ProductRepository {
    //Auto inyectado: Spring se encarga de crear la instancia
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;
    //Equivalente a poner Select * from productos
    @Override
    public List<Product> getAll(){
        //Se castea de Iterable a Lista
        List <Producto> productos= (List<Producto>)  productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }
    @Override
    public Optional<List<Product>> getByCategory(int categoryId){
        // CORREGIDO: nombre -> Nombre
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarceProducts(int quantity){
        // CORREGIDO: and -> And
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(productMapper::toProducts);
    }

    @Override
    //Obtener un producto dado el id
    public Optional<Product> getProduct(int productId){
        return productoCrudRepository.findById(productId).map (producto->productMapper.toProduct(producto));
    }
    @Override
    //Guardar un producto
    public Product save(Product product){
        Producto producto=productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }
    @Override
    //eliminar producto por Id
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}


