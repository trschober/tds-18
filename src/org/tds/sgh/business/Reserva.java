package org.tds.sgh.business;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Reserva {
	
	private long codigo ;
	private GregorianCalendar fechaFin;
	private GregorianCalendar fechaInicio;
	private boolean modificacionPorHuesped;
	private EstadoReserva estado;
	private Habitacion habitacion;
	private ArrayList<Huesped> huespedes;
	private Hotel hotel ;
	private Cliente cliente ;
	private TipoHabitacion tipoHabitacion;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	} 	
	public Reserva(String nombreTipoHabitacion, GregorianCalendar _fechaInicio, GregorianCalendar _fechaFin, boolean modificable, Cliente c, Hotel h)
	{		
		this.tipoHabitacion = new TipoHabitacion(nombreTipoHabitacion);
		this.codigo = (long)Math.random()*10000;
		this.fechaInicio=_fechaInicio;
		this.fechaFin= _fechaFin;
		this.modificacionPorHuesped = modificable;
		this.habitacion = new Habitacion(this.tipoHabitacion, nombreTipoHabitacion);
		this.estado = EstadoReserva.Pendiente;
		this.huespedes = new ArrayList<Huesped>();
		this.hotel = h;
		this.cliente = c;
	}	
	
	public Reserva()
	{
		this.fechaInicio= new GregorianCalendar();
		this.fechaFin= new GregorianCalendar();
		this.habitacion = new Habitacion(null, null);
		this.estado = EstadoReserva.Pendiente;
		this.codigo = (long)Math.random()*10000;
		//this.huespedes = new ArrayList<>();
	}
	
	
	public Habitacion getHabitacion() throws Exception
	{

		//Habitacion habitacion = null; //this.habitacion.getHabitacion(CodigoHabitacion);
		
		if (habitacion == null)
		{
			throw new Exception("No existe una habitacion con el codigo indicado.");
		}
		
		return this.habitacion;
	}
		
	public Huesped getHuesped(Integer CodigoHuesped) throws Exception
	{
		Huesped huesped = this.huespedes.get(CodigoHuesped);
		
		if (huesped == null)
		{
			throw new Exception("No existe un huesped con el codigo indicado.");
		}
		
		return huesped;
	}
	//public Hotel getHotel(Integer CodigoHotel) throws Exception
	//{
		//Hotel hotel = this.hotel.get(CodigoHotel);
		
		//if (hotel == null)
		//{
			//throw new Exception("No existe un hotel con el codigo indicado.");
		//}
		
		//return hotel;
	//}
	
	public GregorianCalendar getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(GregorianCalendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	public GregorianCalendar getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(GregorianCalendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Boolean getModificacionPorHuesped() {
		return modificacionPorHuesped;
	}
	public void setModificacionPorHuesped(Boolean modificacionPorHuesped) {
		this.modificacionPorHuesped = modificacionPorHuesped;
	}
	public EstadoReserva getEstado() {
		return estado;
	}
	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}
	
	public void setHabitaciones(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	public List<Huesped> getHuespedes() {
		return huespedes;
	}
	//public void setHuespedes(List<Huesped> huespedes) {
	//	this.huespedes = huespedes;
	//}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean ToparFecha(GregorianCalendar fechaInico, GregorianCalendar fechaFin) {
		
		boolean conflicto = true;
		
		if (this.fechaInicio.compareTo(fechaInico) < 0 && this.fechaFin.compareTo(fechaInico) < 0) {
			conflicto = false;
		} else if (this.fechaInicio.compareTo(fechaFin) > 0 && this.fechaFin.compareTo(fechaFin) > 0) {
			conflicto = false;
		} 

		return conflicto;
		
	}
	
	public boolean EstaPendiente() {
		return estado.compareTo(EstadoReserva.Pendiente) == 0;
	}
	public boolean EstaCancelada() {
		return estado.compareTo(EstadoReserva.Cancelada) == 0;
	}
	public boolean EstaNoTomada() {
		return estado.compareTo(EstadoReserva.NoTomada) == 0;
	}
	public boolean EstaTomada() {
		return estado.compareTo(EstadoReserva.Tomada) == 0;
	}
	
	public int CoincideTipoHabitacion(GregorianCalendar _fechaInicio,GregorianCalendar _fechaFin )
	{		
		//Set<Habitacion> HabitacionEncontradas = new HashSet<Habitacion>();
		int Coincide=0;
		//for (Habitacion habitacion : this.Habitaciones.values())
		//{
			//if (habitacion.coincideElNombre(patronNombreCliente))
			//{
				Coincide++;
			//}
		//}		
		return Coincide;
	}
	public int CoincideReserva(GregorianCalendar _fechaInicio,GregorianCalendar _fechaFin )
	{		
		//Set<Habitacion> HabitacionEncontradas = new HashSet<Habitacion>();
		int Coincide=0;
		//for (Habitacion habitacion : this.Habitaciones.values())
		//{
			//if (habitacion.coincideElNombre(patronNombreCliente))
			//{
				Coincide++;
			//}
		//}		
		return Coincide;
	}

	
}
