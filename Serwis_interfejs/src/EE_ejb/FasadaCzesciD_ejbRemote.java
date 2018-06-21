/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EE_ejb;

import DTO.CzesciDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Darek Xperia
 */
@Remote
public interface FasadaCzesciD_ejbRemote {

    public CzesciDTO znajdzCzesc(String nazwa);
    
    public CzesciDTO znajdzCzesc(Long id);

    public List<CzesciDTO> listaCzesci();

    public void aktualizujDane(CzesciDTO czesc);

    public Long znajdzNastepneID();
    
    public void dodajCzesc(CzesciDTO czescDTO);
}
