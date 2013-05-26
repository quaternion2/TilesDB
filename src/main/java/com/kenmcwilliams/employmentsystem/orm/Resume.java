/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "resume",
        uniqueConstraints=@UniqueConstraint(columnNames={"candidate_id", "name"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resume.findAll", query = "SELECT r FROM Resume r"),
    @NamedQuery(name = "Resume.findById", query = "SELECT r FROM Resume r WHERE r.id = :id"),
    @NamedQuery(name = "Resume.findByName", query = "SELECT r FROM Resume r WHERE r.name = :name"),
    @NamedQuery(name = "Resume.findByCreatedBy", query = "SELECT r FROM Resume r WHERE r.createdBy = :createdBy"),
    @NamedQuery(name = "Resume.findByCreatedDate", query = "SELECT r FROM Resume r WHERE r.createdDate = :createdDate"),
    @NamedQuery(name = "Resume.findByUpdatedBy", query = "SELECT r FROM Resume r WHERE r.updatedBy = :updatedBy"),
    @NamedQuery(name = "Resume.findByUpdatedDate", query = "SELECT r FROM Resume r WHERE r.updatedDate = :updatedDate")})
public class Resume implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resumeId")
    private Set<Position> positionCollection;
    @JoinColumn(name = "opportunity_id", referencedColumnName = "id")
    @ManyToOne
    private Opportunity opportunityId;
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Candidate candidateId;
    @JoinColumn(name = "qual_id", referencedColumnName = "id")
    @ManyToOne
    private Qual qualId;

    public Resume() {
    }

    public Resume(Integer id) {
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @XmlTransient
    public Set<Position> getPositionCollection() {
        return positionCollection;
    }

    public void setPositionCollection(Set<Position> positionCollection) {
        this.positionCollection = positionCollection;
    }

    public Opportunity getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Opportunity opportunityId) {
        this.opportunityId = opportunityId;
    }

    public Candidate getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Candidate candidateId) {
        this.candidateId = candidateId;
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
