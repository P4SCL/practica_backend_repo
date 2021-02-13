package com.api.backend.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.backend.models.entity.Cliente;
import com.api.backend.models.excepciones.ApiRequestException;
import com.api.backend.models.services.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	
	@GetMapping("/clientes")
	public List<Cliente> listarClientes(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> listarClientesPage(@PathVariable int page){
		return clienteService.findAll(PageRequest.of(page, 4));
	}
	
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){

		Cliente cliente = null;
		try {
			cliente = clienteService.findById(id);
		}catch(DataAccessException e){
			throw new ApiRequestException("No se pudo encontrar el usuario con id: ".concat(id.toString()));
		}
		
		if(cliente ==null) {
			throw new ApiRequestException("No se pudo encontrar el usuario con id: ".concat(id.toString()));
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> guardarCliente(@Valid @RequestBody Cliente cliente,BindingResult result){

		Cliente clienteNew = null;
		Map<String,Object> response = new HashMap<>();
		Map<String,Object> erroresCampos = new HashMap<>();
		// Si el result tiene errores
	
		if(result.hasErrors()) {
			result.getFieldErrors().forEach(err->erroresCampos.put(err.getField(),"El campo "+err.getField()+" "+err.getDefaultMessage()));
			return new ResponseEntity<Map<String,Object>>(erroresCampos,HttpStatus.BAD_REQUEST);
		}
		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataIntegrityViolationException dke) {

			String[] codes = null;
			Object[] arguments = null;
			FieldError field_email = new FieldError("Cliente","email", cliente.getEmail(), false, codes, arguments, "Email duplicado");
			
			
			result.addError(field_email);
		
			erroresCampos.put(field_email.getField(),field_email.getDefaultMessage());
			

			return new ResponseEntity<Map<String,Object>>(erroresCampos,HttpStatus.BAD_REQUEST);
		}
	    

		response.put("mensaje", "Registro insertado correctamente");
		response.put("cliente", clienteNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> actualizarCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id,BindingResult result){
		
		Map<String,Object> response = new HashMap<>();
		
		Cliente clienteBD = clienteService.findById(id);
		Cliente clienteActualizado=null;
		
		if(result.hasErrors()) {
			Map<String,String> erroresCampos = new HashMap<>();
			
			result.getFieldErrors().forEach(err->erroresCampos.put(err.getField(),"El campo: "+err.getField()+" "+err.getDefaultMessage()));
			return new ResponseEntity<Map<String,String>>(erroresCampos,HttpStatus.BAD_REQUEST);
		}
		
		
		if(clienteBD == null) {
			throw new ApiRequestException("No se pudo encontrar el cliente con ID: ".concat(id.toString()));
		}
		
			clienteBD.setNombre(cliente.getNombre());
			clienteBD.setApellido(cliente.getApellido());
			clienteBD.setEmail(cliente.getEmail());
			clienteActualizado = clienteService.update(clienteBD);
		
		
		response.put("mensaje", "Cliente actualizado con éxito");
		response.put("cliente", clienteActualizado);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> borrarCliente(@PathVariable Long id){
		Map<String,Object> response = null; 
		try {
			response = new HashMap<>();
			clienteService.delete(id);
		}catch(DataAccessException e) {
			throw new ApiRequestException("No existe el id: ".concat(id.toString()));
		}
		response.put("mensaje", "Cliente eliminado con éxito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NO_CONTENT);
		
	}
}
