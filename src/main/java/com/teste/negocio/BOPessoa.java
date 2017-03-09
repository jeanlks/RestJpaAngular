package com.teste.negocio;

import java.util.ArrayList;
import java.util.List;

import com.teste.entity.AmizadeEntity;
import com.teste.entity.PessoaEntity;
import com.teste.model.Pessoa;
import com.teste.repository.PessoaRepository;

public class BOPessoa {
    public PessoaRepository repositoryPessoa = new PessoaRepository();
  
    public String cadastrar(Pessoa pessoa){
        PessoaEntity entity = new PessoaEntity();
        if (validaPessoa(pessoa)) {

            try {
                entity.setNome(pessoa.getNome());
                entity.setEmail(pessoa.getEmail());
                entity.setEmpresa(pessoa.getEmpresa());
                entity.setTelefone(pessoa.getTelefone());
                if (verificaEmailCadastrado(pessoa.getEmail())) {
                    return "Email já existente";
                } else {
                    repositoryPessoa.salvar(entity);
                }
                return "Registro cadastrado com sucesso!";

            } catch (Exception e) {
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
    
    public Pessoa getPessoaPorEmail(String email){
        PessoaEntity entity = repositoryPessoa.getPessoaPorEmail(email);
        if (entity != null)
            return new Pessoa(entity.getPessoaId(), entity.getNome(), entity.getEmail(), entity.getTelefone(),
                    entity.getEmpresa());
        return null;
    }
    
    public List<Pessoa> listarAmigosPorId(int id){
        List<Pessoa> amigos = new ArrayList<>();
        List<AmizadeEntity> listaEntityAmizadeIds = repositoryPessoa.listarAmigosPorId(id);
        for (AmizadeEntity entity : listaEntityAmizadeIds) {
            PessoaEntity entidadePessoa = repositoryPessoa.getPessoa(entity.getId2());
            amigos.add(new Pessoa(entidadePessoa.getPessoaId(), entidadePessoa.getNome(), entidadePessoa.getEmail(),
                    entidadePessoa.getTelefone(), entidadePessoa.getEmpresa()));
        }
        return amigos;
    }


    public Pessoa getPessoa(int id) {
        PessoaEntity entity = repositoryPessoa.getPessoa(id);
        if (entity != null)
            return new Pessoa(entity.getPessoaId(), entity.getNome(), entity.getEmail(), entity.getTelefone(),
                    entity.getEmpresa());
        return null;
    }


    public String excluir(Integer pessoaId) {
        try {
            repositoryPessoa.excluir(pessoaId);
            return "Registro excluido com sucesso!";
        } catch (Exception e) {
            return "Erro ao excluir o registro! " + e.getMessage();
        }
    }


    public List<Pessoa> listaPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        List<PessoaEntity> listaEntityPessoas = repositoryPessoa.listarPessoas();
        for (PessoaEntity entity : listaEntityPessoas) {
            pessoas.add(new Pessoa(entity.getPessoaId(), entity.getNome(), entity.getEmail(), entity.getTelefone(),
                    entity.getEmpresa()));
        }
        return pessoas;
    }


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
            return "Erro ao alterar o registro " + e.getMessage();
        }
    }
    
}
