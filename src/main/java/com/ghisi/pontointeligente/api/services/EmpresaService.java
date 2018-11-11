package com.ghisi.pontointeligente.api.services;

import java.util.Optional;

import com.ghisi.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

		Optional<Empresa> buscarCnpj(String cnpj);
		
		Empresa persistir(Empresa empresa);
		
}
