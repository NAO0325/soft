/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.facade.entity;

import com.soft.facade.general.AbstractFacade;
import com.soft.jpa.pojo.Student;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class StudentFacade extends AbstractFacade<Student> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public StudentFacade() {
        super(Student.class);
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
    protected Student parseObject(Object[] o) {
        
        Student e = new Student();
        e.setIdperson((Integer) o[0]);
        e.setStudentNumber((Long) o[1]);
        e.setAverageMark((Integer) o[0]);
        
        return e;
    }

    @Override
    public void create(Student obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Student obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idbarrio, StringBuilder err) {
        deleteGeneral(idbarrio, err);
    }
    
    public Student findByIdperson(int idperson) {
        String hql = "SELECT s"
                + " FROM Student s"
                + " WHERE s.idperson = " + idperson;
        return objectFromHQL(hql);
    }

}
