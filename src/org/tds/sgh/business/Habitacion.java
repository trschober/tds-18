package org.tds.sgh.business;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Habitacion {
	// --------------------------------------------------------------------------------------------

	private String nombre;

	private TipoHabitacion tipoHabitacion;

	// --------------------------------------------------------------------------------------------
	private long id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public Habitacion(TipoHabitacion tipoHabitacion, String nombre) {
		this.nombre = nombre;

		this.tipoHabitacion = tipoHabitacion;
	}

	// --------------------------------------------------------------------------------------------

	public String getNombre() {
		return this.nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void settipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public TipoHabitacion gettipoHabitacion() {
		return this.tipoHabitacion;
	}
}
