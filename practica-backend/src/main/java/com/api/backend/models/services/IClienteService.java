package com.api.backend.models.services;

import java.util.List;

import com.api.backend.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	public Cliente findById(Long id);
	
	public Cliente update(Cliente cliente);
	
	public void delete(Long id);
}
