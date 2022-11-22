package com.suercopa.repository.colaborador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suercopa.model.Colaborador;
import com.suercopa.repository.filter.ColaboradorFilter;
import com.suercopa.repository.projection.ResumoColaborador;

public interface ColaboradorRepositoryQuery {

	public Page<Colaborador> filtrar(ColaboradorFilter colaboradorFilter, Pageable pageable);
	public Page<ResumoColaborador> resumir(ColaboradorFilter colaboradorFilter, Pageable pageable);
	
}
