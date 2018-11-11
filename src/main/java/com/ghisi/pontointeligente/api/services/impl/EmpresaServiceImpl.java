package com.ghisi.pontointeligente.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghisi.pontointeligente.api.entities.Empresa;
import com.ghisi.pontointeligente.api.repositories.EmpresaRepository;
import com.ghisi.pontointeligente.api.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Optional<Empresa> buscarCnpj(String cnpj) {
		Empresa empresa = empresaRepository.findByCnpj(cnpj);
		return Optional.ofNullable(empresa);
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		return this.empresaRepository.save(empresa);
	}

}
