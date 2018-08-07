package org.tds.sgh.system;

import java.util.Set;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.ReservaDTO;

public class CancelarReservaController implements ICancelarReservaController {

	public CancelarReservaController(CadenaHotelera cadenaHotelera) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Set<ClienteDTO> buscarCliente(String patronNombreCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteDTO seleccionarCliente(String rut) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO cancelarReservaDelCliente() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
