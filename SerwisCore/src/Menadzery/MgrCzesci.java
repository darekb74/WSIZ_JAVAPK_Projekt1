/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menadzery;

import DTO.CzesciDTO;
import Tabele.CzesciD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darek Xperia
 */
public class MgrCzesci {

    public CzesciD map(CzesciDTO dtoC) {
        CzesciD c = new CzesciD();
        c.setId(dtoC.getId());
        c.setNazwa(dtoC.getNazwa());
        c.setProducent(dtoC.getProducent());
        c.setModel(dtoC.getModel());
        c.setJednostka(dtoC.getJednostka());
        c.setCena_jednostkowa(dtoC.getCena_jednostkowa());
        return c;
    }

    public List<CzesciD> mapList(List<CzesciDTO> dtoCL) {
        List<CzesciD> tmp = new ArrayList<>();
        dtoCL.forEach((dtoCLe) -> {
            tmp.add(map(dtoCLe));
        });
        return tmp;
    }

}
