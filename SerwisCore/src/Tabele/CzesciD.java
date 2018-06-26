/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabele;

import DTO.CzesciDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Darek Xperia
 */
@Entity
public class CzesciD implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // struktura tabeli czesci
    @Column(unique = true, length = 128)
    private String nazwa;

    @Column(length = 32)
    private String producent;
    
    @Column(length = 32)
    private String model;
    
    @Column(length = 10)
    private String jednostka;
    
    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0")
    private Double cena_jednostkowa;
    
    // czy konieczne ?
    @OneToMany(mappedBy = "czesc")
    private List<MagazynD> pozycje_w_magazynie;

    
    public Long getId() {
        return id;
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

    public void setCena_jednostkowa(Double cena_jednostkowa) {
        this.cena_jednostkowa = cena_jednostkowa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CzesciD)) {
            return false;
        }
        CzesciD other = (CzesciD) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tabele.CzesciD[ id=" + id + " ]";
    }
    
    public CzesciDTO getCzesciDTO() {
        return new CzesciDTO(
                this.getId(),
                this.nazwa,
                this.producent,
                this.model,
                this.jednostka,
                this.cena_jednostkowa);
    }

    public static ArrayList<CzesciDTO> getCzesciListDTO(ArrayList<CzesciD> lista) {
        ArrayList<CzesciDTO> tmp = new ArrayList<>();
        for (CzesciD u : lista) {
            tmp.add(u.getCzesciDTO());
        }
        return tmp;
    }
}
