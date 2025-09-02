package com.todoteg.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Producto {

	private Long id;
	private String nombres;
	private String descripcion;
	private BigDecimal precio;
	private Integer stock;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaActualizacion;
	
	public Producto() {
		
	}
	
	
	public Producto(String nombres, String descripcion, BigDecimal precio, Integer stock,
			LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		this.nombres = nombres;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombres=" + nombres + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", stock=" + stock + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion
				+ "]";
	}
	
	
	
}
