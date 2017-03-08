package com.teste.rest;


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
import com.teste.repository.AmizadeRepository;
import com.teste.repository.PessoaRepository;


/**
 * Classe que expoe recursos
 * 
 * @Path - Caminho para a chamada do recurso
 * */
@Path("/service")
public class ServiceController {
		
     PessoaRepository repositoryPessoa = new PessoaRepository();
     AmizadeRepository repositoryAmizade = new AmizadeRepository();
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
	public String cadastrar(Pessoa pessoa){
		
		PessoaEntity entity = new PessoaEntity();
			if(validaPessoa(pessoa)){
			    try {

		            entity.setNome(pessoa.getNome());
		            entity.setEmail(pessoa.getEmail());
		            entity.setEmpresa(pessoa.getEmpresa());
		            entity.setTelefone(pessoa.getTelefone());
		            if(verificaEmailCadastrado(pessoa.getEmail())){
		                return "Email já existente";
		            }else{
		                repositoryPessoa.salvar(entity);
		            }
		            return "Registro cadastrado com sucesso!";
		            
		        } catch (Exception e) {
		            
		            return "Erro ao cadastrar um registro " + e.getMessage();
		        }
			}else{
			    return "Registro não inserido devido a falta de campos obrigatórios";
			}
		
	
	}
	    
	protected boolean verificaEmailCadastrado(String email) {
        if(getPessoaPorEmail(email)!=null){
            return true;
        }
        return false;
    }

    protected boolean validaPessoa(Pessoa pessoa) {
        if(pessoa.getNome()!=null 
           || pessoa.getEmail()!=null
           || pessoa.getTelefone()!=null){
            return true;
        }
        return false;
    }

    /**
	 * Altera Pessoa cadastrada.
	 * **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String alterar(Pessoa pessoa){
		
		PessoaEntity entity = new PessoaEntity();
		
		try {

			entity.setPessoaId(pessoa.getPessoaId());
			entity.setNome(pessoa.getNome());
			entity.setEmail(pessoa.getEmail());
			entity.setEmpresa(pessoa.getEmpresa());
			entity.setTelefone(pessoa.getTelefone());
			
			repositoryPessoa.alterar(entity);
			
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
	public List<Pessoa> listaPessoas(){
		
		List<Pessoa> pessoas =  new ArrayList<Pessoa>();
		
		List<PessoaEntity> listaEntityPessoas = repositoryPessoa.listarPessoas();
		
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
	public Pessoa getPessoa(@PathParam("codigo") Integer codigo){
		
		PessoaEntity entity = repositoryPessoa.getPessoa(codigo);
		
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
    public Pessoa getPessoaPorEmail(@PathParam("email") String email){
        
        PessoaEntity entity = repositoryPessoa.getPessoaPorEmail(email);
        
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
        
        List<AmizadeEntity> listaEntityAmizadeIds = repositoryPessoa.listarAmigosPorId(id);
        
        for (AmizadeEntity entity : listaEntityAmizadeIds) {
            PessoaEntity entidadePessoa = repositoryPessoa.getPessoa(entity.getId2());
            amigos.add(new Pessoa(entidadePessoa.getPessoaId(),
                       entidadePessoa.getNome(),
                       entidadePessoa.getEmail(),
                       entidadePessoa.getTelefone(),
                       entidadePessoa.getEmpresa()));
        }
        
        return amigos;
    }
    

//    /**
//     * @Consumes - determina formato dos dados consumidos.
//     * @Produces - determina formato dos dados retornados.
//     * 
//     * Cadastro de Pessoa
//     * */
//    @POST   
//    @Consumes("application/json; charset=UTF-8")
//    @Produces("application/json; charset=UTF-8")
//    @Path("/insereAmigo")
//    public String InsereAmigo(int id,Pessoa pessoa){
//        
//        PessoaEntity entity = new PessoaEntity();
//                
//        try {
//
//            entity.setNome(pessoa.getNome());
//            entity.setEmail(pessoa.getEmail());
//            entity.setEmpresa(pessoa.getEmpresa());
//            entity.setTelefone(pessoa.getTelefone());
//            
//            repository.insereAmigo(id,entity);
//            
//            return "Registro cadastrado com sucesso!";
//            
//        } catch (Exception e) {
//            
//            return "Erro ao cadastrar um registro " + e.getMessage();
//        }
//    
//    }
	
	/**
	 * Excluindo uma pessoa pelo código
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String excluir(@PathParam("pessoaId") Integer pessoaId){
		
		try {
			
			repositoryPessoa.excluir(pessoaId);
			
			return "Registro excluido com sucesso!";
			
		} catch (Exception e) {
		
			return "Erro ao excluir o registro! " + e.getMessage();
		}
		
	}
	   /**
     * Excluindo uma amizade pelo código
     * */
    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluirAmizade/{id1}/{id2}")  
    public String excluirAmizade(@PathParam("id1") int id1, @PathParam("id2") int id2){
        
        try {
            
            repositoryAmizade.removerAmigo(id1, id2);
            
            return "Registro excluido com sucesso!";
            
        } catch (Exception e) {
        
            return "Erro ao excluir o registro! " + e.getMessage();
        }
        
    }
	
}
