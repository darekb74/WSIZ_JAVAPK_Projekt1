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
public class MagazynDTO implements Serializable {

    private Long id;
    private CzesciDTO czesc;
    private Integer regal;
    private Integer polka;
    private Integer ilosc;

    public MagazynDTO(Long id_, CzesciDTO czesc_, Integer regal_, Integer polka_, Integer ilosc_) {
        id = id_;
        czesc = czesc_;
        regal = regal_;
        polka = polka_;
        ilosc = ilosc_;
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
    
    public boolean sprawdzWarunekFiltrowania(String tekst) {
        return czesc.sprawdzWarunekFiltrowania(tekst);
    }

    public CzesciDTO getCzesc() {
        return czesc;
    }

    public void setCzesc(CzesciDTO czesc) {
        this.czesc = czesc;
    }

    public Integer getRegal() {
        return regal;
    }

    public void setRegal(String regal) {
        try {
            this.regal = Integer.parseInt((String) regal);
        } catch (NumberFormatException e) {
            this.regal = null;
        }
    }

    public void setRegal(Integer regal) {
        this.regal = regal;
    }

    public Integer getPolka() {
        return polka;
    }

    public void setPolka(String polka) {
        try {
            this.polka = Integer.parseInt((String) polka);
        } catch (NumberFormatException e) {
            this.polka = null;
        }
    }

    public void setPolka(Integer polka) {
        this.polka = polka;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        try {
            this.ilosc = Integer.parseInt((String) ilosc);
        } catch (NumberFormatException e) {
            this.ilosc = null;
        }
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Object[] toArray() {
        return new Object[]{getId(),
            czesc,
            regal,
            polka,
            ilosc};
    }

    @Override
    public String toString() {
        return "" + getId() + ", "
                + czesc.getNazwa() + ", "
                + regal + ", "
                + polka + ", "
                + ilosc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final MagazynDTO other = (MagazynDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
