/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konwertery;

import DTO.CzesciDTO;
import EE_ejb.FasadaCzesciD_ejbRemote;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;
import warstwa_internetowa.menadzer_magazynu;

/**
 *
 * @author Darek Xperia
 */
@FacesConverter("KonwerterCzesciDTO")
public class KonwerterCzesciDTO implements Converter {

    @EJB(mappedName = "ejb/FasadaCzesciD_ejb")
    FasadaCzesciD_ejbRemote fasadaCzesci;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        menadzer_magazynu m = context.getApplication().evaluateExpressionGet(context, "#{menadzer_magazynu}", menadzer_magazynu.class);
        try {
            CzesciDTO cDTO = m.getFasadaCzesci().znajdzCzesc(Long.valueOf(value));
            m.setCzesc(cDTO);
            if (cDTO==null) throw new NumberFormatException();
            context.addMessage(component.getClientId(), new FacesMessage(cDTO.getNazwa()));
            return cDTO;
        } catch (NumberFormatException | NullPointerException e) {
            throw new ConverterException(new FacesMessage(value + " nie jest prawidłowym ID części."), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof CzesciDTO) {
            return String.valueOf(((CzesciDTO) value).getId());
        } else {
            throw new ConverterException(new FacesMessage(value + " nie jest prawidłowym obiektem części."));
        }
    }
}
