package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
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
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	} 
	
	public Reserva(String _Codigo , GregorianCalendar _fechaInicio, GregorianCalendar _fechaFin)
	{
		this.codigo =_Codigo;
		this.fechaInicio=_fechaInicio;
		this.fechaFin= _fechaFin;
		
	}
	
	public Habitacion getHabitacion(Integer CodigoHabitacion) throws Exception
	{
		Habitacion habitacion = this.getHabitacion.get(CodigoHabitacion);
		
		if (habitacion == null)
		{
			throw new Exception("No existe una habitacion con el nombre indicado.");
		}
		
		return Habitacion;
	}
	
	
}
