package com.suercopa.resorce;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.suercopa.model.Colaborador;
import com.suercopa.repository.ColaboradorRepository;
import com.suercopa.service.ColaboradorService;
import com.suercopa.service.ExceptionCpf;

@RestController
@RequestMapping("/colaboradores")
@CrossOrigin(origins = "http://supercopa-app.vercel.app")
public class ColaboradorResource {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@GetMapping
	public Page<Colaborador> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
		return colaboradorRepository.findByNomeContaining(nome, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Colaborador> buscarPorCodigo(@PathVariable Long codigo){
	return colaboradorRepository.findById(codigo)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	
	}
	
	
	@PostMapping
	public ResponseEntity<Colaborador> registrarColaborador(@Valid @RequestBody Colaborador colaborador, HttpServletRequest request) throws Exception {
		this.verificaDados(colaborador);
		
		Colaborador colaboradorSalvar = colaboradorRepository.save(colaborador);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand("Location").toUri();
		return ResponseEntity.created(uri).body(colaboradorSalvar);
	}
	
	
	@PutMapping("/{codigo}")
	public Colaborador atualizar(@PathVariable Long codigo,@RequestBody Colaborador colaborador) {
		
		return colaboradorService.atualizar(codigo, colaborador);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		colaboradorRepository.deleteById(codigo);
	}
	
	
	public void verificaDados(Colaborador colaborador) throws ExceptionCpf{
		Optional<Colaborador> colaboradorCPF = colaboradorRepository.findBycpf(colaborador.getCpf());
		
		if(colaboradorCPF.isPresent()) {
			throw new ExceptionCpf();
		}
	}
	
	@ExceptionHandler({ExceptionCpf.class})
	public ResponseEntity<String> handleCPFInvalidaException(ExceptionCpf e) {
		String exception = e.getMessage();
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
