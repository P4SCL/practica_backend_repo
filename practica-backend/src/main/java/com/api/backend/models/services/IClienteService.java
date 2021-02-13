package com.api.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.backend.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	public Page<Cliente> findAll(Pageable page);
	
	public Cliente findById(Long id);
	
	public Cliente update(Cliente cliente);
	
	public void delete(Long id);
}
