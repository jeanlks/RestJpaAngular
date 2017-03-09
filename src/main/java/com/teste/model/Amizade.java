package com.teste.model;

/**
 * Classe Amizade model.
 * @author Jean
 *
 */
public class Amizade {
    private int idAmizade; // id Amizade
    private int id1; // id Pessoa 1
    private int id2; // id Pessoa 2
    public int getIdAmizade() {
        return idAmizade;
    }
    public void setIdAmizade(int idAmizade) {
        this.idAmizade = idAmizade;
    }
    public int getId1() {
        return id1;
    }
    public void setId1(final int id1) {
        this.id1 = id1;
    }
    public int getId2() {
        return id2;
    }
    public void setId2(final int id2) {
        this.id2 = id2;
    }
    
}
