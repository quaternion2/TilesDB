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
@Table(name = "position",
uniqueConstraints =
@UniqueConstraint(columnNames = {"resume_id", "company_id", "title", "start_date", "end_date", "currently_employed"}))
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
    @GeneratedValue(strategy = GenerationType.TABLE)
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
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Resume resumeId;
    //TODO: Need to review how companies are being digested by the system (or rather how they are not being digested!)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) //TODO: Review if this is safe
    private Company companyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    //@LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("rank ASC")
    private Set<PositionPoint> positionPointCollection;
    @JoinTable(name = "position_job_tags", joinColumns = {
        @JoinColumn(name = "position_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "job_tags_id", referencedColumnName = "id")})
    @ManyToMany
    private Set<JobTags> jobTagsSet;

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
    public Set<PositionPoint> getPositionPointCollection() {
        return positionPointCollection;
    }

    public void setPositionPointCollection(Set<PositionPoint> positionPointCollection) {
        this.positionPointCollection = positionPointCollection;
    }

    @XmlTransient
    public Set<JobTags> getJobTagsSet() {
        return jobTagsSet;
    }

    public void setJobTagsSet(Set<JobTags> jobTagsSet) {
        this.jobTagsSet = jobTagsSet;
    }

    @Override
    public int hashCode() {
        int hash;
        if (id != null) {
            hash = id.hashCode();
        } else {
            hash = System.identityHashCode(this); //used to avoid collision on id's with nulls
        }
        //hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        //check type
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        //check primary id
        if (this.id != null && other.id != null && this.id == other.id) {
            return true;
        }
        //check UNIQUE Constraint {"resume_id", "company_id", "title", "start_date", "end_date", "currently_employed"}
        if (other != null && this.resumeId != null && this.companyId != null
                && this.resumeId.getId() == other.resumeId.getId()
                && this.companyId.getId() == other.companyId.getId()
                && this.title.equals(other.title)
                && this.startDate.equals(other.startDate)
                && this.endDate.equals(other.endDate)
                && this.currentlyEmployed == other.currentlyEmployed) {
            return true;
        }

        //if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
        //    return false;
        //}
        return false;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.Position[ id=" + id + " ]";
    }
}
