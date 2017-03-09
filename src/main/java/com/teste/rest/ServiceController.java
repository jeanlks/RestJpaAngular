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
import com.teste.model.Pessoa;
import com.teste.repository.AmizadeRepository;
import com.teste.repository.PessoaRepository;

/**
 * Classe com os recursos
 * 
 * @Path - Caminho para a chamada do recurso
 */
@Path("/service")
public class ServiceController {

    PessoaRepository repositoryPessoa = new PessoaRepository();
    AmizadeRepository repositoryAmizade = new AmizadeRepository();

    /**
     * @Consumes - determina formato dos dados consumidos.
     * @Produces - determina formato dos dados retornados.
     * @param pessoa
     *            pessoa para cadastro
     * @return mensagem de erro ou sucesso. Cadastro de Pessoa
     */
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(Pessoa pessoa) {
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
    protected boolean verificaEmailCadastrado(String email) {
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
     * @param pessoa
     *            a pessoa no banco.
     * @return mensagem de sucesso ou erro.
     */
    @PUT
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")
    @Path("/alterar")
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

    /**
     * 
     * @return todas pessoas do banco.
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/todasPessoas")
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
     * @param id
     *            codigo da pessoa para busca
     * @return retorna a pessoa pelo id.
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getPessoa/{id}")
    public Pessoa getPessoa(@PathParam("id") Integer id) {
        PessoaEntity entity = repositoryPessoa.getPessoa(id);
        if (entity != null)
            return new Pessoa(entity.getPessoaId(), entity.getNome(), entity.getEmail(), entity.getTelefone(),
                    entity.getEmpresa());
        return null;
    }

    /**
     * 
     * @param email
     *            email da pessoa a buscar.
     * @return pessoa.
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getPessoaPorEmail/{email}")
    public Pessoa getPessoaPorEmail(@PathParam("email") String email) {
        PessoaEntity entity = repositoryPessoa.getPessoaPorEmail(email);
        if (entity != null)
            return new Pessoa(entity.getPessoaId(), entity.getNome(), entity.getEmail(), entity.getTelefone(),
                    entity.getEmpresa());
        return null;
    }

    /**
     * 
     * @param id
     *            id da pessoa da qual quer buscar a lista.
     * @return lista de amigos.
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/getAmigosPorIdPessoa/{id}")
    public List<Pessoa> listarAmigosPorId(@PathParam("id") int id) {
        List<Pessoa> amigos = new ArrayList<>();
        List<AmizadeEntity> listaEntityAmizadeIds = repositoryPessoa.listarAmigosPorId(id);
        for (AmizadeEntity entity : listaEntityAmizadeIds) {
            PessoaEntity entidadePessoa = repositoryPessoa.getPessoa(entity.getId2());
            amigos.add(new Pessoa(entidadePessoa.getPessoaId(), entidadePessoa.getNome(), entidadePessoa.getEmail(),
                    entidadePessoa.getTelefone(), entidadePessoa.getEmpresa()));
        }
        return amigos;
    }

    /**
     * 
     * @param id
     *            id da pessoa
     * @param pessoa
     *            amigo
     * @return mensagem de erro ou sucesso.
     */
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/insereAmigo/{id}")
    public String InsereAmigo(@PathParam("id") int id, Pessoa pessoa) {
        PessoaEntity entity = new PessoaEntity();
        try {
            entity.setNome(pessoa.getNome());
            entity.setEmail(pessoa.getEmail());
            entity.setEmpresa(pessoa.getEmpresa());
            entity.setTelefone(pessoa.getTelefone());

            repositoryAmizade.insereAmigo(id, entity);

            return "Registro cadastrado com sucesso!";

        } catch (Exception e) {

            return "Erro ao cadastrar um registro " + e.getMessage();
        }

    }

    /**
     * 
     * @param pessoaId
     *            id da pessoa para deletar.
     * @return retorna o erro ou mensagem de sucesso.
     */
    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluir/{codigo}")
    public String excluir(@PathParam("pessoaId") Integer pessoaId) {
        try {
            repositoryPessoa.excluir(pessoaId);
            return "Registro excluido com sucesso!";
        } catch (Exception e) {
            return "Erro ao excluir o registro! " + e.getMessage();
        }
    }

    /**
     * 
     * @param id1
     *            id1 da pessoa a ser excluida
     * @param id2
     *            id2 da pessoa a ser excluida
     * @return retorna mensagem de erro ou sucesso.
     */
    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/excluirAmizade/{id1}/{id2}")
    public String excluirAmizade(@PathParam("id1") int id1, @PathParam("id2") int id2) {
        try {
            repositoryAmizade.removerAmigo(id1, id2);
            return "Registro excluido com sucesso!";
        } catch (Exception e) {
            return "Erro ao excluir o registro! " + e.getMessage();
        }

    }

}
