package org.tds.sgh.system;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.infrastructure.NotImplementedException;


public class ControllerFactory implements IControllerFactory
{
	// --------------------------------------------------------------------------------------------
	
	private CadenaHotelera cadenaHotelera;
	
	private ReservaController reservaController;
	
	// --------------------------------------------------------------------------------------------
	
	public ControllerFactory(CadenaHotelera cadenaHotelera)
	{
		this.cadenaHotelera = cadenaHotelera;
		reservaController = new ReservaController(cadenaHotelera);
	}
	
	// --------------------------------------------------------------------------------------------
	
	 
	@Override
	public ICadenaController createCadenaController()
	{
		return reservaController;
	}
	
	@Override
	public ICancelarReservaController createCancelarReservaController()
	{
		return reservaController;
	}
	
	@Override
	public IHacerReservaController createHacerReservaController()
	{
		return reservaController;
	}
	
	@Override
	public IModificarReservaController createModificarReservaController()
	{
		return reservaController;
	}
	
	@Override
	public ITomarReservaController createTomarReservaController()
	{
		return reservaController;

	}
}
