package org.tds.sgh.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
	// --------------------------------------------------------------------------------------------

	private String direccion;

	private String mail;

	private String nombre;

	private String rut;

	private String telefono;

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

	public Cliente() {
		this.direccion = "";
		this.mail = "";
		this.nombre = "";
		this.rut = "";
		this.telefono = "";
	}

	public Cliente(String rut, String nombre, String direccion, String telefono, String mail) {
		this.direccion = direccion;

		this.mail = mail;

		this.nombre = nombre;

		this.rut = rut;

		this.telefono = telefono;
	}

	// --------------------------------------------------------------------------------------------

	public boolean coincideElNombre(String patronNombreCliente) {
		return this.nombre.matches(patronNombreCliente);
	}

	public String getDireccion() {
		return this.direccion;
	}

	protected void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMail() {
		return this.mail;
	}

	protected void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return this.nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return this.rut;
	}

	protected void setRut(String rut) {
		this.rut = rut;
	}

	public String getTelefono() {
		return this.telefono;
	}

	protected void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
