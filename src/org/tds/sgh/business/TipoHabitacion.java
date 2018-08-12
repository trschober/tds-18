package org.tds.sgh.business;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoHabitacion {
	// --------------------------------------------------------------------------------------------

	private String nombre;
	private String codigo;

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

	public TipoHabitacion(String nombre) {
		this.nombre = nombre;
		Random rand = new Random();

		this.codigo = String.valueOf(rand.nextInt(100000));
	}

	// --------------------------------------------------------------------------------------------

	public String getNombre() {
		return this.nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return this.codigo;
	}

	protected void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
