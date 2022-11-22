package com.suercopa.repository.projection;

public class ResumoColaborador {
	private Long codigo;
	private String nome;
	private String cpf;
	private Integer favor;
	private Integer contra;

	public ResumoColaborador(Long codigo, String nome, String cpf, Integer favor, Integer contra) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.favor = favor;
		this.contra = contra;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getFavor() {
		return favor;
	}

	public void setFavor(Integer favor) {
		this.favor = favor;
	}

	public Integer getContra() {
		return contra;
	}

	public void setContra(Integer contra) {
		this.contra = contra;
	}

}
