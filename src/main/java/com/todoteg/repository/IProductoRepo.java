package com.todoteg.repository;

import java.util.List;
import java.util.Optional;

import com.todoteg.models.Producto;

public interface IProductoRepo {
	
	List<Producto> findAll();
	Producto save(Producto newProducto);
	boolean update(Producto producto);
	Optional<Producto> findById(Long id);
	boolean deleteById(Long id);
	List<Producto> saveAll(List<Producto> newProductos);
}
