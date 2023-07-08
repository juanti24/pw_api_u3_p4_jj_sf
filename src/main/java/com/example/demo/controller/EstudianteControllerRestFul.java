package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;

@RestController
//Debe hacer alucion al controlador
//Debe estar en plural

@RequestMapping("/estudiantes")
public class EstudianteControllerRestFul {
	
	@Autowired
	private IEstudianteService estudianteService;

	//Tener claro el metodo (Get)
	@GetMapping(path = "/buscar")
	public Estudiante consultarPorCedula() {
		String cedula = "1723920979";
		return this.estudianteService.consultarPorCedula(cedula);
		
	}

}
