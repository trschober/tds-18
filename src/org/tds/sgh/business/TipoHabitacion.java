package org.tds.sgh.business;

import java.util.Map;

public class TipoHabitacion
{
	// --------------------------------------------------------------------------------------------
	
	private String nombre;
	private String codigo;
	private Map<String, Habitacion> habitaciones;
	
	// --------------------------------------------------------------------------------------------
	
	public TipoHabitacion(String nombre)
	{
		this.nombre = nombre;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public String getNombre()
	{
		return this.nombre;
	}
	public String getCodigo()
	{
		return this.codigo;
	}
}
