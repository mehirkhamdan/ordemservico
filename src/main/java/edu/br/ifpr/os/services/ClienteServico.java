package edu.br.ifpr.os.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.br.ifpr.os.exception.EmailJaExisteException;
import edu.br.ifpr.os.models.Cliente;
import edu.br.ifpr.os.repositories.ClienteRepositorio;

@Service
public class ClienteServico {
	
	@Autowired
	ClienteRepositorio repositorio;
	
	public Cliente salvar(Cliente cliente) throws Exception {
		
		Cliente existente = repositorio.findByEmail(cliente.getEmail());
		
		if (existente != null && !existente.equals(cliente)) {
			throw new EmailJaExisteException("Já existe um usuario com este email");
		}
		
		return repositorio.save(cliente);
		
	}

}
