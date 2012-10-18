/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p"),
    @NamedQuery(name = "Position.findById", query = "SELECT p FROM Position p WHERE p.id = :id"),
    @NamedQuery(name = "Position.findByTitle", query = "SELECT p FROM Position p WHERE p.title = :title"),
    @NamedQuery(name = "Position.findByStartDate", query = "SELECT p FROM Position p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Position.findByEndDate", query = "SELECT p FROM Position p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Position.findByCurrentlyEmployed", query = "SELECT p FROM Position p WHERE p.currentlyEmployed = :currentlyEmployed")})
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "currently_employed")
    private Boolean currentlyEmployed;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Company companyId;
    @JoinColumn(name = "resume_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Resume resumeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private Collection<PositionPoint> positionPointCollection;

    public Position() {
    }

    public Position(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getCurrentlyEmployed() {
        return currentlyEmployed;
    }

    public void setCurrentlyEmployed(Boolean currentlyEmployed) {
        this.currentlyEmployed = currentlyEmployed;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public Resume getResumeId() {
        return resumeId;
    }

    public void setResumeId(Resume resumeId) {
        this.resumeId = resumeId;
    }

    @XmlTransient
    public Collection<PositionPoint> getPositionPointCollection() {
        return positionPointCollection;
    }

    public void setPositionPointCollection(Collection<PositionPoint> positionPointCollection) {
        this.positionPointCollection = positionPointCollection;
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
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.Position[ id=" + id + " ]";
    }
    
}
