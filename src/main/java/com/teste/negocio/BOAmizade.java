package com.teste.negocio;

import com.teste.entity.AmizadeEntity;
import com.teste.entity.PessoaEntity;
import com.teste.model.Pessoa;
import com.teste.repository.AmizadeRepository;

public class BOAmizade {
    public AmizadeRepository repositoryAmizade = new AmizadeRepository();
    BOPessoa pessoaBO = new BOPessoa();
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

            return "Erro ao cadastrar um registro " + e.getMessage();
        }
    }
}
