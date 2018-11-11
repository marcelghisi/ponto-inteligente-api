package com.ghisi.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ghisi.pontointeligente.api.entities.Empresa;
import com.ghisi.pontointeligente.api.entities.Funcionario;
import com.ghisi.pontointeligente.api.enums.PerfilEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	private static final String EMAIL = "marcel.ghisi@gmail.com";
	private static final String CPF = "02479484971";
	
	@Before
	public void setUp() {
		Empresa empresa = this.empresaRepository.save(obterDadosDaEmpresa());
		this.funcionarioRepository.save(obterDadosFuncionario(empresa));
	}
	private Funcionario obterDadosFuncionario(Empresa empresa) {
	
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(CPF);
		funcionario.setEmail(EMAIL);
		funcionario.setEmpresa(empresa);
		funcionario.setNome("Marcel Ghisi");
		funcionario.setSenha("m6a6r2c9");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		
		return funcionario;
	}

	@After
	public final void tearDown() {
		empresaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarFuncionarioPorEmail() {
		Funcionario funcionario = this.funcionarioRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, funcionario.getEmail());
	}
	
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpf(CPF);
		assertEquals(CPF, funcionario.getCpf());
	}
	
	@Test
	public void testBuscarFuncionarioPorEmailECpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);
		assertEquals(CPF, funcionario.getCpf());
		assertEquals(EMAIL, funcionario.getEmail());
	}
	
	@Test
	public void testBuscarFuncionarioPorEmailECpfParaEmailInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, "email@invalido.com");
		assertNotNull(funcionario);
	}
	
	@Test
	public void testBuscarFuncionarioPorEmailECpfParaCpfInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("4444444", "marcel.ghisi@gmail.com");
		assertNotNull(funcionario);
	}
	
	private Empresa obterDadosDaEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setCnpj("024795485000127");
		empresa.setRazaoSocial("Serasa ID");
		return empresa;
	}
	

}
