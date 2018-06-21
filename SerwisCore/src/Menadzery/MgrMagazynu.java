/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menadzery;

import DTO.MagazynDTO;
import Tabele.MagazynD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darek Xperia
 */
public class MgrMagazynu {

    public MagazynD map(MagazynDTO dtoC) {
        MagazynD c = new MagazynD();
        MgrCzesci m = new MgrCzesci();
        c.setId(dtoC.getId());
        c.setCzesc(m.map(dtoC.getCzesc()));
        c.setRegal(dtoC.getRegal());
        c.setPolka(dtoC.getPolka());
        c.setIlosc(dtoC.getIlosc());
        return c;
    }

    public List<MagazynD> mapList(List<MagazynDTO> dtoCL) {
        List<MagazynD> tmp = new ArrayList<>();
        dtoCL.forEach((dtoCLe) -> {
            tmp.add(map(dtoCLe));
        });
        return tmp;
    }

}
