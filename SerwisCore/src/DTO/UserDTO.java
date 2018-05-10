/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Darek Xperia
 */
public class UserDTO implements Serializable {

    private Long id;
    private String username;
    private String password_hash;
    private String eMail;
    private Date last_login;
    private Boolean isOnline;

    public UserDTO(Long id_, String username_, String password_hash_, String eMail_, Date last_login_, Boolean isOnline_) {
        id = id_;
        username = username_;
        password_hash = password_hash_;
        eMail = eMail_;
        last_login = last_login_;
        isOnline = isOnline_;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean isOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

}
