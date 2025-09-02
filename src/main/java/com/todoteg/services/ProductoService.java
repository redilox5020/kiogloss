package com.todoteg.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.todoteg.models.Producto;
import com.todoteg.repository.IProductoRepo;

@Service
public class ProductoService {
	
	private final IProductoRepo repo;

    public ProductoService(IProductoRepo productoRepository) {
        this.repo = productoRepository;
    }
	
    
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
		
		
		if(!producto.isPresent()) {
			throw new RuntimeException("No se puede actualizar. Producto con id " + id + " no encontrado.");
			// return repo.save(productoArg);
		};
		
		Producto productoDb = producto.get();
		productoDb.setNombres(productoArg.getNombres());
		productoDb.setDescripcion(productoArg.getDescripcion());
		productoDb.setPrecio(productoArg.getPrecio());
		productoDb.setStock(productoArg.getStock());
		
		if(repo.update(productoDb)) return productoDb;
		
		throw new RuntimeException("La actualización en la base de datos no afectó ninguna fila para el id: " + id);
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
