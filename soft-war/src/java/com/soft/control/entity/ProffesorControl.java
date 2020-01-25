/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.control.entity;

import com.soft.control.general.AbstractControl;
import com.soft.facade.entity.ProfessorFacade;
import com.soft.facade.general.AbstractFacade;
import com.soft.jpa.pojo.Professor;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "professorControl")
@RequestScoped
public class ProffesorControl extends AbstractControl<Professor> {

    @EJB
    ProfessorFacade facade;

    public ProffesorControl() {
        super(Professor.class);
        attrOrd = "name";
        ascDesc = "ASC";
    }

    @Override
    public void recuperaById() {
        int idp = facesUtil.getFacesParamInteger("idperson_");
        setObj(facade.findByIdperson(idp));
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

    protected ProfessorFacade getProfessorFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Professor obj) {
        return obj.getName();
    }

}
