/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.jpa.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author desarrollo
 */
@Entity
@PrimaryKeyJoinColumn(name="idperson")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p")
    , @NamedQuery(name = "Professor.findBySalary", query = "SELECT p FROM Professor p WHERE p.salary = :salary")})
public class Professor extends Person implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "salary")
    private double salary;

    public Professor() {
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getIdperson() != null ? this.getIdperson().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.getIdperson() == null && other.getIdperson() != null) || (this.getIdperson() != null && !this.getIdperson().equals(other.getIdperson()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soft.jpa.pojo.Professor[ idperson=" + this.getIdperson() + " ]";
    }

}
