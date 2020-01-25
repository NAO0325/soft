/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.control.entity;

import com.soft.control.general.AbstractControl;
import com.soft.facade.entity.StudentFacade;
import com.soft.facade.general.AbstractFacade;
import com.soft.jpa.pojo.Student;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "studentControl")
@RequestScoped
public class StudentControl extends AbstractControl<Student> {

    @EJB
    StudentFacade facade;

    public StudentControl() {
        super(Student.class);
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

    protected StudentFacade getStudentFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Student obj) {
        return obj.getName();
    }

}
