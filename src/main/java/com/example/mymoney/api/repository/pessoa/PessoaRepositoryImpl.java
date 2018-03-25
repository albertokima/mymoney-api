package com.example.mymoney.api.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.mymoney.api.model.Endereco_;
import com.example.mymoney.api.model.Pessoa;
import com.example.mymoney.api.model.Pessoa_;
import com.example.mymoney.api.repository.filter.PessoaFilter;
import com.example.mymoney.api.repository.util.RepositoryUtils;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private RepositoryUtils repositoryUtils;
	
	@Override
	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(pessoaFilter, builder, root);
		criteria.where(predicates);		
		repositoryUtils.adicionarOrdem(pageable, builder, criteria, root);
		TypedQuery<Pessoa> query = manager.createQuery(criteria);
		
		repositoryUtils.adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(pessoaFilter));
	}

	private Predicate[] criarRestricoes(PessoaFilter pessoaFilter, CriteriaBuilder builder,
			Root<Pessoa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(pessoaFilter.getNome())){
			predicates.add(builder.like(builder.lower(
					root.get(Pessoa_.nome)) , "%"+ pessoaFilter.getNome().toLowerCase() +"%"));
		}
		if (!StringUtils.isEmpty(pessoaFilter.getCidade())){
			predicates.add(builder.like(builder.lower(
					root.get(Pessoa_.endereco).get(Endereco_.cidade)) , "%"+ pessoaFilter.getCidade().toLowerCase() +"%"));
		}
		if (!StringUtils.isEmpty(pessoaFilter.getAtivo())){
			predicates.add(builder.isTrue(builder.equal(root.get(Pessoa_.ativo), pessoaFilter.getAtivo())));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(PessoaFilter pessoaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(pessoaFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
