package com.teste.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.teste.entity.AmizadeEntity;
import com.teste.entity.PessoaEntity;
import com.teste.model.Pessoa;
import com.teste.repository.PessoaRepository;

/**
 * Classe de negocio de Pessoa.
 * @author Jean
 *
 */
public class BOPessoa {
    Logger LOG = Logger.getLogger(BOPessoa.class.getName());
    public PessoaRepository repositoryPessoa = new PessoaRepository();
  
    /**
     * 
     * @param pessoa pessoa a cadastrar.
     * @return retorna mensagem de erro ou sucesso.
     */
    public String cadastrar(Pessoa pessoa){
        PessoaEntity entity = new PessoaEntity();
        if (validaPessoa(pessoa)) {

            try {
                entity.setNome(pessoa.getNome());
                entity.setEmail(pessoa.getEmail());
                entity.setEmpresa(pessoa.getEmpresa());
                entity.setTelefone(pessoa.getTelefone());
                if (verificaEmailCadastrado(pessoa.getEmail())) {
                    alterar(pessoa);
                } else {
                    repositoryPessoa.salvar(entity);
                }
                return "Registro cadastrado com sucesso!";

            } catch (Exception e) {
                LOG.log(Level.WARNING, e.getMessage(), e);
                return "Erro ao cadastrar um registro " + e.getMessage();
            }
        } else {
            return "Registro não inserido devido a falta de campos obrigatórios";
        }

    }
    

    /**
     * 
     * @param email
     *            email
     * @return false ou true se o email ja foi cadastrado.
     */
    public boolean verificaEmailCadastrado(String email) {
        if (getPessoaPorEmail(email) != null) {
            return true;
        }
        return false;
    }


    /**
     * 
     * @param pessoa
     *            objeto pessoa.
     * @return false ou true dependendo se a pessoa tem algum campo necessário
     *         null.
     */
    protected boolean validaPessoa(Pessoa pessoa) {
        if (pessoa.getNome() != null || pessoa.getEmail() != null || pessoa.getTelefone() != null) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param email email da pessoa a buscar.
     * @return retorna pessoa ou null.
     */
    public Pessoa getPessoaPorEmail(String email){
        PessoaEntity entity = repositoryPessoa.getPessoaPorEmail(email);
        if (entity != null)
            return new Pessoa(entity.getPessoaId(), entity.getNome(), entity.getEmail(), entity.getTelefone(),
                    entity.getEmpresa());
        return null;
    }
    
    

    /**
     * 
     * @param id da pessoa buscada.
     * @return retorna pessoa.
     */
    public Pessoa getPessoa(int id) {
        PessoaEntity entity = repositoryPessoa.getPessoa(id);
        if (entity != null)
            return new Pessoa(entity.getPessoaId(), entity.getNome(), entity.getEmail(), entity.getTelefone(),
                    entity.getEmpresa());
        return null;
    }


    /**
     * 
     * @param pessoaId id da pessoa a excluir.
     * @return mensagem de erro ou sucesso.
     */
    public String excluir(Integer pessoaId) {
        try {
            repositoryPessoa.excluir(pessoaId);
            return "Registro excluido com sucesso!";
        } catch (Exception e) {
            LOG.log(Level.WARNING, e.getMessage(), e);
            return "Erro ao excluir o registro! " + e.getMessage();
        }
    }


    /**
     * 
     * @return lista de pessoas.
     */
    public List<Pessoa> listaPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        List<PessoaEntity> listaEntityPessoas = repositoryPessoa.listarPessoas();
        for (PessoaEntity entity : listaEntityPessoas) {
            pessoas.add(new Pessoa(entity.getPessoaId(), entity.getNome(), entity.getEmail(), entity.getTelefone(),
                    entity.getEmpresa()));
        }
        return pessoas;
    }

    /**
     * 
     * @param pessoa pessoa a alterar dados.
     * @return mensagem de erro ou sucesso.
     */
    public String alterar(Pessoa pessoa) {
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
            LOG.log(Level.WARNING, e.getMessage(), e);
            return "Erro ao alterar o registro " + e.getMessage();
        }
    }
    
}
