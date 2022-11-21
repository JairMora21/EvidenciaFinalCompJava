/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jajimenez
 */
@Entity
public class Imc implements Serializable {

    @Id
    private Integer id;
    private String fecha;
    private Double peso;
    private Double altura;
    private Double imcres;

    public Imc() {
    }

    public Imc(Integer id, String fecha, Double peso, Double altura, Double imcres) {
        this.id = id;
        this.fecha = fecha;
        this.peso = peso;
        this.altura = altura;
        this.imcres = imcres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getImcres() {
        return imcres;
    }

    public void setImcres(Double imcres) {
        this.imcres = imcres;
    }
}
