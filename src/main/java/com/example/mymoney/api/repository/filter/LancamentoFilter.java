package com.example.mymoney.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {

	private String descricao;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVencimentoDe;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVencimentoAte;
	
	private String nomePessoa;
	
	private Boolean vencido;
	
	private Boolean aVencer;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimentoDe() {
		return dataVencimentoDe;
	}

	public void setDataVencimentoDe(LocalDate dataVencimentoDe) {
		this.dataVencimentoDe = dataVencimentoDe;
	}

	public LocalDate getDataVencimentoAte() {
		return dataVencimentoAte;
	}

	public void setDataVencimentoAte(LocalDate dataVencimentoAte) {
		this.dataVencimentoAte = dataVencimentoAte;
	}
	
	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public Boolean getVencido() {
		return vencido;
	}

	public void setVencido(Boolean vencido) {
		this.vencido = vencido;
	}
	
	public Boolean getaVencer() {
		return aVencer;
	}

	public void setaVencer(Boolean aVencer) {
		this.aVencer = aVencer;
	}

	public boolean isVencido() {
		return this.vencido!=null && this.vencido;
	}
	
	public boolean isAVencer() {
		return this.aVencer!=null && this.aVencer;
	}

}
