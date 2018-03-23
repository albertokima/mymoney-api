package com.example.mymoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mymoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
