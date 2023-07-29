package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IMateriaRepository;
import com.example.demo.repository.modelo.Materia;
import com.example.demo.service.to.MateriaTO;

@Service
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private IMateriaRepository iMateriaRepository;

	@Override
	public Materia seleccionarPorMateria(String asignatura) {
		// TODO Auto-generated method stub
		return this.iMateriaRepository.seleccionarPorNombre(asignatura);
	}

	@Override
	public void registrar(Materia materia) {
		// TODO Auto-generated method stub
		this.iMateriaRepository.insertar(materia);
	}

	@Override
	public void actualizar(Materia materia) {
		// TODO Auto-generated method stub
		this.iMateriaRepository.actualizar(materia);
	}

	@Override
	public MateriaTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.convertir(this.iMateriaRepository.buscarPorId(id));
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.iMateriaRepository.eliminar(id);
	}

	@Override
	public List<MateriaTO> buscarPorCedulaEstudiante(String cedula) {
		// TODO Auto-generated method stub
		return this.iMateriaRepository.buscarPorCedulaEstudiante(cedula).stream().map(mat -> this.convertir(mat))
				.collect(Collectors.toList());
	}

	private MateriaTO convertir(Materia materia) {

		MateriaTO matTO = new MateriaTO();

		matTO.setId(materia.getId());
		matTO.setNumeroCreditos(materia.getNumeroCreditos());
		matTO.setNombre(materia.getNombre());

		return matTO;
	}

}