package com.teste.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.teste.entity.AmizadeEntity;
import com.teste.entity.PessoaEntity;


/**
 * Classe de funcoes de repositorio para Pessoa.
 * @author Jean
 *
 */
public class PessoaRepository {

	private final EntityManagerFactory entityManagerFactory;
	
	private final EntityManager entityManager;
	
	/**
	 * Construtor de uso exclusivo do EntityManager.
	 */
	public PessoaRepository(){	
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
	
	/**
	 * 
	 * @param pessoaEntity entidade de pessoa para salvar.
	 */
	public void salvar(PessoaEntity pessoaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(pessoaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * 
	 * @param pessoaEntity entidade de pessoa para alterar.
	 */
	public void alterar(PessoaEntity pessoaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(pessoaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * 
	 * @return retorna lista de pessoas.
	 */
	@SuppressWarnings("unchecked")
	public List<PessoaEntity> listarPessoas(){
		
		return this.entityManager.createQuery("SELECT p FROM PessoaEntity p ORDER BY p.nome").getResultList();
	}
	
	/**
	 * 
	 * @param pessoaId id da pessoa.
	 * @return pessoa de acordo com o ID fornecido.
	 */
	public PessoaEntity getPessoa(final Integer pessoaId){
		
		return this.entityManager.find(PessoaEntity.class, pessoaId);
	}
	
	/**
	 * 
	 * @param pessoaId id da Pessoa.
	 */
	public void excluir(final Integer pessoaId){		
		PessoaEntity pessoa = this.getPessoa(pessoaId);	
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(pessoa);
		this.entityManager.getTransaction().commit();
		
	}
	/**
	 * 
	 * @param email email da pessoa.
	 * @return retorna a pessoa com aquele email.
	 */
	@SuppressWarnings("unchecked")
    public PessoaEntity getPessoaPorEmail(final String email) {
	    List<PessoaEntity> listaPessoas =  this.entityManager.createQuery("SELECT p FROM PessoaEntity p  WHERE email LIKE :email")
                .setParameter("email", email)
                .getResultList();
        if(!listaPessoas.isEmpty()){
            return listaPessoas.get(0);
        }
        else{
            return null;
        }
    }
	
	 
}
