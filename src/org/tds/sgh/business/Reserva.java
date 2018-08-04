package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Reserva {
	
	private String codigo ;
	private GregorianCalendar fechaFin;
	private GregorianCalendar fechaInicio;
	private Boolean modificacionPorHuesped;
	private EstadoReserva estado;
	private Habitacion habitacion;
	private List<Huesped> huespedes;
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
	//public Habitacion getHabitacion(Integer CodigoHabitacion) throws Exception
	//{
		//Habitacion habitacion = null; //this.habitacion.getHabitacion(CodigoHabitacion);
		
		//if (habitacion == null)
		//{
		//	throw new Exception("No existe una habitacion con el codigo indicado.");
		//}
		
		//return habitacion;
	//}
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
	public Habitacion getHabitacion() {
		return habitacion;
	}
	public void setHabitaciones(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	public List<Huesped> getHuespedes() {
		return huespedes;
	}
	public void setHuespedes(List<Huesped> huespedes) {
		this.huespedes = huespedes;
	}
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
	
	public boolean ToparFecha (GregorianCalendar _fechaInico,GregorianCalendar fechaFin)
	{
		boolean FechaOk =this.fechaInicio.compareTo(_fechaInico)<=0 && this.fechaFin.compareTo(fechaFin)>=0 ;
		return FechaOk && getEstado() == EstadoReserva.TOMADA;	
	}
	public boolean EstaPendiente() {
		return estado.compareTo(EstadoReserva.PENDIENTE) == 0;
	}
	public boolean EstaCancelada() {
		return estado.compareTo(EstadoReserva.CANCELADA) == 0;
	}
	public boolean EstaNoTomada() {
		return estado.compareTo(EstadoReserva.NO_TOMADA) == 0;
	}
	public boolean EstaTomada() {
		return estado.compareTo(EstadoReserva.TOMADA) == 0;
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
