/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
    private Byte rmask;

    public UserDTO(Long id_, String username_, String password_hash_, String eMail_, Date last_login_, Boolean isOnline_, Byte rmask_) {
        id = id_;
        username = username_;
        password_hash = password_hash_;
        eMail = eMail_;
        last_login = last_login_;
        isOnline = isOnline_;
        rmask = rmask_;
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

    public Byte getRmask() {
        return rmask;
    }

    public void setRmask(Byte rmask) {
        this.rmask = rmask;
    }

    public Object[] toArray() {
        return new Object[]{getId(),
            username,
            password_hash,
            eMail,
            last_login,
            isOnline,
            rmask};
    }

    @Override
    public String toString() {
        return "" + getId() + ", "
                + username + ", "
                + password_hash + ", "
                + eMail + ", "
                + last_login + ", "
                + isOnline + ", "
                + rmask;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.username);
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
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password_hash, other.password_hash)) {
            return false;
        }
        if (!Objects.equals(this.eMail, other.eMail)) {
            return false;
        }
        if (!Objects.equals(this.last_login, other.last_login)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.isOnline, other.isOnline)) {
            return false;
        }
        if (!Objects.equals(this.rmask, other.rmask)) {
            return false;
        }
        return true;
    }
    
}
