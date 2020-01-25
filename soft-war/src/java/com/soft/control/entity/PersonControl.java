/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.control.entity;

import com.soft.control.general.AbstractControl;
import com.soft.facade.entity.PersonFacade;
import com.soft.facade.general.AbstractFacade;
import com.soft.jpa.pojo.Person;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "personControl")
@RequestScoped
public class PersonControl extends AbstractControl<Person> {

    @EJB
    PersonFacade facade;

    public PersonControl() {
        super(Person.class);
        attrOrd = "name";
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
        facade.delete(facesUtil.getFacesParamLong("idperson_"), err);
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

    protected PersonFacade getPersonFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Person obj) {
        return obj.getName();
    }

}
