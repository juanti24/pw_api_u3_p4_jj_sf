package com.example.demo.service;

import com.example.demo.repository.modelo.Estudiante;

public interface IEstudianteService {

	public Estudiante consultarPorCedula(String cedula);
	
}
