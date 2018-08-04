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
		Habitacion habitacion = this.habitacion.get(CodigoHabitacion);
		
		if (habitacion == null)
		{
			throw new Exception("No existe una habitacion con el nombre indicado.");
		}
		
		return Habitacion;
	}
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
	public Map<String, Habitacion> getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(Map<String, Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	public Map<String, Huesped> getHuespedes() {
		return huespedes;
	}
	public void setHuespedes(Map<String, Huesped> huespedes) {
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
	
	
}
