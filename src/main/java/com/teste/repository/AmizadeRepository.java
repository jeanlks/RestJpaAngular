package com.teste.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.teste.entity.AmizadeEntity;
import com.teste.entity.PessoaEntity;

public class AmizadeRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public AmizadeRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public void salvar(AmizadeEntity amizadeEntity) {

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(amizadeEntity);
        this.entityManager.getTransaction().commit();
    }

    public void alterar(AmizadeEntity amizadeEntity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(amizadeEntity);
        this.entityManager.getTransaction().commit();
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
