package com.todoteg.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre_completo", nullable=false, unique=false)
	private String nombres;
	@Column(name="descripcion_completa", nullable=false, unique=false)
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
