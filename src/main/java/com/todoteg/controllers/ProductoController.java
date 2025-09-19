package com.todoteg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoteg.exception.EntityNotFoundException;
import com.todoteg.models.Producto;
import com.todoteg.services.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@GetMapping
	public List<Producto> listar(){
		return service.Listar();
	}
	
	@DeleteMapping("/{id}")
	public void borrar(@PathVariable Long id) {
		service.eliminar(id);
	}
	
	@GetMapping("/{id}")
	public Producto ListarUno(@PathVariable Long id){
		return service.obtenerUno(id);
	}
	
	@PatchMapping("/{id}")
	public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
		return service.actualizar(id, producto);
	}
	
	@PostMapping("/crear")
	public Producto registrar(@RequestBody Producto producto) {
		
		return service.guardar(producto);
	}
}
