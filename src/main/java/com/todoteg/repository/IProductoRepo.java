package com.todoteg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoteg.models.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Long> {
	
}
