package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.MateriaTO;

@RestController
@RequestMapping("/materias")
public class MateriaControllerRestful {
	
	@Autowired
	private IMateriaService iMateriaService;
	
	
	
		@GetMapping(path = "/{id}")
		@ResponseStatus(HttpStatus.OK)
		public MateriaTO consultarPorId(@PathVariable Integer id) {
			return this.iMateriaService.buscarPorId(id);
		}

	

}
