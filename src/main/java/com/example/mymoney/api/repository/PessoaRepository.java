package com.example.mymoney.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mymoney.api.model.Pessoa;
import com.example.mymoney.api.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery{

    @Modifying(clearAutomatically = true)
    @Query("update Pessoa p set p.ativo = :ativo where p.codigo = :codigo")
    int atualizarAtivo(@Param("codigo") Long codigo, @Param("ativo") Boolean ativo);
    
    public Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);
    
}
