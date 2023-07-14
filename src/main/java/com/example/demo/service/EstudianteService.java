package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;





@Service
public class EstudianteService implements IEstudianteService {
	@Autowired
	private IEstudianteRepository estudianteRepository;

	@Override
	public Estudiante consultarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.seleccionarPorCedula(cedula);
	}

	@Override
	public void guardar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(estudiante);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizar(estudiante);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.borrar(id);
	}

	@Override
	public List<Estudiante> buscarTodos() {
		// TODO Auto-generated method stub
		return this.estudianteRepository.buscarTodos();
	}

}
