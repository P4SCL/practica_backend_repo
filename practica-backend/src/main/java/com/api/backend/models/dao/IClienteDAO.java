package com.api.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.backend.models.entity.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente,Long> {

}
