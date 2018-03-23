package com.example.mymoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mymoney.api.model.Lancamento;
import com.example.mymoney.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
