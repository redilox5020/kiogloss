package com.todoteg;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.todoteg.models.Producto;
import com.todoteg.repository.IProductoRepo;

@SpringBootApplication
public class KioglossApplication {

	public static void main(String[] args) {
		SpringApplication.run(KioglossApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(IProductoRepo repository) {
		return args -> {

			ArrayList<Producto> listaProductos = new ArrayList<>();

			Producto producto1 = new Producto();
			producto1.setId(Long.valueOf(1));
			producto1.setNombres("Nintendo Switch");
			producto1.setDescripcion("...........");
			producto1.setPrecio(BigDecimal.valueOf(54.6));
			producto1.setStock(102);
			producto1.setFechaCreacion(LocalDateTime.now());
			producto1.setFechaActualizacion(LocalDateTime.now());

			Producto producto2 = new Producto("Nintendo Switch 2", "xxxx", BigDecimal.valueOf(54.00), 100,
					LocalDateTime.now(), LocalDateTime.now());

			producto2.setId(Long.valueOf(2));

			listaProductos.add(producto1);
			listaProductos.add(producto2);
			
			repository.saveAll(listaProductos);
		};
	}

}
