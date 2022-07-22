package com.idat.EC3RENZOMARTINEZRESERVARCITA.service;

import java.util.List;

import com.idat.EC3RENZOMARTINEZRESERVARCITA.dto.ClienteDTORequest;
import com.idat.EC3RENZOMARTINEZRESERVARCITA.dto.ClienteDTOResponse;



public interface ClienteService {

	public void guardarCliente(ClienteDTORequest cliente);
	public void editarCliente(ClienteDTORequest cliente);
	public void eliminarCliente(Integer id);
	public List<ClienteDTOResponse> listarCliente();
	public ClienteDTOResponse obtenerClienteId(Integer id);
	
}
