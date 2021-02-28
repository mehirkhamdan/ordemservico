package edu.br.ifpr.os.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.br.ifpr.os.models.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

	public List<Cliente> findByNome(String nome);
	public List<Cliente> findByNomeContaining(String nome);
	
	public Cliente findByEmail(String email);
	
	/*
	@Query(value = "SELECT FROM Cliente where id = ?", nativeQuery = true)
	public void teste(Integer id);
	*/
	
}
