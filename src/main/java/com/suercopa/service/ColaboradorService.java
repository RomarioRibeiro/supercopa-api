package com.suercopa.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.suercopa.model.Colaborador;
import com.suercopa.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	
	
	public Colaborador buscarPorCodigo(Long codigo) {
		Colaborador colaborador = colaboradorRepository.findById(codigo)
					.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return colaborador;
		
	}
	
	
	public Colaborador atualizar(Long codigo ,Colaborador colaborador) {
		Colaborador colaboradorSalva = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(colaborador, colaboradorSalva, "codigo");
		
		return colaboradorRepository.save(colaboradorSalva);
		
	}
	
	
}
