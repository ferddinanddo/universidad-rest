package com.ibm.academy.microservices.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Table(name = "alumnos", schema = "universidad")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Alumno extends Persona {
	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "carrera_id")
	private Carrera carrera; 
	
	public Alumno(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
		super(id, nombre, apellido, dni, direccion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString() + "Alumno []";
	}

	private static final long serialVersionUID = -8996305743467884407L;
}
