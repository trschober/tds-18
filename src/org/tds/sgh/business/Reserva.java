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
	private boolean modificablePorHuesped;
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
	public Reserva(TipoHabitacion nombreTipoHabitacion, GregorianCalendar _fechaInicio, GregorianCalendar _fechaFin, boolean modificable, Cliente c, Hotel h)
	{		
		this.setTipoHabitacion(nombreTipoHabitacion);
		this.codigo = (long)Math.random()*10000;
		this.fechaInicio=_fechaInicio;
		this.fechaFin= _fechaFin;
		this.modificablePorHuesped = modificable;
		this.habitacion = null;
		this.estado = EstadoReserva.Pendiente;
		this.huespedes = new ArrayList<Huesped>();
		this.hotel = h;
		this.cliente = c;
	}	
	
	
	public Habitacion getHabitacion() throws Exception
	{

		if (habitacion == null)
		{
			throw new Exception("No existe una habitacion asociada a esta reserva.");
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
	public Boolean getModificablePorHuesped() {
		return modificablePorHuesped;
	}
	public void setModificablePorHuesped(Boolean modificacionPorHuesped) {
		this.modificablePorHuesped = modificacionPorHuesped;
	}
	public EstadoReserva getEstado() {
		return estado;
	}
	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}
	
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	public List<Huesped> getHuespedes() {
		return huespedes;
	}
	
	public void addHuespedes(Huesped huesped) {
		this.huespedes.add(huesped);
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
	public String getRutCliente() {
		return cliente.getRut();
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
	public String getTipoHabitacion() {
		return tipoHabitacion.getNombre();
	}
	public String getNombreHabitacion() {
		if (habitacion == null) {
			return null;
		}
		else {
		return habitacion.getNombre();
		}
	}
	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	public String getNombreHotel() {
		return this.hotel.getNombre();
	}

	
}
