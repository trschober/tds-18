package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.infrastructure.ICalendario;
import org.tds.sgh.infrastructure.Infrastructure;


public class Hotel
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Habitacion> habitaciones;	
	private Map<String, Reserva> reservas;	
	private String nombre;	
	private String pais;
	private ICalendario calendario;
	
	// --------------------------------------------------------------------------------------------
	public Hotel()
	{		
		this.habitaciones =  new HashMap<String, Habitacion>();		
		this.reservas =  new HashMap<String, Reserva>();
		this.nombre = "";		
		this.pais = "";
		this.calendario = Infrastructure.getInstance().getCalendario();

	}
	
	public Hotel(String nombre, String pais)
	{
		this.habitaciones = new HashMap<String, Habitacion>();	
		this.reservas =  new HashMap<String, Reserva>();
		this.nombre = nombre;		
		this.pais = pais;
		this.calendario = Infrastructure.getInstance().getCalendario();

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
			GregorianCalendar ff,
			boolean modificando
			) throws Exception {
				
		int capacidadPorTipHabitacion = this.calcularCapacidad(nth);
		int reservasConConflicto = this.calcularMaxReservasConConflictos(nth, fi, ff);
				
		return capacidadPorTipHabitacion > reservasConConflicto;
		
	}	
	
	private int calcularMaxReservasConConflictos(
			String nth, 
			GregorianCalendar fi, 
			GregorianCalendar ff
			) throws Exception {
		
		int conflicto = 0;
		
		for(Reserva r : this.reservas.values()) {
			
			if (r.getTipoHabitacion().equals(nth) && r.EstaPendiente()){
					if ((r.getFechaInicio().before(fi) && r.getFechaFin().after(fi))
						|| (r.getFechaInicio().before(ff) && r.getFechaInicio().after(fi))
						|| (r.getFechaInicio().equals(fi))
						|| (r.getFechaFin().equals(ff))
					){
					conflicto++;
			 		}
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
			
			if (r.getCliente()==C
				&& r.EstaPendiente()
				&& !calendario.esPasada(r.getFechaInicio()))
			{
				Obj.add(r);
			}
		}
		
		return Obj; 
		
	}
	
	public Reserva registrarReserva(TipoHabitacion tipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificable, Cliente cliente, Hotel h) throws Exception {
		
		Reserva r = new Reserva(
				tipoHabitacion,
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
		
		boolean eliminar = false;
		for (Reserva r : this.reservas.values())
		{
			if (r.getCodigo() == reserva.getCodigo())
			{
				eliminar = true;
			}
		}
		if (eliminar) {
			reservas.remove(Long.toString(reserva.getCodigo()));
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
