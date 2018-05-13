/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabele;

import DTO.PersonalInfoDTO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Darek Xperia
 */
@Entity
public class PersonalInfo extends Tabela implements Serializable {

    @OneToOne
    UserD user;
    @Column(length = 128)
    String name;
    @Column(length = 128)
    String address;
    @Column(length = 16)
    String phone1;
    @Column(length = 16)
    String phone2;
    @Column(columnDefinition = "SMALLINT DEFAULT 0")
    // 0 - klient, 1 - firma, 2 - serwisant, 3 - magazynier, 4 - kireownik serwisu, 5 -
    Byte type;

    public UserD getUser() {
        return user;
    }

    public void setUser(UserD user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalInfo)) {
            return false;
        }
        PersonalInfo other = (PersonalInfo) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[PersonalInfo] id=" + this.getId() + ", userID=" + user.getId();
    }

    public PersonalInfoDTO getPInfoDTO() {
        return new PersonalInfoDTO(
                this.getId(), user, name, address, phone1, phone2, type);
    }
}
