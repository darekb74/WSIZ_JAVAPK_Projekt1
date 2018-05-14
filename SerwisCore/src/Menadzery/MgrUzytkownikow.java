/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menadzery;

import DTO.UserDTO;
import Tabele.UserD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darek Xperia
 */
public class MgrUzytkownikow {

    public UserD map(UserDTO dtoU) {
        UserD u = new UserD();
        u.setId(dtoU.getId());
        u.setUsername(dtoU.getUsername());
        u.setPassword_hash(dtoU.getPassword_hash());
        u.seteMail(dtoU.geteMail());
        u.setIsOnline(dtoU.isOnline());
        u.setLast_login(dtoU.getLast_login());
        u.setRmask(dtoU.getRmask());
        return u;
    }

    public List<UserD> mapList(List<UserDTO> dtoUL) {
        List<UserD> tmp = new ArrayList<>();
        dtoUL.forEach((dtoULe) -> {
            tmp.add(map(dtoULe));
        });
        return tmp;
    }

}
