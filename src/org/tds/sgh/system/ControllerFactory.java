package org.tds.sgh.system;

import org.tds.sgh.business.CadenaHotelera;

public class ControllerFactory implements IControllerFactory {
	// --------------------------------------------------------------------------------------------

	private ReservaController reservaController;

	// --------------------------------------------------------------------------------------------

	public ControllerFactory(CadenaHotelera cadenaHotelera) {
		reservaController = new ReservaController(cadenaHotelera);
	}

	// --------------------------------------------------------------------------------------------

	@Override
	public ICadenaController createCadenaController() {
		// this.reservaController.setModificando(false);
		return reservaController;
	}

	@Override
	public ICancelarReservaController createCancelarReservaController() {
		// this.reservaController.setModificando(false);
		return reservaController;
	}

	@Override
	public IHacerReservaController createHacerReservaController() {
		// this.reservaController.setModificando(false);
		return reservaController;
	}

	@Override
	public IModificarReservaController createModificarReservaController() {
		this.reservaController.clear();
		this.reservaController.setModificando(true);
		return reservaController;
	}

	@Override
	public ITomarReservaController createTomarReservaController() {

		this.reservaController.setModificando(true);
		return reservaController;

	}
}
