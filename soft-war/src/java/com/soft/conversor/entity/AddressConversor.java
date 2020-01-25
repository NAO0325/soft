package com.soft.conversor.entity;

import com.soft.facade.entity.AddressFacade;
import com.soft.jpa.pojo.Address;
import com.soft.util.BeanUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author napoavi@gmail.com
 */
@FacesConverter(forClass = Address.class)
public class AddressConversor implements Converter {

    AddressFacade bean = BeanUtil.lookupFacadeBean(AddressFacade.class);

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        return bean.find(Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        Address o = (Address) object;
        if (o.getIdaddress() == null) {
            return null;
        }
        return o.getIdaddress().toString();
    }
}
