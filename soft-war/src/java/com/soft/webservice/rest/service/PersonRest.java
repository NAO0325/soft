/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.webservice.rest.service;

import com.soft.facade.entity.PersonFacade;
import com.soft.jpa.pojo.Address;
import com.soft.jpa.pojo.Person;
import com.soft.util.BeanUtil;
import com.soft.util.Numero;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author desarrollo
 */
@Path("soft.personRest")
public class PersonRest {

    private PersonFacade personFacade = BeanUtil.lookupFacadeBean(PersonFacade.class);

    @Context
    private UriInfo context;

    @GET
    @Path("hola")
    @Produces(MediaType.TEXT_PLAIN)
    public String hola() {
        return "Hello!";
    }

    @GET
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@QueryParam("name") String name, @QueryParam("phoneNumber") Long phoneNumber, @QueryParam("emailAddress") String emailAddress, @QueryParam("idaddress") Integer idaddress) {

        String result = "";
        StringBuilder err = new StringBuilder();

        result = validaCampos(false, null, name, emailAddress, phoneNumber, idaddress);

        if (!result.isEmpty()) {
            return result;
        }

        Person p = new Person();
        p.setName(name);
        p.setPhoneNumber(phoneNumber);
        p.setEmailAddress(emailAddress);
        p.setIdaddress(new Address(idaddress));

        personFacade.create(p, err);

        if (err.toString().trim().isEmpty()) {
            return "{\"SUCCESS\":\"Person Created!\"}";
        } else {
            return "{\"ALERT\":\"" + err.toString() + "\"}";
        }
    }

    @GET
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    public String edit(@QueryParam("idperson") Integer idperson, @QueryParam("name") String name, @QueryParam("phoneNumber") Long phoneNumber, @QueryParam("emailAddress") String emailAddress, @QueryParam("idaddress") Integer idaddress) {

        String result = "";
        StringBuilder err = new StringBuilder();

        if (idperson == null) {
            return "{\"ALERT\":\"Falta  idperson!\"}";
        }

        if (!result.isEmpty()) {
            return result;
        }

        Person p = personFacade.find(idperson);

        if (p == null) {
            return "{\"WARNING\":\"Person no registred!\"}";
        }

        if (name != null && !name.isEmpty()) {
            p.setName(name);
        }
        if (phoneNumber != null && Numero.isNumeric(phoneNumber.toString())) {
            p.setPhoneNumber(phoneNumber);
        }
        if (emailAddress != null && !emailAddress.isEmpty()) {
            p.setEmailAddress(emailAddress);
        }
        if (idaddress != null) {
            p.setIdaddress(new Address(idaddress));
        }

        personFacade.edit(p, err);

        if (err.toString().trim().isEmpty()) {
            return "{\"SUCCESS\":\"Person Edited!\"}";
        } else {
            return "{\"ALERT\":\"" + err.toString() + "\"}";
        }
    }

    @GET
    @Path("/remove")
    @Produces(MediaType.APPLICATION_JSON)
    public String remove(@QueryParam("idperson") Integer idperson) {

        StringBuilder err = new StringBuilder();

        if (idperson == null) {
            return "{\"ALERT\":\"Falta  idperson!\"}";
        }

        Person p = personFacade.find(idperson);

        if (p == null) {
            return "{\"WARNING\":\"Person no registred!\"}";
        }

        personFacade.delete(p.getIdperson(), err);

        if (err.toString().trim().isEmpty()) {
            return "{\"SUCCESS\":\"Person Deleted!\"}";
        } else {
            return "{\"ALERT\":\"" + err.toString() + "\"}";
        }
    }

    @GET
    @Path("/find")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Person find(@QueryParam("idperson") Integer idperson) {
        return personFacade.find(idperson);
    }

    @GET
    @Path("/findAll")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findAll() {
        return personFacade.listAllLight("idperson", "ASC");
    }

    private String validaCampos(boolean isEdit, Integer idperson, String name, String emailAddress, Long phoneNumber, Integer idaddress) {

        String res = "";

        if (isEdit) {
            if (idperson == null) {
                res = "{\"ALERT\":\"Falta  idperson!\"}";
            }
        }

        if (name == null || name.trim().isEmpty()) {
            res = "{\"ALERT\":\"Falta campo name!\"}";
        }

        if (emailAddress == null || emailAddress.trim().isEmpty()) {
            res = "{\"ALERT\":\"Falta campo emailAddress!\"}";
        }

        if (phoneNumber != null && !Numero.isNumeric(phoneNumber.toString())) {
            res = "{\"ALERT\":\"Campo phoneNumber no numerico!\"}";
        }

        if (idaddress == null) {
            res = "{\"ALERT\":\"Falta campo idaddress!\"}";
        } else if (!Numero.isNumeric(idaddress.toString())) {
            res = "{\"ALERT\":\"Campo idaddress no numerico!\"}";
        }

        return res;
    }
}
