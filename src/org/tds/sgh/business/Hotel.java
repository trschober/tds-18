package org.tds.sgh.business;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.dtos.TipoHabitacionDTO;
import org.tds.sgh.infrastructure.NotImplementedException;


public class Hotel
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Habitacion> habitaciones;	
	private Map<String, Reserva> reservas;	
	private String nombre;	
	private String pais;
	
	// --------------------------------------------------------------------------------------------
	public Hotel()
	{		
		this.habitaciones =  new HashMap<String, Habitacion>();		
		this.reservas =  new HashMap<String, Reserva>();
		this.nombre = "";		
		this.pais = "";
	}
	
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
			throw new Exception("El hotel ya tiene una habitaciï¿½n con el nombre indicado.");
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
	
	public Reserva seleccionarPorCodigoReserva(String codigoReserva) throws Exception { 
		
		if (!this.reservas.containsKey(codigoReserva))
		{
			throw new Exception("No existe el cï¿½digo de reserva indicado.");
		}
		
		return this.reservas.get(codigoReserva);
	}
	
	public Set<Reserva> getReservas(){
		
		return new HashSet<Reserva>(this.reservas.values());
	}
	
	public boolean confirmarDisponibilidad( String nth, GregorianCalendar fi, GregorianCalendar ff  ) throws Exception {
				
		//boolean Reserva = true;
		int capacidadPorTipHabitacion = this.calcularCapacidad(nth);
		int reservasConConflicto = this.calcularMaxReservasConConflictos(nth, fi, ff);
		
		return capacidadPorTipHabitacion > reservasConConflicto;
		
	}	
	
	//TODO Mejorar algoritmo para calcular conflictos de fechas
	private int calcularMaxReservasConConflictos(String nth, GregorianCalendar fi, GregorianCalendar ff) throws Exception {
		
		int conflicto = 0;
		
		for(Reserva r : this.reservas.values()) {
			
			 if ( r.ToparFecha(fi, ff) && r.getHabitacion().getTipoHabitacion().getNombre().equals(nth) && r.EstaPendiente() ) {
				 conflicto++;
			 }
		}
		
		return conflicto;
	}

	public Set<Reserva> buscarReservasPendientes() {
		
		
		Set<Reserva> reservasPendientes = new HashSet<Reserva>();
		
		for (Reserva reserva : this.reservas.values())
		{
			if (reserva.getEstado() == EstadoReserva.PENDIENTE)
			{
				reservasPendientes.add(reserva);
			}
		}
		
		return reservasPendientes;
	}
	
	public int calcularCapacidad(String nth) {
		
         int capacidad = 0;
		
		for (Habitacion habitacion : this.habitaciones.values())
		{
			//TODO revisar si se compara nombre en lugar de código de habitación
			if (habitacion.getTipoHabitacion().getNombre().equals(nth))
			{
				capacidad++;
			}
		}
		
		return capacidad;
		
	}
}
