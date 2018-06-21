/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabele;

import DTO.MagazynDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Darek Xperia
 */
@Entity
public class MagazynD implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // struktura tabeli czesci
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_czesci")
    private CzesciD czesc;
    private Integer regal;
    private Integer polka;
    private Integer ilosc;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CzesciD getCzesc() {
        return czesc;
    }

    public void setCzesc(CzesciD czesc) {
        this.czesc = czesc;
    }

    public Integer getRegal() {
        return regal;
    }

    public void setRegal(Integer regal) {
        this.regal = regal;
    }

    public Integer getPolka() {
        return polka;
    }

    public void setPolka(Integer polka) {
        this.polka = polka;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.czesc);
        hash = 17 * hash + Objects.hashCode(this.regal);
        hash = 17 * hash + Objects.hashCode(this.polka);
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
        final MagazynD other = (MagazynD) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.czesc, other.czesc)) {
            return false;
        }
        if (!Objects.equals(this.regal, other.regal)) {
            return false;
        }
        if (!Objects.equals(this.polka, other.polka)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tabele.MagazynD[ id=" + id + " ]";
    }
    
    public MagazynDTO getMagazynDTO() {
        return new MagazynDTO(
                this.getId(),
                this.czesc.getCzesciDTO(),
                this.regal,
                this.polka,
                this.ilosc);
    }

    public static ArrayList<MagazynDTO> getCzesciListDTO(ArrayList<MagazynD> lista) {
        ArrayList<MagazynDTO> tmp = new ArrayList<>();
        for (MagazynD u : lista) {
            tmp.add(u.getMagazynDTO());
        }
        return tmp;
    }
}
