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
@Table(name = "resume")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resume.findAll", query = "SELECT r FROM Resume r"),
    @NamedQuery(name = "Resume.findById", query = "SELECT r FROM Resume r WHERE r.id = :id"),
    @NamedQuery(name = "Resume.findByCompanyName", query = "SELECT r FROM Resume r WHERE r.companyName = :companyName")})
public class Resume implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "company_name")
    private String companyName;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resumeId")
    private Collection<Position> positionCollection;

    public Resume() {
    }

    public Resume(Integer id) {
        this.id = id;
    }

    public Resume(Integer id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Position> getPositionCollection() {
        return positionCollection;
    }

    public void setPositionCollection(Collection<Position> positionCollection) {
        this.positionCollection = positionCollection;
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
        if (!(object instanceof Resume)) {
            return false;
        }
        Resume other = (Resume) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.Resume[ id=" + id + " ]";
    }
    
}
