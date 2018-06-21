/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EE_ejb;

import DTO.MagazynDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Darek Xperia
 */
@Remote
public interface FasadaMagazynD_ejbRemote {

    public MagazynDTO znajdzCzesc(String nazwa);

    public List<MagazynDTO> listaCzesci();

    public void aktualizujDane(MagazynDTO czesc);

}
