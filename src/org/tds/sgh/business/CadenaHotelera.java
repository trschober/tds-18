package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.ReservaDTO;


public class CadenaHotelera
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Cliente> clientes;
	
	private Map<String, Hotel> hoteles;
	
	private String nombre;
	
	private Map<String, TipoHabitacion> tiposHabitacion;
	
	// --------------------------------------------------------------------------------------------
	
	public CadenaHotelera(String nombre)
	{
		this.clientes = new HashMap<String, Cliente>();
		
		this.hoteles = new HashMap<String, Hotel>();
		
		this.nombre = nombre;
		
		this.tiposHabitacion = new HashMap<String, TipoHabitacion>();
	}
	
	// --------------------------------------------------------------------------------------------
	
	public Cliente agregarCliente(
		String rut,
		String nombre,
		String direccion,
		String telefono,
		String mail) throws Exception
	{
		if (this.clientes.containsKey(rut))
		{
			throw new Exception("Ya existe un cliente con el RUT indicado.");
		}
		
		Cliente cliente = new Cliente(rut, nombre, direccion, telefono, mail);
		
		this.clientes.put(cliente.getRut(), cliente);
		
		return cliente;
	}
	
	public Hotel agregarHotel(String nombre, String pais) throws Exception
	{
		if (this.hoteles.containsKey(nombre))
		{
			throw new Exception("Ya existe un hotel con el nombre indicado.");
		}
		
		Hotel hotel = new Hotel(nombre, pais);
		
		this.hoteles.put(hotel.getNombre(), hotel);
		
		return hotel;
	}
	
	public TipoHabitacion agregarTipoHabitacion(String nombre) throws Exception
	{
		if (this.tiposHabitacion.containsKey(nombre))
		{
			throw new Exception("Ya existe un tipo de habitación con el nombre indicado.");
		}
		
		TipoHabitacion tipoHabitacion = new TipoHabitacion(nombre);
		
		this.tiposHabitacion.put(tipoHabitacion.getNombre(), tipoHabitacion);
		
		return tipoHabitacion;
	}
	
	public Cliente buscarCliente(String rut) throws Exception
	{
		Cliente cliente = this.clientes.get(rut);
		
		if (cliente == null)
		{
			throw new Exception("No existe un cliente con el nombre indicado.");
		}
		
		return cliente;
	}
	
	public Set<Cliente> buscarClientes(String patronNombreCliente)
	{
		Set<Cliente> clientesEncontrados = new HashSet<Cliente>();
		
		for (Cliente cliente : this.clientes.values())
		{
			if (cliente.coincideElNombre(patronNombreCliente))
			{
				clientesEncontrados.add(cliente);
			}
		}
		
		return clientesEncontrados;
	}
	
	public Hotel buscarHotel(String nombre) throws Exception
	{
		Hotel hotel = this.hoteles.get(nombre);
		
		if (hotel == null)
		{
			throw new Exception("No existe un hotel con el nombre indicado.");
		}
		
		return hotel;
	}
	
	public TipoHabitacion buscarTipoHabitacion(String nombre) throws Exception
	{
		TipoHabitacion tipoHabitacion = this.tiposHabitacion.get(nombre);
		
		if (tipoHabitacion == null)
		{
			throw new Exception("No existe un tipo de habitación con el nombre indicado.");
		}
		
		return tipoHabitacion;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public Set<Cliente> listarClientes()
	{
		return new HashSet<Cliente>(this.clientes.values());
	}
	
	public Set<Hotel> listarHoteles()
	{
		return new HashSet<Hotel>(this.hoteles.values());
	}
	
	public Set<TipoHabitacion> listarTiposHabitacion()
	{
		return new HashSet<TipoHabitacion>(this.tiposHabitacion.values());
	}

	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {
		Hotel h = this.buscarHotel(nombreHotel);
		TipoHabitacion th = this.buscarTipoHabitacion(nombreTipoHabitacion);

		if (th == null)
			{
				throw new Exception("habitacion inexistente");
			}
		String nth = th.getNombre();
		boolean a = h.confirmarDisponibilidad(nth, fechaInicio, fechaFin);
		return a;
	}

	public Reserva registrarReserva(
			String nombreHotel, 
			String nombreTipoHabitacion, 
			GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, 
			boolean modificablePorHuesped,
			Cliente cliente
			) throws Exception {
		
		Hotel h = this.buscarHotel(nombreHotel);
		TipoHabitacion th = this.buscarTipoHabitacion(nombreTipoHabitacion);
		return h.registrarReserva(th, fechaInicio, fechaFin, modificablePorHuesped, cliente, h);
	}

	public Set<HotelDTO> sugerirAlternativas(
			String pais, 
			String nombreTipoHabitacion, 
			GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin
			) throws Exception {
		if (this.tiposHabitacion.get(nombreTipoHabitacion) == null)
			{
				throw new Exception("tipo habitacion inexistente");
			}
		
		Set<HotelDTO> hotelesDTO = new HashSet<HotelDTO>();

		for (Hotel hotel : hoteles.values()) {
			if (hotel.confirmarDisponibilidad(
					nombreTipoHabitacion,
					fechaInicio, 
					fechaFin) 
				&& hotel.getPais().equals(pais)) 
			{
				hotelesDTO.add(new HotelDTO(hotel.getNombre(), hotel.getPais()));
			}
		}

		return hotelesDTO;
	}

	public Set<Reserva> BuscarReservaCliente(Cliente cliente) {
		Set<Reserva> Obj = new HashSet<>();
		
		for (Hotel hotel : hoteles.values()) {		
			
			Obj.addAll(hotel.obtenerReservasCliente(cliente));
		}
		
		return Obj;
	}

	public Set<Reserva> buscarReservasPendientes(String nombreHotel) throws Exception {
		
		return this.buscarHotel(nombreHotel).buscarReservasPendientes();
	}
}
