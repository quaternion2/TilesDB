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
@Table(name = "opportunity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opportunity.findAll", query = "SELECT o FROM Opportunity o"),
    @NamedQuery(name = "Opportunity.findById", query = "SELECT o FROM Opportunity o WHERE o.id = :id"),
    @NamedQuery(name = "Opportunity.findByTitle", query = "SELECT o FROM Opportunity o WHERE o.title = :title"),
    @NamedQuery(name = "Opportunity.findBySubmissionMethod", query = "SELECT o FROM Opportunity o WHERE o.submissionMethod = :submissionMethod"),
    @NamedQuery(name = "Opportunity.findByClosingTime", query = "SELECT o FROM Opportunity o WHERE o.closingTime = :closingTime"),
    @NamedQuery(name = "Opportunity.findByRequestTime", query = "SELECT o FROM Opportunity o WHERE o.requestTime = :requestTime"),
    @NamedQuery(name = "Opportunity.findByPriority", query = "SELECT o FROM Opportunity o WHERE o.priority = :priority")})
public class Opportunity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Size(max = 255)
    @Column(name = "submission_method")
    private String submissionMethod;
    @Column(name = "closing_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closingTime;
    @Column(name = "request_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestTime;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", columnDefinition="TEXT")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "priority")
    private int priority;
    @JoinTable(name = "recruiter_opportunity", joinColumns = {
        @JoinColumn(name = "opportunity_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "recruiter_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Recruiter> recruiterCollection;
    @JoinColumn(name = "qual_id", referencedColumnName = "id")
    @ManyToOne
    private Qual qualId;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;

    public Opportunity() {
    }

    public Opportunity(Integer id) {
        this.id = id;
    }

    public Opportunity(Integer id, String title, int priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
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

    public String getSubmissionMethod() {
        return submissionMethod;
    }

    public void setSubmissionMethod(String submissionMethod) {
        this.submissionMethod = submissionMethod;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @XmlTransient
    public Collection<Recruiter> getRecruiterCollection() {
        return recruiterCollection;
    }

    public void setRecruiterCollection(Collection<Recruiter> recruiterCollection) {
        this.recruiterCollection = recruiterCollection;
    }

    public Qual getQualId() {
        return qualId;
    }

    public void setQualId(Qual qualId) {
        this.qualId = qualId;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
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
        if (!(object instanceof Opportunity)) {
            return false;
        }
        Opportunity other = (Opportunity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwillaims.employmentsystemdb.Opportunity[ id=" + id + " ]";
    }
    
}
