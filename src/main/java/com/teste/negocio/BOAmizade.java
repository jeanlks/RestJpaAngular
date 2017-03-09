package com.teste.negocio;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.teste.entity.AmizadeEntity;
import com.teste.entity.PessoaEntity;
import com.teste.model.Pessoa;
import com.teste.repository.AmizadeRepository;

/**
 * Classe de negocio de amizade.
 * @author Jean
 *
 */
public class BOAmizade {
    Logger LOG = Logger.getLogger(BOAmizade.class.getName());
    public AmizadeRepository repositoryAmizade = new AmizadeRepository();
    BOPessoa pessoaBO = new BOPessoa();
    /**
     * 
     * @param id do primeiro amigo.
     * @param pessoa pessoa a ser adicionada como amigo.
     * @return mensagem de erro ou sucesso.
     */
    public String insereAmigo(int id, Pessoa pessoa) {
        PessoaEntity entity = new PessoaEntity();
        try {
            Pessoa pessoaRetorno = pessoaBO.getPessoaPorEmail(pessoa.getEmail());
            if(pessoaRetorno!=null){
                AmizadeEntity amizade = new AmizadeEntity();
                amizade.setId1(id);
                amizade.setId2(pessoaRetorno.getPessoaId());
                repositoryAmizade.salvar(amizade);
            }
            else{
                entity.setNome(pessoa.getNome());
                entity.setEmail(pessoa.getEmail());
                entity.setEmpresa(pessoa.getEmpresa());
                entity.setTelefone(pessoa.getTelefone());
                repositoryAmizade.inserePessoaEAdicionaComoAmigo(id, entity);
            }
            

            return "Registro cadastrado com sucesso!";

        } catch (Exception e) {
            LOG.log(Level.WARNING, e.getMessage(), e);
            return "Erro ao cadastrar um registro " + e.getMessage();
        }
    }
    
    /**
     * 
     * @param id1 id da pessoa1 da amizade.
     * @param id2 id da pessoa2 da amizade.
     * @return retorna mensagem de erro ou sucesso.
     */
    public String excluir(int id1, int id2) {
        try {
            repositoryAmizade.removerAmigo(id1, id2);
            return "Registro excluido com sucesso!";
        } catch (Exception e) {
            LOG.log(Level.WARNING, e.getMessage(), e);
            return "Erro ao excluir o registro! " + e.getMessage();
        }

    }
}
