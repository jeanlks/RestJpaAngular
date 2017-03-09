package com.teste.rest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.teste.entity.AmizadeEntity;
import com.teste.entity.PessoaEntity;
import com.teste.model.Pessoa;
import com.teste.repository.AmizadeRepository;
import com.teste.repository.PessoaRepository;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de testes para recursos.
 * @author Jean
 *
 */
public class ServiceControllerTest {
    @Mock
    PessoaRepository repositorioPessoa;
    @Mock
    AmizadeRepository repositorioAmizade;
    ServiceController servico = new ServiceController();
    @Before
    public void init(){
        initMocks(this);
        servico.pessoaBO.repositoryPessoa = repositorioPessoa;
        servico.amizadeBO.repositoryAmizade = repositorioAmizade;
        carregaFuncoesGeraisMock();
    }
    
    public void carregaFuncoesGeraisMock(){
        when(repositorioPessoa.getPessoa(anyInt())).thenReturn(getPessoaEntityMock());
        when(repositorioPessoa.listarAmigosPorId(anyInt())).thenReturn(getListAmizadeMock());
        when(repositorioPessoa.listarPessoas()).thenReturn(getListaPessoaEntityMock());  
    }
    

    @Test
    public void verificaEmailJaCadastradoTest() {
        when(repositorioPessoa.getPessoaPorEmail(anyString())).thenReturn(getPessoaEntityMock());
        assertTrue(servico.pessoaBO.verificaEmailCadastrado("teste"));
        
    }
    
    @Test
    public void verificaEmailNaoCadastradoTest(){
        when(repositorioPessoa.getPessoaPorEmail(anyString())).thenReturn(null);
        assertFalse(servico.pessoaBO.verificaEmailCadastrado("teste"));  
    }
    @Test
    public void getPessoaTest(){
        assertNotNull(servico.getPessoa(1));
    }
    
    @Test
    public void listaAmigosPorIdTest(){
       assertNotNull( servico.listarAmigosPorId(1));
    }
    @Test
    public void getPessoaPorEmailTest(){
        when(repositorioPessoa.getPessoaPorEmail(anyString())).thenReturn(getPessoaEntityMock());
        assertNotNull(servico.getPessoaPorEmail("teste"));
    }
    @Test
    public void cadastrarTest(){
        when(repositorioPessoa.getPessoaPorEmail(anyString())).thenReturn(null);
        servico.cadastrar(getPessoaMock());
        verify(repositorioPessoa).salvar(any());
    }
    
    @Test
    public void cadastrarCamposNulosTest(){
        assertNotNull(servico.cadastrar(new Pessoa()));
    }
    
    @Test
    public void alterarTest(){
        assertNotNull(servico.alterar(getPessoaMock()));
    }
    
    @Test
    public void listaPessoasTest(){
        assertNotNull(servico.listaPessoas());
    }
    
    @Test
    public void excluirTest(){
        servico.excluir(1);
    }
    
    @Test
    public void insereAmigoTest(){
        servico.InsereAmigo(1, getPessoaMock());
    }
    private List<PessoaEntity> getListaPessoaEntityMock() {
        List<PessoaEntity> listaPessoas = new ArrayList<>(); 
        listaPessoas.add(getPessoaEntityMock());
        return listaPessoas;
    }

    private List<AmizadeEntity> getListAmizadeMock() {
        List<AmizadeEntity> listaAmizade = new ArrayList<>(); 
        listaAmizade.add(getAmizadeMock());
        return listaAmizade;
    }

    private AmizadeEntity getAmizadeMock() {
        AmizadeEntity amizadeMock = new AmizadeEntity();
        amizadeMock.setIdAmizade(1);
        amizadeMock.setId1(1);
        amizadeMock.setId2(1);
        return amizadeMock;
    }

    private PessoaEntity getPessoaEntityMock() {
        PessoaEntity pessoaEntityMock = new PessoaEntity();
        pessoaEntityMock.setNome("teste");
        pessoaEntityMock.setPessoaId(1);
        pessoaEntityMock.setEmail("teste@gmail.com");
        pessoaEntityMock.setTelefone("12312312");
        return pessoaEntityMock;
    }

    private Pessoa getPessoaMock() {
        Pessoa pessoaMock = new Pessoa();
        pessoaMock.setNome("teste");
        pessoaMock.setPessoaId(1);
        pessoaMock.setTelefone("12312312");
        pessoaMock.setEmail("teste");
        return pessoaMock;
    }


}
