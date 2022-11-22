package com.suercopa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suercopa.model.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{
	
	Optional<Colaborador> findBycpf(String cpf);
	
	List<Colaborador> findByNome(String nome);
	public Page<Colaborador> findByNomeContaining(String nome, Pageable pageable);
}
