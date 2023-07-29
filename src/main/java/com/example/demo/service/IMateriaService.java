package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.to.MateriaTO;

public interface IMateriaService {

	public Materia seleccionarPorMateria(String asignatura);

	public void registrar(Materia materia);

	public void actualizar(Materia materia);

	public MateriaTO buscarPorId(Integer id);

	public void borrar(Integer id);

	public List<MateriaTO> buscarPorCedulaEstudiante(String cedula);

}