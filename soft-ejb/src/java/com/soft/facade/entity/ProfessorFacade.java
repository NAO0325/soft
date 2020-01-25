/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.facade.entity;

import com.soft.facade.general.AbstractFacade;
import com.soft.jpa.pojo.Professor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class ProfessorFacade extends AbstractFacade<Professor> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ProfessorFacade() {
        super(Professor.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idperson", "name"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"idperson", "name"};
        return attrs;
    }

    @Override
    protected Professor parseObject(Object[] o) {
        
        Professor p = new Professor();
        p.setIdperson((Integer) o[0]);
        p.setSalary((Double) o[1]);
        
        return p;
    }

    @Override
    public void create(Professor obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Professor obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idbarrio, StringBuilder err) {
        deleteGeneral(idbarrio, err);
    }
    
    public Professor findByIdperson(int idperson) {
        String hql = "SELECT s"
                + " FROM Professor s"
                + " WHERE s.idperson = " + idperson;
        return objectFromHQL(hql);
    }

}
