package com.ibm.academy.microservices.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "carreras", schema = "universidad")
public class Carrera implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre", unique = true, nullable = false, length = 80)
	private String nombre;
	@Column(name = "cantidad_materia")
	private Integer cantidadMaterias;
	@Column(name = "cantidad_annos")
	private Integer cantidadAnnos;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	@OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY)
	private Set<Alumno> alumnos;
	
	@ManyToMany(mappedBy = "carreras", fetch = FetchType.LAZY)
	private Set<Profesor> profesores;

	public Carrera(Integer id, String nombre, Integer cantidadMaterias, Integer cantidadAnnos) {
		this.id = id;
		this.nombre = nombre;
		this.cantidadMaterias = cantidadMaterias;
		this.cantidadAnnos = cantidadAnnos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrera other = (Carrera) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
	@PrePersist
	private void antesPersistir() {
		this.fechaCreacion = new Date();
	}
	@PreUpdate
	private void antesModificar() {
		this.fechaModificacion = new Date();
	}
	private static final long serialVersionUID = 6250738151370169265L;

}
