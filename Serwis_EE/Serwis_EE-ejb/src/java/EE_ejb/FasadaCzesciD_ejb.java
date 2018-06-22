/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EE_ejb;

import DTO.CzesciDTO;
import Menadzery.MgrCzesci;
import T_EE_ejb.CzesciDFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Darek Xperia
 */
@Stateless(mappedName = "ejb/FasadaCzesciD_ejb")
@Named
public class FasadaCzesciD_ejb implements FasadaCzesciD_ejbRemote {

    @EJB
    private CzesciDFacadeLocal bazaCzesci;

    private MgrCzesci mgr = new MgrCzesci();

    @Override
    public CzesciDTO znajdzCzesc(String nazwa) {
        try {
            return bazaCzesci.findByName(nazwa).getCzesciDTO();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CzesciDTO znajdzCzesc(Long id) {
        try {
            return bazaCzesci.find(id).getCzesciDTO();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CzesciDTO> listaCzesci() {
        return this.MapToDTO(bazaCzesci.findAll());
    }

    private List<CzesciDTO> MapToDTO(List<Tabele.CzesciD> lista) {
        List<CzesciDTO> result = new ArrayList<>();

        lista.forEach((e) -> {
            result.add(e.getCzesciDTO());
        });
        return result;
    }

    @Override
    public void aktualizujDane(CzesciDTO czesc) {
        bazaCzesci.edit(mgr.map(czesc));
    }

    @Override
    public void usunPozycje(CzesciDTO czesc) {
        bazaCzesci.remove(mgr.map(czesc));
    }

    @Override
    public Long znajdzNastepneID() {
        return bazaCzesci.findNextId();
    }

    @Override
    public void dodajCzesc(CzesciDTO czescDTO) {
        bazaCzesci.create(mgr.map(czescDTO));
    }
}
