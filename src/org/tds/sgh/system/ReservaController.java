package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.EstadoReserva;
import org.tds.sgh.business.Habitacion;
import org.tds.sgh.business.Hotel;
import org.tds.sgh.business.Huesped;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.business.TipoHabitacion;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.DTO;
import org.tds.sgh.dtos.HabitacionDTO;
import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.ReservaDTO;
import org.tds.sgh.dtos.TipoHabitacionDTO;
import org.tds.sgh.infrastructure.*;

public class ReservaController implements IHacerReservaController, ITomarReservaController, ICancelarReservaController, IModificarReservaController, ICadenaController {

	private final DTO DTO = org.tds.sgh.dtos.DTO.getInstance();
	
	// --------------------------------------------------------------------------------------------
	
	private CadenaHotelera cadenaHotelera;
	private Hotel hotel;
	private Cliente cliente;
	private Reserva reserva;
	private boolean modificando;
	private ISistemaMensajeria sistemaMensajeria;
	private ISistemaFacturacion sistemaFacturacion;
	private ICalendario calendario;
	
	// --------------------------------------------------------------------------------------------
	
	public ReservaController(CadenaHotelera cadenaHotelera)
	{
		this.cadenaHotelera = cadenaHotelera;
		sistemaMensajeria = Infrastructure.getInstance().getSistemaMensajeria();
		sistemaFacturacion = Infrastructure.getInstance().getSistemaFacturacion();
		calendario = Infrastructure.getInstance().getCalendario();
		this.modificando = false;
	}
	
	@Override
	public Set<ClienteDTO> buscarCliente(String patronNombreCliente){
	
		return DTO.mapClientes(cadenaHotelera.buscarClientes(patronNombreCliente));
	}

	@Override
	public ClienteDTO seleccionarCliente(String rut) throws Exception {		
		if (rut == null)
		{
			throw new Exception("cliente incorrecto");
		}
		
		this.cliente = cadenaHotelera.buscarCliente(rut);
		return DTO.map(cadenaHotelera.buscarCliente(rut));		
	}

	@Override
	public ClienteDTO registrarCliente(String rut, String nombre, String direccion, String telefono, String mail)
			throws Exception {
		
		ClienteDTO clienteRegistrado = DTO.map(cadenaHotelera.agregarCliente(rut, nombre, direccion, telefono, mail));

		this.cliente = cadenaHotelera.buscarCliente(rut);
		
		return clienteRegistrado;
	}

