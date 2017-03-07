package com.teste.controller;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.teste.entity.PessoaEntity;
import com.teste.model.Pessoa;
import com.teste.repository.PessoaRepository;


/**
 * Essa classe vai expor os nosso métodos para serem acessasdos via http
 * 
 * @Path - Caminho para a chamada da classe que vai representar o nosso serviço
 * */
@Path("/service")
public class ServiceController {
		
	private final  PessoaRepository repository = new PessoaRepository();

	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova pessoa
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String Cadastrar(Pessoa pessoa){
		
		PessoaEntity entity = new PessoaEntity();
				
		try {

			entity.setNome(pessoa.getNome());
			entity.setEmail(pessoa.getEmail());
			entity.setEmpresa(pessoa.getEmpresa());
			entity.setTelefone(pessoa.getTelefone());
			
			repository.Salvar(entity);
			
			return "Registro cadastrado com sucesso!";
			
		} catch (Exception e) {
			
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
	
	}
	
	/**
	 * Essse método altera uma pessoa já cadastrada
	 * **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String Alterar(Pessoa pessoa){
		
		PessoaEntity entity = new PessoaEntity();
		
		try {

			entity.setPessoaId(pessoa.getPessoaId());
			entity.setNome(pessoa.getNome());
			entity.setEmail(pessoa.getEmail());
			entity.setEmpresa(pessoa.getEmpresa());
			entity.setTelefone(pessoa.getTelefone());
			
			repository.Alterar(entity);
			
			return "Registro alterado com sucesso!";
			
		} catch (Exception e) {
			
			return "Erro ao alterar o registro " + e.getMessage();
			
		}

	}
	/**
	 * Esse método lista todas pessoas cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todasPessoas")
	public List<Pessoa> TodasPessoas(){
		
		List<Pessoa> pessoas =  new ArrayList<Pessoa>();
		
		List<PessoaEntity> listaEntityPessoas = repository.TodasPessoas();
		
		for (PessoaEntity entity : listaEntityPessoas) {
			
			pessoas.add(new Pessoa(entity.getPessoaId(),
			        entity.getNome(),
			        entity.getEmail(),
			        entity.getTelefone(),
			        entity.getEmpresa()));
		}
		
		return pessoas;
	}
	
	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getPessoa/{codigo}")
	public Pessoa GetPessoa(@PathParam("codigo") Integer codigo){
		
		PessoaEntity entity = repository.GetPessoa(codigo);
		
		if(entity != null)
			return new Pessoa(entity.getPessoaId(),
                    entity.getNome(),
                    entity.getEmail(),
                    entity.getTelefone(),
                    entity.getEmpresa());
		
		return null;
	}
	
	/**
	 * Excluindo uma pessoa pelo código
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String Excluir(@PathParam("pessoaI  d") Integer pessoaId){
		
		try {
			
			repository.Excluir(pessoaId);
			
			return "Registro excluido com sucesso!";
			
		} catch (Exception e) {
		
			return "Erro ao excluir o registro! " + e.getMessage();
		}
		
	}
	
}
