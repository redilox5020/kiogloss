package com.todoteg.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoteg.models.Producto;
import com.todoteg.repository.IProductoRepo;

@Service
public class ProductoService {
	
	@Autowired
	private IProductoRepo repo;
    
    public List<Producto> Listar(){
    	return repo.findAll();
    }
    
	public Producto guardar(Producto producto) {
		producto.setFechaCreacion(LocalDateTime.now());
		producto.setFechaActualizacion(LocalDateTime.now());
		return repo.save(producto);
	}
	
	public Producto actualizar(Long id, Producto productoArg) {
		Optional<Producto> producto = repo.findById(id);
		
		Producto productoDb = producto.get();
		
		if(producto.isPresent()) {
			productoDb.setNombres(productoArg.getNombres());
			productoDb.setDescripcion(productoArg.getDescripcion());
			productoDb.setPrecio(productoArg.getPrecio());
			productoDb.setStock(productoArg.getStock());
			return repo.save(productoDb);
		}
		return repo.save(productoArg);
	}
	
	public Optional<Producto> obtenerUno(Long id) {
		return repo.findById(id);
	}
	
	public void eliminar(Long id) {
		repo.deleteById(id);
	}
	
	public List<Producto> guardarTodo(ArrayList<Producto> productos) {
		
		return repo.saveAll(productos);
	}

}
