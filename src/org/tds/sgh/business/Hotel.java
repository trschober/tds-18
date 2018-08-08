package org.tds.sgh.business;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.dtos.DTO;
import org.tds.sgh.dtos.ReservaDTO;
import org.tds.sgh.dtos.TipoHabitacionDTO;
import org.tds.sgh.infrastructure.NotImplementedException;


public class Hotel
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Habitacion> habitaciones;	
	private Map<String, Reserva> reservas;	
	private String nombre;	
	private String pais;
	private final DTO DTO = org.tds.sgh.dtos.DTO.getInstance();
	
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
		this.reservas =  new HashMap<String, Reserva>();
		this.nombre = nombre;		
		this.pais = pais;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public Habitacion agregarHabitacion(TipoHabitacion tipoHabitacion, String nombre) throws Exception
	{
		if (this.habitaciones.containsKey(nombre))
		{
			throw new Exception("El hotel ya tiene una habitaci�n con el nombre indicado.");
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
			throw new Exception("No existe el c�digo de reserva indicado.");
		}
		
		return this.reservas.get(codigoReserva);
	}
	
	public Set<Reserva> getReservas(){
		
		return new HashSet<Reserva>(this.reservas.values());
	}
	
	public boolean confirmarDisponibilidad(
			String nth, 
			GregorianCalendar fi, 
			GregorianCalendar ff  
			) throws Exception {
				
		//boolean Reserva = true;
		int capacidadPorTipHabitacion = this.calcularCapacidad(nth);
		int reservasConConflicto = this.calcularMaxReservasConConflictos(nth, fi, ff);
		
		return capacidadPorTipHabitacion > reservasConConflicto;
		
	}	
	
	//TODO Mejorar algoritmo para calcular conflictos de fechas
	private int calcularMaxReservasConConflictos(
			String nth, 
			GregorianCalendar fi, 
			GregorianCalendar ff
			) throws Exception {
		
		int conflicto = 0;
		
		for(Reserva r : this.reservas.values()) {
			
			// if ( r.ToparFecha(fi, ff) && r.getTipoHabitacion().equals(nth) && r.EstaPendiente() ) {
			if (r.getTipoHabitacion().equals(nth)
					&& fi.compareTo(r.getFechaFin()) < 0
					&& ff.compareTo(r.getFechaInicio()) > 0
					&& r.EstaPendiente()) {
				conflicto++;
			 }
		}
		
		return conflicto;
	}

	public Set<Reserva> buscarReservasPendientes() {
		
		
		Set<Reserva> reservasPendientes = new HashSet<Reserva>();
		
		for (Reserva reserva : this.reservas.values())
		{
			if (reserva.EstaPendiente())
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
			//TODO revisar si se compara nombre en lugar de c�digo de habitaci�n
			if (habitacion.getTipoHabitacion().equals(nth))
			{
				capacidad++;
			}
		}
		
		return capacidad;
		
	}

	public Set<Reserva> obtenerReservasCliente(Cliente C)
	{
		Set<Reserva> Obj = new HashSet<>();
		 
		for (Reserva r : this.reservas.values())
		{
			
			if (r.getCliente()==C)
			{
				Obj.add(r);
			}
		}
		
		return Obj; 
		
	}
	
	public Reserva registrarReserva(TipoHabitacion nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificable, Cliente cliente, Hotel h) throws Exception {
		
		Reserva r = new Reserva(
				nombreTipoHabitacion,
				fechaInicio,
				fechaFin,
				modificable,
				cliente,
				h);
		
		reservas.put(Long.toString(r.getCodigo()),r);
		
		
		return r;
	}
	public Reserva registrarReserva(Reserva r) {
		
		reservas.put(Long.toString(r.getCodigo()),r);
		
		return r;
	}
	
	public void eliminarReserva(Reserva reserva) {
		
		for (Reserva r : this.reservas.values())
		{
			
			if (r.getCodigo() == reserva.getCodigo())
			{
				reservas.remove(Long.toString(reserva.getCodigo()));
			}
		}
		
	}

	public Habitacion buscarHabitacionLibre(String tipoHabitacion, 
			GregorianCalendar fi, 
			GregorianCalendar ff) {
		
		Set<Reserva> resUsadas = new HashSet<Reserva>();
		
		for(Reserva r : this.reservas.values()) {
			
			if (r.getTipoHabitacion().equals(tipoHabitacion)
					&& fi.compareTo(r.getFechaFin()) < 0
					&& ff.compareTo(r.getFechaInicio()) > 0
					&& r.EstaTomada()) {
				resUsadas.add(r);
			 }
		}
		
		for (Habitacion habitacion : this.habitaciones.values())
		{
			if (habitacion.getTipoHabitacion().equals(tipoHabitacion))
			{
				for (Reserva reserva : resUsadas) {
					if (!habitacion.getNombre().equals(reserva.getNombreHabitacion())) {
						return habitacion;
					}
				}
			}
		}
		return null;
	}
}
