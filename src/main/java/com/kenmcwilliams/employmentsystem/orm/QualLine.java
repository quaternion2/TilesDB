/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "qual_line", catalog = "emp_sys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QualLine.mandatory.count", query = "SELECT count(q) FROM QualLine q WHERE q.mandatory = true"),
    @NamedQuery(name = "QualLine.desirable.count", query = "SELECT count(q) FROM QualLine q WHERE q.mandatory = false"),
    @NamedQuery(name = "QualLine.findAll", query = "SELECT q FROM QualLine q"),
    @NamedQuery(name = "QualLine.findById", query = "SELECT q FROM QualLine q WHERE q.id = :id"),
    @NamedQuery(name = "QualLine.findByNumber", query = "SELECT q FROM QualLine q WHERE q.number = :number"),
    @NamedQuery(name = "QualLine.findByMandatory", query = "SELECT q FROM QualLine q WHERE q.mandatory = :mandatory"),
    @NamedQuery(name = "QualLine.findByMonths", query = "SELECT q FROM QualLine q WHERE q.months = :months")})
public class QualLine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number")
    private Integer number;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Column(name = "mandatory")
    private Boolean mandatory;
    @Column(name = "months")
    private Integer months;
    @JoinColumn(name = "qual_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Qual qualId;

    public QualLine() {
    }

    public QualLine(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Qual getQualId() {
        return qualId;
    }

    public void setQualId(Qual qualId) {
        this.qualId = qualId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QualLine)) {
            return false;
        }
        QualLine other = (QualLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.QualLine[ id=" + id + " ]";
    }
    
}
