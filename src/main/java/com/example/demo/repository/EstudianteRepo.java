package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;



@Repository
@Transactional
public class EstudianteRepo implements IEstudianteRepo {

	
	
	private EntityManager eM;
	@Override
	public Estudiante seleccionarPorCedula(String cedula) {
		TypedQuery<Estudiante> myQuery= this.eM.createQuery("SELECT e FROM Estudainte e WHERE e.cedula = datoCedula", Estudiante.class);
		myQuery.setParameter("datoCedula", cedula);
				return null;
	}

	
}
