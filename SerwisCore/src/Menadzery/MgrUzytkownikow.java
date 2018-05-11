/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menadzery;

import DTO.UserDTO;
import Tabele.UserD;
import java.util.ArrayList;

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

    public ArrayList<UserD> mapList(ArrayList<UserDTO> dtoUL) {
        ArrayList<UserD> tmp = new ArrayList<>();
        for (UserDTO dtoULe : dtoUL) {
            UserD u = new UserD();
            u.setId(dtoULe.getId());
            u.setUsername(dtoULe.getUsername());
            u.setPassword_hash(dtoULe.getPassword_hash());
            u.seteMail(dtoULe.geteMail());
            u.setIsOnline(dtoULe.isOnline());
            u.setLast_login(dtoULe.getLast_login());
            u.setRmask(dtoULe.getRmask());
            tmp.add(u);
        }
        return tmp;
    }

}
