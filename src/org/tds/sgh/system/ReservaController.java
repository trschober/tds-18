package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.Set;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.Hotel;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.DTO;
import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.ReservaDTO;

public class ReservaController implements IHacerReservaController, ITomarReservaController, ICancelarReservaController {

	private final DTO DTO = org.tds.sgh.dtos.DTO.getInstance();
	
	// --------------------------------------------------------------------------------------------
	
	private CadenaHotelera cadenaHotelera;
	private Hotel hotel;
	private Cliente cliente;
	
	// --------------------------------------------------------------------------------------------
	
	public ReservaController(CadenaHotelera cadenaHotelera)
	{
		this.cadenaHotelera = cadenaHotelera;
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
		
		return DTO.map(cadenaHotelera.agregarCliente(rut, nombre, direccion, telefono, mail));
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {

		return cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public ReservaDTO registrarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		
		return cadenaHotelera.registrarReserva(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, modificablePorHuesped, cliente);

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO registrarHuesped(String nombre, String documento) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO tomarReserva() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO cancelarReservaDelCliente() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
