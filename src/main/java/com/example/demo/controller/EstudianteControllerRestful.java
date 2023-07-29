package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;
import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;

@RestController
@RequestMapping("/estudiantes")
@CrossOrigin
public class EstudianteControllerRestful {
	
	@Autowired
	private IEstudianteService estudianteService;
	@Autowired
	private IMateriaService iMateriaService;


	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Estudiante consultarPorCedula(@PathVariable String cedula) {
		// String cedula = "1750844787";
		return this.estudianteService.consultarPorCedula(cedula);
	}
	@GetMapping(path = "/status/{cedula}")
	public ResponseEntity<Estudiante> consultarPorCedulaStatus(@PathVariable String cedula) {
		// String cedula = "1750844787";
		return ResponseEntity.status(HttpStatus.OK).body(this.estudianteService.consultarPorCedula(cedula));
	}
	@GetMapping
	public ResponseEntity<List<Estudiante>> mostrarTodos(@RequestParam String provincia) {
		List<Estudiante> lista = this.estudianteService.mostrarTodos(provincia);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("detalleMensaje", "Ciudadanos consultados exitosamente");
		cabeceras.add("valorApi", "Incalculable");
		return new ResponseEntity<>(lista, cabeceras, 228);
	}
	// POST
	@PostMapping(consumes = "application/xml")
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	@PostMapping(path = "/g", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Integer guardarConRetorno(@RequestBody Estudiante estudiante) {
		return this.estudianteService.guardarConRetorno(estudiante);
	}
	@PostMapping(path = "/g2", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Estudiante guardarConRetorno2(@RequestBody Estudiante estudiante) {
		Estudiante e = new Estudiante();
		e.setId(this.estudianteService.guardarConRetorno(estudiante));
		e.setCedula(estudiante.getCedula());
		return e;
	}
	// PUT
	@PutMapping(path = "/{identificador}")
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer identificador) {
		estudiante.setId(identificador);
		this.estudianteService.actualizar(estudiante);
	}
	// PATCH
	@PatchMapping(path = "/{identificador}")
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable Integer identificador) {
		Estudiante estu1 = this.estudianteService.buscarPorId(identificador);
		this.estudianteService.actualizarParcial(estu1.getCedula(), estudiante.getCedula());
	}
	// DELETE
	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
	}
	@GetMapping(path = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstudianteTO>> mostrarTodosHATEOAS() {
		List<EstudianteTO> lista = this.estudianteService.buscarTodos();
		// return ResponseEntity.status(HttpStatus.OK).body(lista);
		for (EstudianteTO e : lista) {
			Link myLink = linkTo(methodOn(EstudianteControllerRestful.class).buscarPorEstudiante(e.getCedula()))
					.withRel("materias");
			e.add(myLink);
		}
		return new ResponseEntity<>(lista, null, 200);
	}
	@GetMapping(path = "/{cedula}/materias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MateriaTO>> buscarPorEstudiante(@PathVariable String cedula) {
		List<MateriaTO> lista = this.iMateriaService.buscarPorCedulaEstudiante(cedula);
		for (MateriaTO mat : lista) {
			Link myLink = linkTo(methodOn(MateriaControllerRestful.class).consultarPorId(mat.getId()))
					.withSelfRel();
			mat.add(myLink);
		}

		return new ResponseEntity<>(lista, null, 200);
	}
}