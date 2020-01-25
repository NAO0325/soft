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
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findByStudentNumber", query = "SELECT s FROM Student s WHERE s.studentNumber = :studentNumber")
    , @NamedQuery(name = "Student.findByAverageMark", query = "SELECT s FROM Student s WHERE s.averageMark = :averageMark")})
public class Student extends Person implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "student_number")
    private long studentNumber;
    @Column(name = "average_mark")
    private Integer averageMark;

    public Student() {
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Integer averageMark) {
        this.averageMark = averageMark;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.getIdperson() == null && other.getIdperson() != null) || (this.getIdperson() != null && !this.getIdperson().equals(other.getIdperson()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.soft.jpa.pojo.Student[ idperson=" + this.getIdperson() + " ]";
    }

}
