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
	public PessoaEntity getPessoa(final Integer pessoaId){
		
		return this.entityManager.find(PessoaEntity.class, pessoaId);
	}
	
	/**
	 * EXCLUINDO UM REGISTRO PELO CÓDIGO
	**/
	public void Excluir(final Integer pessoaId){
		
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
	 
	 @SuppressWarnings("unchecked")
	 public void removerAmigo(final int id1,final int id2){
	     this.entityManager
	     .createQuery("DELETE FROM AmizadeEntity WHERE ID1 LIKE :ID1 AND  ID2 LIKE :ID2 ")
	     .setParameter("ID1", id1)
	     .setParameter("ID2", id2);
	     
	     this.entityManager.flush();
	     
	 }
	 public void insereAmigo(final int idAmigo1,PessoaEntity pessoaEntity){
	        //Adiciona na tabela Pessoa
	        this.entityManager.getTransaction().begin();
	        this.entityManager.persist(pessoaEntity);
	        this.entityManager.getTransaction().commit();
	        //Cria objeto de amizade e posteriormente salva com a id gerada anteriormente
	        AmizadeEntity amizade = new AmizadeEntity();
	        amizade.setId1(idAmigo1);
	        amizade.setId2(pessoaEntity.getPessoaId());
	        
	        this.entityManager.getTransaction().begin();
            this.entityManager.persist(amizade);
            this.entityManager.getTransaction().commit();       
	 }
}
