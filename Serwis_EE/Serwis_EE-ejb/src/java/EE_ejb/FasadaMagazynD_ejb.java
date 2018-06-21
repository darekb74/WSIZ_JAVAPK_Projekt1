/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EE_ejb;

import DTO.MagazynDTO;
import Menadzery.MgrMagazynu;
import T_EE_ejb.MagazynDFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Darek Xperia
 */
@Stateless(mappedName="ejb/FasadaMagazynD_ejb")
@Named
public class FasadaMagazynD_ejb implements FasadaMagazynD_ejbRemote {

    @EJB
    private MagazynDFacadeLocal bazaMagazynu;

    private MgrMagazynu mgr = new MgrMagazynu();
    
    @Override
    public MagazynDTO znajdzCzesc(String nazwa) {
        try {
            return bazaMagazynu.findByName(nazwa).getMagazynDTO();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<MagazynDTO> listaCzesci() {
        return this.MapToDTO(bazaMagazynu.findAll());
    }

    private List<MagazynDTO> MapToDTO(List<Tabele.MagazynD> lista) {
        List<MagazynDTO> result = new ArrayList<>();

        lista.forEach((e) -> {
            result.add(e.getMagazynDTO());
        });
        return result;
    }

    @Override
    public void aktualizujDane(MagazynDTO czesc) {
        bazaMagazynu.edit(mgr.map(czesc));
    }
}
