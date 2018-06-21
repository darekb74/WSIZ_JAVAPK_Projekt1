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

    public void setId(Object id) {
        try {
            switch (id.getClass().getName()) {
                case "java.lang.String":
                    this.id = Long.parseLong((String) id);
                    break;
                default:
                    this.id = (Long) id;
                    break;
            }
        } catch (NumberFormatException e) {
            id = null;
        }
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

    public void setCena_jednostkowa(Object cena_jednostkowa) {
        try {
            switch (cena_jednostkowa.getClass().getName()) {
                case "java.lang.String":
                    this.cena_jednostkowa = Double.parseDouble((String) cena_jednostkowa);
                    break;
                default:
                    this.cena_jednostkowa = (Double) cena_jednostkowa;
                    break;
            }
        } catch (NumberFormatException e) {
            cena_jednostkowa = null;
        }
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
        hash = hash + Objects.hashCode(this.nazwa);
        hash = 11 * hash + Objects.hashCode(this.model);
        hash = 3 * hash + Objects.hashCode(this.cena_jednostkowa);
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
        if (!Objects.equals(this.nazwa, other.nazwa)) {
            return false;
        }
        if (!Objects.equals(this.producent, other.producent)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.jednostka, other.jednostka)) {
            return false;
        }
        if (!Objects.equals(this.cena_jednostkowa, other.cena_jednostkowa)) {
            return false;
        }
        return true;
    }

}
