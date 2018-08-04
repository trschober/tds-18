package org.tds.sgh.business;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Huesped {

	private String nombre;
	private String codigo;
	private String documento;
	
	public Huesped(String codigo)
	{
			
		this.nombre = "";		
		this.codigo = codigo;
		this.documento = "";
	}
	
	public Huesped GetHuesped(String codigo)
	{
		
		Huesped huesped = new Huesped(codigo);
		
		return huesped;
	}
}
