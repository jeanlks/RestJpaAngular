package com.teste.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import com.teste.model.Pessoa;
import com.teste.negocio.BOAmizade;
import com.teste.negocio.BOPessoa;
import com.teste.repository.AmizadeRepository;


/**
 * Classe com os recursos
 * 
 */
@Path("/service")
public class ServiceController {
    AmizadeRepository repositoryAmizade = new AmizadeRepository();
    BOPessoa pessoaBO = new BOPessoa();
    BOAmizade amizadeBO = new BOAmizade();

    /**
     *
     * @param pessoa
     *            pessoa para cadastro
     * @return mensagem de erro ou sucesso. Cadastro de Pessoa
     */
    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(Pessoa pessoa) {
        return pessoaBO.cadastrar(pessoa);
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
        return pessoaBO.alterar(pessoa);
    }

    /**
     * 
     * @return todas pessoas do banco.
     */
    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/todasPessoas")
    public List<Pessoa> listaPessoas() {
     return pessoaBO.listaPessoas();
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
       return pessoaBO.getPessoa(id);
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
        return pessoaBO.getPessoaPorEmail(email);
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
        return pessoaBO.listarAmigosPorId(id);
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
        return amizadeBO.insereAmigo(id, pessoa);
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
       return pessoaBO.excluir(pessoaId);
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
       return amizadeBO.excluir(id1,id2);
    }

}
