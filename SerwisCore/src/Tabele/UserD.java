/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabele;

import DTO.UserDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author Darek Xperia
 */
@Entity
public class UserD extends Tabela implements Serializable {

    // struktura tabeli user
    @Column(unique = true, length = 32)
    private String username;

    @Column(length = 32) // md5
    private String password_hash;

    @Column(length = 64)
    private String eMail;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    // 1970-01-01 00:00:00
    //@Column(length = 19)
    private Date last_login;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isOnline;

    @Column(columnDefinition = "SMALLINT DEFAULT 0")
    private Short rmask;

    @Override
    public String toString() {
        return "[User] id=" + this.getId() + ", username=" + username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Short getRmask() {
        return rmask;
    }

    public void setRmask(Short rmask) {
        this.rmask = rmask;
    }

    public UserDTO getUserDTO() {
        return new UserDTO(
                this.getId(),
                this.username,
                this.password_hash,
                this.eMail,
                this.last_login,
                this.isOnline,
                this.rmask);
    }

    public static ArrayList<UserDTO> getUserListDTO(ArrayList<UserD> lista) {
        ArrayList<UserDTO> tmp = new ArrayList<>();
        for (UserD u : lista) {
            tmp.add(u.getUserDTO());
        }
        return tmp;
    }
}
