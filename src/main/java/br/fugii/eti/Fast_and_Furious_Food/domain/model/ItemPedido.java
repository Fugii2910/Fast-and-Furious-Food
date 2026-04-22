package br.fugii.eti.Fast_and_Furious_Food.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class ItemPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double qtd;
    
    private double vUnit;
    
    private String obs;

    public ItemPedido() {
    }

    public ItemPedido(Long id, double qtd, double vUnit, String obs) {
        this.id = id;
        this.qtd = qtd;
        this.vUnit = vUnit;
        this.obs = obs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getvUnit() {
        return vUnit;
    }

    public void setvUnit(double vUnit) {
        this.vUnit = vUnit;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
}
