package com.example.mymoney.api.repository.lancamento;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.example.mymoney.api.model.Categoria_;
import com.example.mymoney.api.model.Lancamento;
import com.example.mymoney.api.model.Lancamento_;
import com.example.mymoney.api.model.Pessoa_;
import com.example.mymoney.api.model.TipoLancamento;
import com.example.mymoney.api.repository.filter.LancamentoFilter;
import com.example.mymoney.api.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);		
		adicionarOrdem(pageable, builder, criteria, root);
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);		
		Root<Lancamento> root = criteria.from(Lancamento.class);

		criteria.select(builder.construct(ResumoLancamento.class
				, root.get(Lancamento_.codigo), root.get(Lancamento_.descricao)
				, root.get(Lancamento_.dataVencimento), root.get(Lancamento_.dataPagamento)
				, root.get(Lancamento_.valor), root.get(Lancamento_.tipo)
				, root.get(Lancamento_.categoria).get(Categoria_.nome)
				, root.get(Lancamento_.pessoa).get(Pessoa_.nome)));
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);		
		adicionarOrdem(pageable, builder, criteria, root);
		TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())){
			predicates.add(builder.like(builder.lower(
					root.get(Lancamento_.descricao)) , "%"+ lancamentoFilter.getDescricao().toLowerCase() +"%"));
		}
		if (lancamentoFilter.getDataVencimentoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(
					root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoDe()));
		}
		if (lancamentoFilter.getDataVencimentoAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(
					root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoAte()));
		}
		if (lancamentoFilter.isVencido()) {
			ZoneId fusoHorario = ZoneId.of("America/Sao_Paulo");
			predicates.add(builder.lessThan(
					root.get(Lancamento_.dataVencimento), LocalDate.now(fusoHorario)));
			predicates.add(builder.isNull(
					root.get(Lancamento_.dataPagamento)));
			predicates.add(builder.equal(
					root.get(Lancamento_.tipo), TipoLancamento.DESPESA));
		}
		if (lancamentoFilter.isAVencer()) {
			ZoneId fusoHorario = ZoneId.of("America/Sao_Paulo");
			predicates.add(builder.equal(
					root.get(Lancamento_.dataVencimento), LocalDate.now(fusoHorario)));
			predicates.add(builder.isNull(
					root.get(Lancamento_.dataPagamento)));
			predicates.add(builder.equal(
					root.get(Lancamento_.tipo), TipoLancamento.DESPESA));
		}
		if (lancamentoFilter.getNomePessoa() != null) {
			predicates.add(builder.like(builder.lower(
					root.get(Lancamento_.pessoa).get(Pessoa_.nome)) , "%"+ lancamentoFilter.getNomePessoa().toLowerCase() +"%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int registroAtual = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(registroAtual);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarOrdem(Pageable pageable, CriteriaBuilder builder, CriteriaQuery<?> criteria,
			Root<?> root) {
		Sort sort = pageable.getSort();
		if(sort != null) {
			Sort.Order order = sort.iterator().next();
			String field = order.getProperty();
			criteria.orderBy(
				order.isAscending() ? builder.asc(root.get(field)) : builder.desc(root.get(field))
			);
		}
	}

}
