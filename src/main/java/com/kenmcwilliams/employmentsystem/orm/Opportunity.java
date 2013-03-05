/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
    @NamedQuery(name = "Opportunity.findByName", query = "SELECT o FROM Opportunity o WHERE o.name = :name"),
    @NamedQuery(name = "Opportunity.findByDescription", query = "SELECT o FROM Opportunity o WHERE o.description = :description"),
    @NamedQuery(name = "Opportunity.findByClientId", query = "SELECT o FROM Opportunity o WHERE o.clientId = :clientId"),
    @NamedQuery(name = "Opportunity.findBySubmissionMethod", query = "SELECT o FROM Opportunity o WHERE o.submissionMethod = :submissionMethod"),
    @NamedQuery(name = "Opportunity.findByClosingDate", query = "SELECT o FROM Opportunity o WHERE o.closingDate = :closingDate")})
public class Opportunity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "client_id")
    private String clientId;
    @Size(max = 45)
    @Column(name = "submission_method")
    private String submissionMethod;
    @Size(max = 45)
    @Column(name = "closing_date")
    private String closingDate;

    public Opportunity() {
    }

    public Opportunity(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSubmissionMethod() {
        return submissionMethod;
    }

    public void setSubmissionMethod(String submissionMethod) {
        this.submissionMethod = submissionMethod;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
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
        return "com.kenmcwilliams.employmentsystem.orm.Opportunity[ id=" + id + " ]";
    }
    
}
