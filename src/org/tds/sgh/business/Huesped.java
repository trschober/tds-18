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
			
		//TODO: con el c�digo obtenemos un nuevo huesped
		
		this.nombre = "";		
		this.setCodigo(codigo);
		this.documento = "";
	}
	public Huesped(String nombre, String documento)
	{		
		this.nombre = nombre;
		this.documento = documento;
	}
	public String GetNombre()
	{				
		return this.nombre;
	}
	
	public String GetDocumento()
	{				
		return this.documento;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
