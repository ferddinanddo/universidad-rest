package com.ibm.academy.microservices.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "personas", schema = "universidad")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "nombre", nullable = false, length = 80)
	private String nombre;
	@Column(name = "apellido", nullable = false, length = 80)
	private String apellido;
	@Column(name = "dni", nullable = false, unique = true, length = 10)
	private String dni;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
		@AttributeOverride(name = "departamento", column = @Column(name = "departamento"))
	})
	private Direccion direccion;
	public Persona(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.direccion = direccion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(id, other.id);
	}
	@PrePersist
	private void antesPersistir() {
		this.fechaCreacion = new Date();
	}
	@PreUpdate
	private void antesModificar() {
		this.fechaModificacion = new Date();
	}

	private static final long serialVersionUID = 8153254058861998339L;
}