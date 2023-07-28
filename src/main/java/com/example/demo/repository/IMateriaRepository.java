package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.to.MateriaTO;

public interface IMateriaRepository {
	
	public List<Materia> buscarPorCedulaEstudiante(String cedula);

	public MateriaTO buscarPorId(Integer id);
}