	@Override
	public boolean confirmarDisponibilidad(
			String nombreHotel, 
			String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, 
			GregorianCalendar fechaFin
			) throws Exception {
		
		if (this.calendario.esPasada(fechaInicio)
			|| this.calendario.esPosterior(fechaInicio, fechaFin))
		{
			throw new Exception("Fechas incorrectas");
		}
		
		if (this.reserva != null
			&& this.modificando	
				) {
			if (this.hotel.seleccionarPorCodigoReserva(Long.toString(this.reserva.getCodigo())).getCodigo()
				 == this.reserva.getCodigo()	
					){
					return true;
			}
		}
		
		return cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public ReservaDTO registrarReserva(
			String nombreHotel, 
			String nombreTipoHabitacion, 
			GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, 
			boolean modificablePorHuesped
			) throws Exception {
		this.reserva = cadenaHotelera.registrarReserva(
				nombreHotel, 
				nombreTipoHabitacion, 
				fechaInicio, 
				fechaFin, 
				modificablePorHuesped, 
				cliente);
		this.hotel = cadenaHotelera.buscarHotel(nombreHotel);
		this.sistemaMensajeria.enviarMail(this.cliente.getMail(), "asunto", "texto");
		
		return DTO.map(this.reserva);
	}
	@Override
	public ReservaDTO registrarReserva(
			HotelDTO hotel,
			TipoHabitacionDTO tipoHabitacion,
			GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin,
			boolean modificablePorHuesped) throws Exception
		{
		this.reserva = cadenaHotelera.registrarReserva(
				hotel.getNombre(), 
				tipoHabitacion.getNombre(), 
				fechaInicio, 
				fechaFin, 
				modificablePorHuesped, 
				cliente);
		this.hotel = cadenaHotelera.buscarHotel(hotel.getNombre());
		this.sistemaMensajeria.enviarMail(this.cliente.getMail(), "asunto", "texto");
		
		return DTO.map(this.reserva);
		}
	

	@Override
	public Set<HotelDTO> sugerirAlternativas(
			String pais, 
			String nombreTipoHabitacion, 
			GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {
		if (this.calendario.esPasada(fechaInicio)
				|| this.calendario.esPosterior(fechaInicio, fechaFin))
			{
				throw new Exception("Fechas incorrectas");
			}
		return cadenaHotelera.sugerirAlternativas(pais, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		
		if (this.cliente == null)
		{
			throw new Exception("No hay un cliente seleccionado");
		}
		Set<ReservaDTO> reservas = new HashSet<ReservaDTO>();
		
		for(Hotel h : cadenaHotelera.listarHoteles()) {
			reservas.addAll(DTO.mapReservas( h.obtenerReservasCliente(this.cliente)));
		}
		
		return reservas;
	}

	@Override
	public ReservaDTO modificarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		
		if (this.reserva == null
			|| !this.reserva.getModificablePorHuesped())
		{
			throw new Exception("No hay una reserva seleccionado o la reserva no es modificable");
		}
		
		this.reserva.getHotel().eliminarReserva(this.reserva);
		this.reserva.setHotel(cadenaHotelera.buscarHotel(nombreHotel));
		this.reserva.getHotel().registrarReserva(this.reserva);
		this.reserva.setTipoHabitacion(this.cadenaHotelera.buscarTipoHabitacion(nombreTipoHabitacion));
		this.reserva.setFechaInicio(fechaInicio);
		this.reserva.setFechaFin(fechaFin);
		this.reserva.setModificablePorHuesped(modificablePorHuesped);
		
		this.sistemaMensajeria.enviarMail(this.reserva.getCliente().getMail(), "Modifica Reserva", "Mensaje");
		
		return DTO.map(reserva);
	}

	@Override
	public Set<ReservaDTO> buscarReservasPendientes(String nombreHotel) throws Exception {
		if (this.hotel == null)
		{
			throw new Exception("No hay un hotel seleccionado");
		}
		
		this.hotel = cadenaHotelera.buscarHotel(nombreHotel);
		return DTO.mapReservas(cadenaHotelera.buscarReservasPendientes(nombreHotel));
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		if (this.hotel == null
			|| this.cliente == null)
		{
			throw new Exception("No hay un hotel o cliente seleccionado");
		}
		
		String codigo = Long.toString(codigoReserva);
		this.reserva = hotel.seleccionarPorCodigoReserva(codigo);
		
		if (this.reserva.getCliente().getRut()
				!= this.cliente.getRut())
			{
				throw new Exception("cliente incorrecto");
			}
		
		return DTO.map(this.reserva);
	}

	@Override
	public ReservaDTO registrarHuesped(String nombre, String documento) throws Exception {
		if (this.reserva == null)
		{
			throw new Exception("No hay una reserva seleccionada");
		}
		
		this.reserva.addHuespedes(new Huesped(nombre, documento));
		return DTO.map(this.reserva);
	}

	@Override
	public ReservaDTO tomarReserva() throws Exception {
		
		if (this.reserva == null)
		{
			throw new Exception("No hay una reserva seleccionada");
		}
		this.reserva.setEstado(EstadoReserva.Tomada);
		this.reserva.setHabitacion(
				this.hotel.buscarHabitacionLibre(
						this.reserva.getTipoHabitacion(),
						this.reserva.getFechaInicio(),
						this.reserva.getFechaFin()));
		
		this.sistemaMensajeria.enviarMail(this.cliente.getMail(), "asunto", "texto");
		this.sistemaFacturacion.iniciarEstadia(DTO.map(this.reserva));
		return DTO.map(this.reserva);
	}

	@Override
	public ReservaDTO cancelarReservaDelCliente() throws Exception {
		if (this.cliente == null)
		{
			throw new Exception("No hay un cliente seleccionado");
		}
		this.reserva.setEstado(EstadoReserva.Cancelada);
		this.sistemaMensajeria.enviarMail(this.cliente.getMail(), "cancela reserva", "mensaje");
		return DTO.map(this.reserva);
	}

	@Override
	public ClienteDTO agregarCliente(
		String rut,
		String nombre,
		String direccion,
		String telefono,
		String mail) throws Exception
	{
		Cliente cliente = this.cadenaHotelera.agregarCliente(rut, nombre, direccion, telefono, mail);
		
		return DTO.map(cliente);
	}
	
	@Override
	public HabitacionDTO agregarHabitacion(
		String nombreHotel,
		String nombreTipoHabitacion,
		String nombre) throws Exception
	{
		Hotel hotel = this.cadenaHotelera.buscarHotel(nombreHotel);
		
		TipoHabitacion tipoHabitacion = this.cadenaHotelera.buscarTipoHabitacion(nombreTipoHabitacion);
		
		Habitacion habitacion = hotel.agregarHabitacion(tipoHabitacion, nombre);
		
		return DTO.map(hotel, habitacion);
	}
	
	@Override
	public HotelDTO agregarHotel(String nombre, String pais) throws Exception
	{
		Hotel hotel = this.cadenaHotelera.agregarHotel(nombre, pais);
		
		return DTO.map(hotel);
	}
	
	@Override
	public TipoHabitacionDTO agregarTipoHabitacion(String nombre) throws Exception
	{
		TipoHabitacion tipoHabitacion = this.cadenaHotelera.agregarTipoHabitacion(nombre);
		
		return DTO.map(tipoHabitacion);
	}
	
	@Override
	public Set<ClienteDTO> getClientes()
	{
		return DTO.mapClientes(cadenaHotelera.listarClientes());
	}
	
	@Override
	public Set<HabitacionDTO> getHabitaciones(String nombreHotel) throws Exception
	{
		Hotel hotel = cadenaHotelera.buscarHotel(nombreHotel);
		
		return DTO.mapHabitaciones(hotel, hotel.listarHabitaciones());
	}
	
	@Override
	public Set<HotelDTO> getHoteles()
	{
		return DTO.mapHoteles(cadenaHotelera.listarHoteles());
	}
	
	@Override
	public Set<TipoHabitacionDTO> getTiposHabitacion()
	{
		return DTO.mapTiposHabitacion(cadenaHotelera.listarTiposHabitacion());
	}
	
	public void clear() {
		this.reserva = null;
		this.cliente = null;
	}
	public void setModificando(boolean mod) {
		this.modificando = mod;
	}
}
