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
			
		//TODO: con el código obtenemos un nuevo huesped
		
		this.nombre = "";		
		this.codigo = codigo;
		this.documento = "";
	}
	
	public String GetNombre()
	{				
		return this.nombre;
	}
	
	public String GetDocumento()
	{				
		return this.documento;
	}
}
