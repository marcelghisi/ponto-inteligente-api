package com.ghisi.pontointeligente.api.repositories;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghisi.pontointeligente.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);
	
}
