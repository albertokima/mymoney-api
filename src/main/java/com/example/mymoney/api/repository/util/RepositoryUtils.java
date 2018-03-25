package com.example.mymoney.api.repository.util;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class RepositoryUtils {
	
	public final void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int registroAtual = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(registroAtual);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	public final void adicionarOrdem(Pageable pageable, CriteriaBuilder builder, CriteriaQuery<?> criteria,
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
