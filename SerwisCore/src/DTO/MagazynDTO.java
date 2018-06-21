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

    public CzesciDTO getCzesc() {
        return czesc;
    }

    public void setCzesc(CzesciDTO czesc) {
        this.czesc = czesc;
    }

    public Integer getRegal() {
        return regal;
    }

    public void setRegal(Object regal) {
        try {
            switch (id.getClass().getName()) {
                case "java.lang.String":
                    this.regal = Integer.parseInt((String) regal);
                    break;
                default:
                    this.regal = (Integer) regal;
                    break;
            }
        } catch (NumberFormatException e) {
            regal = null;
        }
    }

    public Integer getPolka() {
        return polka;
    }

    public void setPolka(Object polka) {
        try {
            switch (id.getClass().getName()) {
                case "java.lang.String":
                    this.polka = Integer.parseInt((String) polka);
                    break;
                default:
                    this.polka = (Integer) polka;
                    break;
            }
        } catch (NumberFormatException e) {
            polka = null;
        }
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Object ilosc) {
        try {
            switch (id.getClass().getName()) {
                case "java.lang.String":
                    this.ilosc = Integer.parseInt((String) ilosc);
                    break;
                default:
                    this.ilosc = (Integer) ilosc;
                    break;
            }
        } catch (NumberFormatException e) {
            ilosc = null;
        }
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
        hash = 89 * hash + Objects.hashCode(this.czesc);
        hash = 89 * hash + Objects.hashCode(this.regal);
        hash = 89 * hash + Objects.hashCode(this.polka);
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

    

}
