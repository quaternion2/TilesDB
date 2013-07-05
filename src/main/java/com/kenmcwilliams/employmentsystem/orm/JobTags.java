/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import java.util.Set;
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
@Table(name = "job_tags")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobTags.findAll", query = "SELECT j FROM JobTags j"),
    @NamedQuery(name = "JobTags.findById", query = "SELECT j FROM JobTags j WHERE j.id = :id"),
    @NamedQuery(name = "JobTags.findByName", query = "SELECT j FROM JobTags j WHERE j.name = :name")})
public class JobTags implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @ManyToMany(mappedBy = "jobTagsSet")
    private Set<Position> positionSet;
    @OneToMany(mappedBy = "synonym")
    private Set<JobTags> jobTagsSet;
    @JoinColumn(name = "synonym", referencedColumnName = "id")
    @ManyToOne
    private JobTags synonym;

    public JobTags() {
    }

    public JobTags(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return name;
    }

    public void setShortName(String shortName) {
        this.name = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Set<Position> getPositionSet() {
        return positionSet;
    }

    public void setPositionSet(Set<Position> positionSet) {
        this.positionSet = positionSet;
    }

    @XmlTransient
    public Set<JobTags> getJobTagsSet() {
        return jobTagsSet;
    }

    public void setJobTagsSet(Set<JobTags> jobTagsSet) {
        this.jobTagsSet = jobTagsSet;
    }

    public JobTags getSynonym() {
        return synonym;
    }

    public void setSynonym(JobTags synonym) {
        this.synonym = synonym;
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
        if (!(object instanceof JobTags)) {
            return false;
        }
        JobTags other = (JobTags) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.JobTags[ id=" + id + " ]";
    }
    
}
