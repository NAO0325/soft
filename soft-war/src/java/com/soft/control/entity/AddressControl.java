/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.control.entity;

import com.soft.control.general.AbstractControl;
import com.soft.facade.entity.AddressFacade;
import com.soft.facade.general.AbstractFacade;
import com.soft.jpa.pojo.Address;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "addressControl")
@RequestScoped
public class AddressControl extends AbstractControl<Address> {

    @EJB
    AddressFacade facade;

    public AddressControl() {
        super(Address.class);
        attrOrd = "street";
        ascDesc = "ASC";
    }

    public void nuevo() {
        createDefault();
    }

    public void editar() {
        editDefault();
    }

    public void eliminar() {
        StringBuilder err = new StringBuilder();
        facade.delete(facesUtil.getFacesParamLong("idaddress_"), err);
        if (err.toString().isEmpty()) {
            successful = true;
            facesUtil.msgOk("", "Registro eliminado !");
        } else {
            successful = false;
            facesUtil.msgError("ALERTA: ", err.toString());
        }
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    protected AddressFacade getAddressFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Address obj) {
        return obj.getStreet() + " - " + obj.getCity();
    }

}
