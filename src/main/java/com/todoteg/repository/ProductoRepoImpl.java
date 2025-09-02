package com.todoteg.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todoteg.models.Producto;

@Repository
public class ProductoRepoImpl implements IProductoRepo{
	
	@Autowired
    private DataSource dataSource;

	@Override
	public List<Producto> findAll() {
		String sql = "SELECT * FROM tbl_producto ORDER BY id DESC";
		List<Producto> productos = new ArrayList<>();
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = statement.executeQuery();
			){
			
			while(rs.next()) {
				Producto producto = new Producto();
				producto.setId(rs.getLong("id"));
				producto.setNombres(rs.getString("nombre_completo"));
				producto.setDescripcion(rs.getString("descripcion_completa"));
				producto.setPrecio(rs.getBigDecimal("precio"));
		        producto.setStock(rs.getInt("stock"));
		        
		        Timestamp fechaCreacion = rs.getTimestamp("fecha_creacion");
		        if(fechaCreacion != null) {
		        	producto.setFechaCreacion(fechaCreacion.toLocalDateTime());
		        }
		        
		        Timestamp fechaActualizacion = rs.getTimestamp("fecha_actualizacion");
		        if(fechaActualizacion != null) {
		        	producto.setFechaActualizacion(fechaActualizacion.toLocalDateTime());
		        }
				productos.add(producto);
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener productos: " + e.getMessage(), e);
		}
		
		
		
		return productos;
	}

	@Override
	public Producto save(Producto newProducto) {
		String sql = "INSERT INTO tbl_producto"
				+ "(nombre_completo, descripcion_completa, precio, stock, fecha_creacion, fecha_actualizacion)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
			){
			statement.setString(1, newProducto.getNombres());
			statement.setString(2, newProducto.getDescripcion());
			statement.setBigDecimal(3, newProducto.getPrecio());
			statement.setInt(4, newProducto.getStock());
			statement.setTimestamp(5, Timestamp.valueOf(newProducto.getFechaCreacion()));
			statement.setTimestamp(6, Timestamp.valueOf(newProducto.getFechaActualizacion()));
			
			int filasAfectadas = statement.executeUpdate();
			
			if(filasAfectadas > 0) {
				try(ResultSet generatedKeys = statement.getGeneratedKeys()){
					if(generatedKeys.next()) {
						newProducto.setId(generatedKeys.getLong(1));
					}
				}
			}
			return newProducto;
		}catch(SQLException e) {
			throw new RuntimeException("Error al crear producto: " + e.getMessage(), e);
		}
		
		
	}
	
	@Override
	public boolean update(Producto producto) {
		String sql = "UPDATE tbl_producto SET nombre_completo=?, descripcion_completa=?, precio=?, stock=?, fecha_actualizacion=? WHERE id= ?";
		
		try(Connection conn = dataSource.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			){
			
			statement.setString(1, producto.getNombres());
			statement.setString(2, producto.getDescripcion());
			statement.setBigDecimal(3, producto.getPrecio());
			statement.setInt(4, producto.getStock());
			statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			statement.setLong(6, producto.getId());
			
			int filasAfectadas = statement.executeUpdate();
			
			return filasAfectadas > 0;
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al actualizar producto: " + e.getMessage(), e);
		}
	}

	@Override
	public Optional<Producto> findById(Long id) {
		String sql = "SELECT * FROM tbl_producto WHERE id=?";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
			){
			
			statement.setLong(1, id);
			
			try(ResultSet rs=statement.executeQuery()){
				if(rs.next()) {
					Producto producto = new Producto();
					producto.setId(rs.getLong("id"));
					producto.setNombres(rs.getString("nombre_completo"));
					producto.setDescripcion(rs.getString("descripcion_completa"));
					producto.setPrecio(rs.getBigDecimal("precio"));
			        producto.setStock(rs.getInt("stock"));
			        
			        Timestamp fechaCreacion = rs.getTimestamp("fecha_creacion");
			        if(fechaCreacion != null) {
			        	producto.setFechaCreacion(fechaCreacion.toLocalDateTime());
			        }
			        
			        Timestamp fechaActualizacion = rs.getTimestamp("fecha_actualizacion");
			        if(fechaActualizacion != null) {
			        	producto.setFechaActualizacion(fechaActualizacion.toLocalDateTime());
			        }
					return Optional.of(producto);
				}
			}
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al obtener producto por ID: " + e.getMessage(), e);
		}
		return Optional.empty();
	}

	@Override
	public boolean deleteById(Long id) {
		String sql = "DELETE FROM tbl_producto WHERE id=?";
		try(Connection conn = dataSource.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql)){
			
			statement.setLong(1, id);
			int filasAfectadas = statement.executeUpdate();
			
			return filasAfectadas > 0;
			
		}catch(SQLException e) {
			throw new RuntimeException("Error al eliminar producto: " + e.getMessage(), e);
		}
		
	}

	@Override
	public List<Producto> saveAll(List<Producto> newProductos) {
		List<Producto> productos = new ArrayList<>();
		
		for(Producto producto: newProductos) {
			productos.add(this.save(producto));
		}
		return productos;
	}
	
	

}
