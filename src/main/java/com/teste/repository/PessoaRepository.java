package com.teste.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.teste.entity.AmizadeEntity;
import com.teste.entity.PessoaEntity;



public class PessoaRepository {

	private final EntityManagerFactory entityManagerFactory;
	
	private final EntityManager entityManager;
	
	public PessoaRepository(){	
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
	
	/**
	 * Salva Entidade Pessoa
	 * */
	public void salvar(PessoaEntity pessoaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(pessoaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * Altera Pessoa
	 * */
	public void alterar(PessoaEntity pessoaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(pessoaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * Retorna Todas Pessoas Cadastradas 
	 * */
	@SuppressWarnings("unchecked")
	public List<PessoaEntity> listarPessoas(){
		
		return this.entityManager.createQuery("SELECT p FROM PessoaEntity p ORDER BY p.nome").getResultList();
	}
	
	/**
	 * Retorna pessoa pelo codigo
	 * */
	public PessoaEntity getPessoa(final Integer pessoaId){
		
		return this.entityManager.find(PessoaEntity.class, pessoaId);
	}
	
	/**
	 * Exclui registro.
	**/
	public void excluir(final Integer pessoaId){		
		PessoaEntity pessoa = this.getPessoa(pessoaId);	
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(pessoa);
		this.entityManager.getTransaction().commit();
		
	}
	@SuppressWarnings("unchecked")
    public PessoaEntity getPessoaPorEmail(final String email) {
	    List<PessoaEntity> listaPessoas =  this.entityManager.createQuery("SELECT p FROM PessoaEntity p  WHERE email LIKE :email")
                .setParameter("email", email)
                .getResultList();
        if(listaPessoas.size()>0){
            return listaPessoas.get(0);
        }
        else{
            return null;
        }
    }
	 @SuppressWarnings("unchecked")
     public List<AmizadeEntity> listarAmigosPorId(int id){
           List<AmizadeEntity> listaAmigos =  this.entityManager
                  .createQuery("SELECT a FROM AmizadeEntity a  WHERE ID1 LIKE :id" )
                 .setParameter("id", id)  
                 .getResultList();
           return listaAmigos;
     }
	 
}
