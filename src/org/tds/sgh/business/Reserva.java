package org.tds.sgh.business;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reserva {

	private long codigo;
	private GregorianCalendar fechaFin;
	private GregorianCalendar fechaInicio;
	private boolean modificablePorHuesped;
	private EstadoReserva estado;
	private Habitacion habitacion;
	private ArrayList<Huesped> huespedes;
	private Hotel hotel;
	private Cliente cliente;
	private TipoHabitacion tipoHabitacion;

	private long id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Reserva(TipoHabitacion nombreTipoHabitacion, GregorianCalendar _fechaInicio, GregorianCalendar _fechaFin,
			boolean modificable, Cliente c, Hotel h) {
		this.setTipoHabitacion(nombreTipoHabitacion);

		Random rand = new Random();

		this.codigo = (long) rand.nextInt(100000);
		this.fechaInicio = _fechaInicio;
		this.fechaFin = _fechaFin;
		this.modificablePorHuesped = modificable;
		this.habitacion = null;
		this.estado = EstadoReserva.Pendiente;
		this.huespedes = new ArrayList<Huesped>();
		this.hotel = h;
		this.cliente = c;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Habitacion getHabitacion() throws Exception {

		if (habitacion == null) {
			throw new Exception("No existe una habitacion asociada a esta reserva.");
		}

		return this.habitacion;
	}

	public boolean tieneHabitacionAsignada() {
		if (habitacion == null)
			return false;
		return true;
	}

	public Huesped getHuesped(Integer CodigoHuesped) throws Exception {
		Huesped huesped = this.huespedes.get(CodigoHuesped);

		if (huesped == null) {
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

	public ArrayList<Huesped> getHuespedes() {
		return huespedes;
	}

	public void setHuespedes(ArrayList<Huesped> huespedes) {
		this.huespedes = huespedes;
	}

	public void addHuespedes(Huesped huesped) {
		this.huespedes.add(huesped);
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@OneToOne(cascade = CascadeType.ALL)
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

	public String getTipoHabitacion() {
		return tipoHabitacion.getNombre();
	}
//	public String getNombreHabitacion() {
//		if (habitacion == null) {
//			return null;
//		}
//		else {
//		return habitacion.getNombre();
//		}
//	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

}
