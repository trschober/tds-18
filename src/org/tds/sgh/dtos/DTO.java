package org.tds.sgh.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.Habitacion;
import org.tds.sgh.business.Hotel;
import org.tds.sgh.business.Huesped;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.business.TipoHabitacion;

public class DTO {
	// --------------------------------------------------------------------------------------------

	private static final DTO Instance = new DTO();

	// --------------------------------------------------------------------------------------------

	public static DTO getInstance() {
		return Instance;
	}

	// --------------------------------------------------------------------------------------------

	private DTO() {
	}

	// --------------------------------------------------------------------------------------------

	public ClienteDTO map(Cliente cliente) {
		return new ClienteDTO(cliente.getRut(), cliente.getNombre(), cliente.getDireccion(), cliente.getTelefono(),
				cliente.getMail());
	}

	public HotelDTO map(Hotel hotel) {
		return new HotelDTO(hotel.getNombre(), hotel.getPais());
	}

	public HabitacionDTO map(Hotel hotel, Habitacion habitacion) {
		return new HabitacionDTO(hotel.getNombre(), habitacion.gettipoHabitacion().getNombre(), habitacion.getNombre());
	}

	public TipoHabitacionDTO map(TipoHabitacion tipoHabitation) {
		return new TipoHabitacionDTO(tipoHabitation.getNombre());
	}

	public HuespedDTO map(Huesped huesped) {
		return new HuespedDTO(huesped.GetNombre(), huesped.GetDocumento());
	}

	public ReservaDTO map(Reserva reserva) throws Exception {
		// HuespedDTO[] hp = new HuespedDTO[reserva.getHuespedes().size()];

		Set<HuespedDTO> x = this.mapHuesped(reserva.getHuespedes().stream().collect(Collectors.toSet()));
		HuespedDTO[] y = x.toArray(new HuespedDTO[0]);

		String nombrehabitacion;

		try {
			nombrehabitacion = reserva.getHabitacion().getNombre();
		} catch (Exception ex) {
			nombrehabitacion = null;
		}
		return new ReservaDTO(reserva.getCodigo(), reserva.getCliente().getRut(), reserva.getHotel().getNombre(),
				reserva.getTipoHabitacion(), reserva.getFechaInicio(), reserva.getFechaFin(),
				reserva.getModificablePorHuesped(), reserva.getEstado().toString(), nombrehabitacion, y);

	}

	public Set<ReservaDTO> mapReservas(Set<Reserva> reservas) throws Exception {
		Set<ReservaDTO> reservasDTO = new HashSet<ReservaDTO>();

		for (Reserva reserva : reservas) {
			reservasDTO.add(this.map(reserva));
		}

		return reservasDTO;
	}

	public Set<ClienteDTO> mapClientes(Set<Cliente> clientes) {
		Set<ClienteDTO> clientesDTO = new HashSet<ClienteDTO>();

		for (Cliente cliente : clientes) {
			clientesDTO.add(this.map(cliente));
		}

		return clientesDTO;
	}

	public Set<HabitacionDTO> mapHabitaciones(Hotel hotel, Set<Habitacion> habitaciones) {
		Set<HabitacionDTO> habitacionesDTO = new HashSet<HabitacionDTO>();

		for (Habitacion habitacion : habitaciones) {
			habitacionesDTO.add(this.map(hotel, habitacion));
		}

		return habitacionesDTO;
	}

	public Set<HotelDTO> mapHoteles(Set<Hotel> hoteles) {
		Set<HotelDTO> hotelesDTO = new HashSet<HotelDTO>();

		for (Hotel hotel : hoteles) {
			hotelesDTO.add(this.map(hotel));
		}

		return hotelesDTO;
	}

	public Set<TipoHabitacionDTO> mapTiposHabitacion(Set<TipoHabitacion> tiposHabitacion) {
		Set<TipoHabitacionDTO> tiposHabitacionDTO = new HashSet<TipoHabitacionDTO>();

		for (TipoHabitacion tipoHabitacion : tiposHabitacion) {
			tiposHabitacionDTO.add(this.map(tipoHabitacion));
		}

		return tiposHabitacionDTO;
	}

	public Set<HuespedDTO> mapHuesped(Set<Huesped> huespedes) {
		Set<HuespedDTO> huespedesDTO = new HashSet<HuespedDTO>();

		for (Huesped huesped : huespedes) {
			huespedesDTO.add(this.map(huesped));
		}

		return huespedesDTO;
	}
}
