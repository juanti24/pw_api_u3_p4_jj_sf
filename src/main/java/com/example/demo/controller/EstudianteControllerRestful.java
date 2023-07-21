package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;


@RestController
@RequestMapping("/estudiantes")
public class EstudianteControllerRestful {
	
	@Autowired
	private IEstudianteService estudianteService;

	// GET
	@GetMapping(path = "/{cedula}")
	public ResponseEntity<Estudiante> consultarPorCedula(@PathVariable String cedula) {
		//return this.estudianteService.consultarPorCedula(cedula);
		return ResponseEntity.status(227).body(this.estudianteService.consultarPorCedula(cedula));
	}

	// GET
		@GetMapping(path = "/status/{cedula}")
		public ResponseEntity<Estudiante> consultarPorCedulaStatus(@PathVariable String cedula) {
			//return this.estudianteService.consultarPorCedula(cedula);
			return ResponseEntity.status(HttpStatus.OK).body(this.estudianteService.consultarPorCedula(cedula));
		}
	
	@GetMapping
	public ResponseEntity<List<Estudiante>> mostrarTodos() {
		HttpHeaders cabeceras = new HttpHeaders();
				cabeceras.add("detalle mensaje", "Ciudadanos consultados");
		return new ResponseEntity<>(this.estudianteService.mostrarTodos("pichincha"), cabeceras, 228);
		
		
	}

	@PostMapping
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}

	@PutMapping(path = "/{identificador}")
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer identificador) {
		estudiante.setId(identificador);
		this.estudianteService.actualizar(estudiante);

	}

	@PatchMapping(path = "/{identificador}")
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable Integer identificador) {
		Estudiante estu1 = this.estudianteService.buscarPorId(identificador);
		estu1.setCedula(estudiante.getCedula());
		this.estudianteService.actualizarParcial(null, null);

	}

	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);

	}
}
