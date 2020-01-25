/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.facade.entity;

import com.soft.facade.general.AbstractFacade;
import com.soft.jpa.pojo.Address;
import com.soft.jpa.pojo.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class PersonFacade extends AbstractFacade<Person> {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PersonFacade() {
        super(Person.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idperson", "name", "phoneNumber", "emailAddress", "idaddress.idaddress", "idaddress.street"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"idperson", "name"};
        return attrs;
    }

    @Override
    protected Person parseObject(Object[] o) {
        Person p = new Person((Integer) o[0]);
        p.setName((String) o[1]);
        p.setPhoneNumber((Long) o[2]);
        p.setEmailAddress((String) o[3]);
        p.setIdaddress(new Address((Integer) o[4]));
        p.getIdaddress().setStreet((String) o[5]);

        return p;
    }

    @Override
    public void create(Person obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Person obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idperson, StringBuilder err) {
        deleteGeneral(idperson, err);
    }

    public Person findByIdaddress(Object idaddress) {
        String hql = "SELECT p"
                + " FROM Person p"
                + " WHERE p.idaddress = " + idaddress;
        return objectFromHQL(hql);
    }
}
