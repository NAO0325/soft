/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.facade.entity;

import com.soft.facade.general.AbstractFacade;
import com.soft.jpa.pojo.Address;
import com.soft.jpa.pojo.Person;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class AddressFacade extends AbstractFacade<Address> {

    @EJB
    PersonFacade personFacade;
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public AddressFacade() {
        super(Address.class);
    }

    @Override
    protected String[] attrsQueryLight() {
        String[] attrs = {"idaddress", "street", "city"};
        return attrs;
    }

    @Override
    protected String[] attrFullTextCriteria() {
        String[] attrs = {"street", "city"};
        return attrs;
    }

    @Override
    protected Address parseObject(Object[] o) {
        Address a = new Address((Integer) o[0]);
        a.setStreet((String) o[1]);
        a.setCity((String) o[2]);
        a.setPostalCode((String) o[3]);
        a.setCountry((String) o[4]);

        return a;
    }

    @Override
    public void create(Address obj, StringBuilder err) {
        createGeneral(obj, err);
    }

    @Override
    public void edit(Address obj, StringBuilder err) {
        editGeneral(obj, err);
    }

    @Override
    public void delete(Object idaddress, StringBuilder err) {

        Person p = personFacade.findByIdaddress(idaddress);

        if (p != null) {
            err.append("La address esta asociada a un registro de person!");
            return;
        }

        deleteGeneral(idaddress, err);
    }

}
