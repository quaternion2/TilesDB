/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @NamedQuery(name = "Position.findByEndDate", query = "SELECT p FROM Position p WHERE p.endDate = :endDate")})
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Lob
    @Column(name = "currently_employed", columnDefinition = "binary", length = 1)
    private byte[] currentlyEmployed;
    @JoinColumn(name = "resume_id", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Resume resumeId;
    //TODO: Need to review how companies are being digested by the system (or rather how they are not being digested!)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = CascadeType.ALL) //TODO: Review if this is safe
    private Company companyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PositionPoint> positionPointCollection;

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

    public byte[] getCurrentlyEmployed() {
        return currentlyEmployed;
    }

    public void setCurrentlyEmployed(byte[] currentlyEmployed) {
        this.currentlyEmployed = currentlyEmployed;
    }

    public Resume getResumeId() {
        return resumeId;
    }

    public void setResumeId(Resume resumeId) {
        this.resumeId = resumeId;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    @XmlTransient
    public Collection<PositionPoint> getPositionPointCollection() {
        return positionPointCollection;
    }

    public void setPositionPointCollection(Set<PositionPoint> positionPointCollection) {
        this.positionPointCollection = positionPointCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : super.hashCode());//null id values must be able to persist in set, until persisted and assigned a value
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if (this.id == null){
            return false;//all ids in DB exist, this is in a temporary state so keep it
        }
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
