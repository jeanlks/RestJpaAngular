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

import com.teste.entity.AmizadeEntity;
import com.teste.entity.PessoaEntity;
import com.teste.model.Amizade;
import com.teste.model.Pessoa;
import com.teste.repository.PessoaRepository;


/**
 * Classe que expoe recursos
 * 
 * @Path - Caminho para a chamada do recurso
 * */
@Path("/service")
public class ServiceController {
		
	private final  PessoaRepository repository = new PessoaRepository();

	/**
	 * @Consumes - determina formato dos dados consumidos.
	 * @Produces - determina formato dos dados retornados.
	 * 
	 * Cadastro de Pessoa
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
	 * Altera Pessoa cadastrada.
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
	 * ListaTodasPessoas
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
		
		PessoaEntity entity = repository.getPessoa(codigo);
		
		if(entity != null)
			return new Pessoa(entity.getPessoaId(),
                    entity.getNome(),
                    entity.getEmail(),
                    entity.getTelefone(),
                    entity.getEmpresa());
		
		return null;
	}
	/**
     * Esse método busca uma pessoa cadastrada pelo nome
     * */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getPessoaPorEmail/{email}")
    public Pessoa GetPessoaPorNome(@PathParam("email") String email){
        
        PessoaEntity entity = repository.getPessoaPorEmail(email);
        
        if(entity != null)
            return new Pessoa(entity.getPessoaId(),
                    entity.getNome(),
                    entity.getEmail(),
                    entity.getTelefone(),
                    entity.getEmpresa());
        
        return null;
    }
    
    /**
     * Esse método busca uma pessoa cadastrada pelo nome
     * */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getAmigosPorIdPessoa/{id}")
    public List<Pessoa> listarAmigosPorId (@PathParam("id") int id){
        
        List<Pessoa> amigos =  new ArrayList<Pessoa>();
        
        List<AmizadeEntity> listaEntityAmizadeIds = repository.listarAmigosPorId(id);
        
        for (AmizadeEntity entity : listaEntityAmizadeIds) {
            PessoaEntity entidadePessoa = repository.getPessoa(entity.getId2());
            amigos.add(new Pessoa(entidadePessoa.getPessoaId(),
                       entidadePessoa.getNome(),
                       entidadePessoa.getEmail(),
                       entidadePessoa.getTelefone(),
                       entidadePessoa.getEmpresa()));
        }
        
        return amigos;
    }
	
	/**
	 * Excluindo uma pessoa pelo código
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String Excluir(@PathParam("pessoaId") Integer pessoaId){
		
		try {
			
			repository.Excluir(pessoaId);
			
			return "Registro excluido com sucesso!";
			
		} catch (Exception e) {
		
			return "Erro ao excluir o registro! " + e.getMessage();
		}
		
	}
	
}
