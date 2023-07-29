package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.MateriaTO;

@RestController
@RequestMapping("/materias")
public class MateriaControllerRestful {

	@Autowired
	private IMateriaService iMateriaService;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MateriaTO consultarPorId(@PathVariable Integer id) {
		return this.iMateriaService.buscarPorId(id);
	}

	@PostMapping(path = "/guardar")
	public void guardar(@RequestBody Materia materia) {
		this.iMateriaService.registrar(materia);
	}

	@PutMapping(path = "/actualizar/{identificador}")
	public void actualizar(@RequestBody Materia materia, @PathVariable Integer identificador) {
		materia.setId(identificador);
		this.iMateriaService.actualizar(materia);
	}

	@DeleteMapping(path = "/eliminar/{id}")
	public void borrar(@PathVariable Integer id) {
		this.iMateriaService.borrar(id);
	}
}