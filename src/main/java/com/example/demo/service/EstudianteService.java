package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IEstudianteRepository;
import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteTO;

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
	public void actualizarParcial(String cedulaActual, String cedulaNueva) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizarParcial(cedulaActual, cedulaNueva);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.borrar(id);
	}

	@Override
	public Estudiante buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.buscarPorId(id);
	}

	@Override
	public List<Estudiante> mostrarTodos(String provincia) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.buscarTodos(provincia);
	}

	@Override
	public List<EstudianteTO> buscarTodos() {
		// TODO Auto-generated method stub
		List<Estudiante> lista = this.estudianteRepository.buscarTodos();
		List<EstudianteTO> listaTO = lista.stream().map(estudiante -> this.convertir(estudiante)).collect(Collectors.toList());
		return listaTO;
	}
	
	@Override
	public Integer guardarConRetorno(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(estudiante);
		return this.estudianteRepository.seleccionarPorCedula(estudiante.getCedula()).getId();
	}
	
	
	private EstudianteTO convertir(Estudiante estudiante) {
		EstudianteTO est = new EstudianteTO();
		est.setId(estudiante.getId());
		est.setApellido(estudiante.getApellido());
		est.setCedula(estudiante.getCedula());
		est.setFechaNacimiento(estudiante.getFechaNacimiento());
		est.setNombre(estudiante.getNombre());
		est.setProvincia(estudiante.getProvincia());
		
		return est;
	}

}