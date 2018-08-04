package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.Map;

public class Reserva {
	
	private String codigo ;
	private GregorianCalendar fechaFin;
	private GregorianCalendar fechaInicio;
	private Boolean modificacionPorHuesped;
	private EstadoReserva estado;
	private Map<String, Habitacion> habitaciones;
	private Map<String, Huesped> huespedes;
	private Hotel hotel ;
	private Cliente cliente ;
	
	public Habitacion getHabitacion(Integer CodigoHabitacion) throws Exception
	{
		Habitacion cliente = this.getHabitacion.get(CodigoHabitacion);
		
		if (cliente == null)
		{
			throw new Exception("No existe un cliente con el nombre indicado.");
		}
		
		return cliente;
	}
	
	
}
