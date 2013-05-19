/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "qual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qual.findAll", query = "SELECT q FROM Qual q"),
    @NamedQuery(name = "Qual.findById", query = "SELECT q FROM Qual q WHERE q.id = :id"),
    @NamedQuery(name = "Qual.findByName", query = "SELECT q FROM Qual q WHERE q.name = :name"),
    @NamedQuery(name = "Qual.findByRole", query = "SELECT q FROM Qual q WHERE q.role = :role"),
    @NamedQuery(name = "Qual.findByDescription", query = "SELECT q FROM Qual q WHERE q.description = :description")})
public class Qual implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "role")
    private String role;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "qualId")
    private Collection<Resume> resumeCollection;
    @OneToMany(mappedBy = "qualId")
    private Collection<Opportunity> opportunityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qualId")
    private Collection<QualLine> qualLineCollection;

    public Qual() {
    }

    public Qual(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Resume> getResumeCollection() {
        return resumeCollection;
    }

    public void setResumeCollection(Collection<Resume> resumeCollection) {
        this.resumeCollection = resumeCollection;
    }

    @XmlTransient
    public Collection<Opportunity> getOpportunityCollection() {
        return opportunityCollection;
    }

    public void setOpportunityCollection(Collection<Opportunity> opportunityCollection) {
        this.opportunityCollection = opportunityCollection;
    }

    @XmlTransient
    public Collection<QualLine> getQualLineCollection() {
        return qualLineCollection;
    }

    public void setQualLineCollection(Collection<QualLine> qualLineCollection) {
        this.qualLineCollection = qualLineCollection;
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
        if (!(object instanceof Qual)) {
            return false;
        }
        Qual other = (Qual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.Qual[ id=" + id + " ]";
    }
    
}
