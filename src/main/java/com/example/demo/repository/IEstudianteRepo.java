package com.example.demo.repository;



import com.example.demo.modelo.Estudiante;




public interface IEstudianteRepo  {

	public Estudiante seleccionarPorCedula(String cedula);
}
