/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMarinCajeroServices.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fondo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfondo")
    private int IdFondo;
    @Column(name = "tipo")
    private String Tipo;
    @Column(name = "cantidad")
    private int Cantidad;
    @Column(name = "denominacion")
    private int Denominacion;
    public int getIdFondo() {
        return IdFondo;
    }
    public void setIdFondo(int IdFondo) {
        this.IdFondo = IdFondo;
    }
    public String getTipo() {
        return Tipo;
    }
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getDenominacion() {
        return Denominacion;
    }

    public void setDenominacion(int Denominacion) {
        this.Denominacion = Denominacion;
    }

}
