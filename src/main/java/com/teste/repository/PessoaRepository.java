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
		
		
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_estudo");
		
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public void Salvar(PessoaEntity pessoaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(pessoaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void Alterar(PessoaEntity pessoaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(pessoaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * RETORNA TODAS AS PESSOAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<PessoaEntity> TodasPessoas(){
		
		return this.entityManager.createQuery("SELECT p FROM PessoaEntity p ORDER BY p.nome").getResultList();
	}
	
	/**
	 * CONSULTA UMA PESSOA CADASTRA PELO CÓDIGO
	 * */
	public PessoaEntity getPessoa(Integer pessoaId){
		
		return this.entityManager.find(PessoaEntity.class, pessoaId);
	}
	
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void Excluir(Integer pessoaId){
		
		PessoaEntity pessoa = this.getPessoa(pessoaId);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(pessoa);
		this.entityManager.getTransaction().commit();
		
	}
	@SuppressWarnings("unchecked")
    public PessoaEntity getPessoaPorEmail(String email) {
	    List<PessoaEntity> listaPessoas =  this.entityManager.createQuery("SELECT p FROM PessoaEntity p  WHERE NOME LIKE :email")
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
                  .createQuery("SELECT p FROM AmizadeEntity p  WHERE ID1 LIKE :id" )
                 .setParameter("id", id)  
                 .getResultList();
           return listaAmigos;
     }
}
