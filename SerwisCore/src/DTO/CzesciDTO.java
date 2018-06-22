/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Darek Xperia
 */
public class CzesciDTO implements Serializable {

    private Long id;
    private String nazwa;
    private String producent;
    private String model;
    private String jednostka;
    private Double cena_jednostkowa;

    public CzesciDTO(Long id_, String nazwa_, String producent_, String model_, String jednostka_, Double cena_jednostkowa_) {
        id = id_;
        nazwa = nazwa_;
        producent = producent_;
        model = model_;
        jednostka = jednostka_;
        cena_jednostkowa = cena_jednostkowa_;
    }

    public Long getId() {
        return id;
    }

    public void setId(String id) {
        try {
            this.id = Long.parseLong((String) id);
        } catch (NumberFormatException e) {
            this.id = null;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }

    public Double getCena_jednostkowa() {
        return cena_jednostkowa;
    }

    public void setCena_jednostkowa(String cena_jednostkowa) {
        try {
            this.cena_jednostkowa = Double.parseDouble((String) cena_jednostkowa);
        } catch (NumberFormatException e) {
            this.cena_jednostkowa = null;
        }
    }

    public void setCena_jednostkowa(Double cena_jednostkowa) {
        this.cena_jednostkowa = cena_jednostkowa;
    }

    public Object[] toArray() {
        return new Object[]{getId(),
            nazwa,
            producent,
            model,
            jednostka,
            cena_jednostkowa};
    }

    @Override
    public String toString() {
        return "" + getId() + ", "
                + nazwa + ", "
                + producent + ", "
                + model + ", "
                + jednostka + ", "
                + cena_jednostkowa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CzesciDTO other = (CzesciDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
