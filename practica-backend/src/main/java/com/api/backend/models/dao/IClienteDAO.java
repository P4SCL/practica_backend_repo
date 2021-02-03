package com.api.backend.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.api.backend.models.entity.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente,Long> {

}
