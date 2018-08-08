package org.tds.sgh.system;

import java.util.GregorianCalendar;
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

public class ReservaController implements IHacerReservaController, ITomarReservaController, ICancelarReservaController, ICadenaController {

	private final DTO DTO = org.tds.sgh.dtos.DTO.getInstance();
	
	// --------------------------------------------------------------------------------------------
	
	private CadenaHotelera cadenaHotelera;
	private Hotel hotel;
	private Cliente cliente;
	private Reserva reserva;
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

	}
	
	@Override
	public Set<ClienteDTO> buscarCliente(String patronNombreCliente) {
		return DTO.mapClientes(cadenaHotelera.buscarClientes(patronNombreCliente));
	}

	@Override
	public ClienteDTO seleccionarCliente(String rut) throws Exception {		
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
	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {

		return cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public ReservaDTO registrarReserva(String nombreHotel, 
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
	public Set<HotelDTO> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {

		return cadenaHotelera.sugerirAlternativas(pais, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO modificarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ReservaDTO> buscarReservasPendientes(String nombreHotel) throws Exception {
		this.hotel = cadenaHotelera.buscarHotel(nombreHotel);
		return DTO.mapReservas(cadenaHotelera.buscarReservasPendientes(nombreHotel));
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		String codigo = Long.toString(codigoReserva);
		this.reserva = hotel.seleccionarPorCodigoReserva(codigo);
		return DTO.map(this.reserva);
	}

	@Override
	public ReservaDTO registrarHuesped(String nombre, String documento) throws Exception {
		this.reserva.addHuespedes(new Huesped(nombre, documento));
		return DTO.map(this.reserva);
	}

	@Override
	public ReservaDTO tomarReserva() throws Exception {
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
		// TODO Auto-generated method stub
		return null;
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
}
