package com.api.backend.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.backend.models.dao.IClienteDAO;
import com.api.backend.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDAO clienteDao;
	
	/** Método para listar los clientes **/
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	/** Método para buscar un cliente por ID **/
	@Override
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	/** Método para crear un cliente,
	 * 	en el @RequestBody debe ir un Cliente cliente,
	 *  con sus atributos, menos el id porque es autogenerado,
	 *  ni la fecha porque antes de persistirse se inicilializa**/
	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}
	
	/** Método para actualizar un cliente,
	 * 	en el @RequestBody debe ir un Cliente cliente,
	 *  con sus atributos, menos el id porque es autogenerado,
	 *  ni la fecha porque antes de persistirse se inicilializa**/
	@Override
	public Cliente update(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	/*
	 * Método que devuelve una página de clientes.
	 */
	@Override
	public Page<Cliente> findAll(Pageable page) {
		
		return clienteDao.findAll(page);
	}
	
	
}
