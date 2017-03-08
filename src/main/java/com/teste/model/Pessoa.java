package com.teste.model;


import javax.xml.bind.annotation.XmlRootElement;
/**
 * Classe Pessoa model.
 * @author Jean
 *
 */
@XmlRootElement
public class Pessoa {

	private int pessoaId;
	private String nome;
	private String email;
	private String telefone;
	private String empresa;
	
	public Pessoa(){
		
	}
	
	

	public Pessoa(int pessoaId, String nome, String email, String telefone, String empresa) {
        super();
        this.pessoaId = pessoaId;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.empresa = empresa;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getTelefone() {
        return telefone;
    }



    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }



    public String getEmpresa() {
        return empresa;
    }



    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }



	public int getPessoaId() {
        return pessoaId;
    }



    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }



    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	

}
