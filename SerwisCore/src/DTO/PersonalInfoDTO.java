/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Tabele.UserD;
import java.io.Serializable;
import javax.swing.JComboBox;

/**
 *
 * @author Darek Xperia
 */
public class PersonalInfoDTO implements Serializable{
    Long id;
    UserD user;
    String name;
    String address;
    String phone1;
    String phone2;
    // 0 - klient, 1 - firma, 2 - serwisant, 3 - magazynier, 4 - kireownik serwisu, 5 -
    Byte type;
    
    public PersonalInfoDTO(Long id, UserD user, String nazwa, String adres, String telefon1, String telefon2, Byte typ) {
        this(user, nazwa, adres, telefon1, telefon2, typ);
        this.id = id;
    }
    public PersonalInfoDTO(UserD user, String nazwa, String adres, String telefon1, String telefon2, Byte typ) {
        this.user = user;
        this.name = nazwa;
        this.address = adres;
        this.phone1 = telefon1;
        this.phone2 = telefon2;
        this.type = typ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    
    public Object[] toArray () {
        return new Object[] {getId(), 
                        user.getId(),
                        name,
                        address,
                        phone1,
                        phone2,
                        type};
    }
}
