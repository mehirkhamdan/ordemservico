package edu.br.ifpr.os.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.br.ifpr.os.models.Cliente;
import edu.br.ifpr.os.repositories.ClienteRepositorio;
import edu.br.ifpr.os.services.ClienteServico;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteRepositorio repositorio;
	
	@Autowired
	ClienteServico servico;
	
	@GetMapping
	public List<Cliente> getClientes(){
		
		List<Cliente> clientes = repositorio.findAll();
		
		return clientes;
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getCliente(@PathVariable Integer id) {
		
		Optional<Cliente> cliente = repositorio.findById(id);
				
		if(!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		
		return ResponseEntity.ok(cliente.get());
		
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Cliente>> getClientesByName(@PathVariable String nome) {
		
		List<Cliente> clientes = repositorio.findByNomeContaining(nome);
				
		return ResponseEntity.ok(clientes);
		
	}
	
	@PostMapping
	public Cliente cadastrar(@Valid @RequestBody Cliente cliente) throws Exception {
		
		return servico.salvar(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente, @PathVariable Integer id) {
		
		if (!repositorio.existsById(id)) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		cliente.setId(id);
		cliente = repositorio.save(cliente);
		
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		
		if (!repositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		repositorio.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
