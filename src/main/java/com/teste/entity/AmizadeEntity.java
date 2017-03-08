package com.teste.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "amizade")
public class AmizadeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int amizadeId;
    
    @Column
    private int id1;
    
    @Column
    private int id2;

    public int getIdAmizade() {
        return amizadeId;
    }

    public void setIdAmizade(int idAmizade) {
        this.amizadeId = idAmizade;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }
    
    
}
