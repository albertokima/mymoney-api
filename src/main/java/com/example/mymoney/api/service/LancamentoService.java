package com.example.mymoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mymoney.api.model.Categoria;
import com.example.mymoney.api.model.Lancamento;
import com.example.mymoney.api.model.Pessoa;
import com.example.mymoney.api.repository.CategoriaRepository;
import com.example.mymoney.api.repository.LancamentoRepository;
import com.example.mymoney.api.repository.PessoaRepository;
import com.example.mymoney.api.service.exception.CategoriaInexistenteException;
import com.example.mymoney.api.service.exception.PessoaInexistenteInativaException;

@Service
public class LancamentoService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(Optional.ofNullable(lancamento.getPessoa().getCodigo()).orElse(0L));
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteInativaException();
		}
		Categoria categoria = categoriaRepository.findOne(Optional.ofNullable(lancamento.getCategoria().getCodigo()).orElse(0L));
		if (categoria == null) {
			throw new CategoriaInexistenteException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

}
