package com.ibm.academy.microservices.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.ibm.academy.microservices.enums.TipoPizarron;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "aulas", schema = "universidad")
public class Aula implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "numero_aula", nullable = false)
	private Integer numeroAula;
	@Column(name = "medidas")
	private String medidas;
	@Column(name = "cantidad_pupitres")
	private Integer cantidadPupitres;
	@Column(name = "tipo_pizarron")
	private TipoPizarron tipoPizarron;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	@ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
	private Pabellon pabellon;
	

	public Aula(Integer id, Integer numeroAula, String medidas, Integer cantidadPupitres, TipoPizarron tipoPizarron) {
		this.id = id;
		this.numeroAula = numeroAula;
		this.medidas = medidas;
		this.cantidadPupitres = cantidadPupitres;
		this.tipoPizarron = tipoPizarron;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numeroAula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(id, other.id) && Objects.equals(numeroAula, other.numeroAula);
	}

	@PrePersist
	private void antesPersistir() {
		this.fechaCreacion = new Date();
	}
	@PreUpdate
	private void antesModificar() {
		this.fechaModificacion = new Date();
	}
	private static final long serialVersionUID = -1292345823316141765L;

}
