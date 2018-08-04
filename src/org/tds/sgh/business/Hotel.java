package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.infrastructure.NotImplementedException;


public class Hotel
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Habitacion> habitaciones;
	
	private String nombre;
	
	private String pais;
	
	// --------------------------------------------------------------------------------------------
	
	public Hotel(String nombre, String pais)
	{
		this.habitaciones = new HashMap<String, Habitacion>();
		
		this.nombre = nombre;
		
		this.pais = pais;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public Habitacion agregarHabitacion(TipoHabitacion tipoHabitacion, String nombre) throws Exception
	{
		if (this.habitaciones.containsKey(nombre))
		{
			throw new Exception("El hotel ya tiene una habitación con el nombre indicado.");
		}
		
		Habitacion habitacion = new Habitacion(tipoHabitacion, nombre);
		
		this.habitaciones.put(habitacion.getNombre(), habitacion);
		
		return habitacion;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getPais()
	{
		return this.pais;
	}
	
	public Set<Habitacion> listarHabitaciones()
	{
		return new HashSet<Habitacion>(this.habitaciones.values());
	}
	
	public Reserva getReserva(int numerocliente) { 
		
		throw new NotImplementedException();
	}
	
	public Set<Reserva> getReservas(){
		throw new NotImplementedException();
	}
	
	public Boolean confirmarDisponibilidad( TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff  ) {
		throw new NotImplementedException();
	}
	
	public Set<Reserva> buscarReservasPendientes() {
		throw new NotImplementedException();
	}
	
	public int calcularCapacidad(TipoHabitacion th, GregorianCalendar fi, GregorianCalendar ff  ) {
		throw new NotImplementedException();
	}
	
	
}
