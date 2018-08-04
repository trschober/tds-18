package org.tds.sgh.business;

import java.util.Map;

public class Reservas {
	
	private String codigo ;
	private String fechaFin;
	private String fechaInicio;
	private Boolean modificacionPorHuesped;
	private EstadoReserva estado;
	private Map<String, Habitacion> habitaciones;
	private Map<String, Huesped> huespedes;
	private Hotel hotel ;
	private Cliente cliente ;
	
	public Habitacion getHabitacion(Integer CodigoHabitacion) throws Exception
	{
		Habitacion cliente = this.clientes.get(CodigoHabitacion);
		
		if (cliente == null)
		{
			throw new Exception("No existe un cliente con el nombre indicado.");
		}
		
		return cliente;
	}
	
	
}
