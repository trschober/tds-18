package org.tds.sgh.business;

import java.util.Map;
import java.util.Random;

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
		Random rand = new Random();
		
		this.codigo = String.valueOf(rand.nextInt(100000));
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
