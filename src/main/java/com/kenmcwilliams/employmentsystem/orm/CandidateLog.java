/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "candidate_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CandidateLog.findAll", query = "SELECT c FROM CandidateLog c"),
    @NamedQuery(name = "CandidateLog.findById", query = "SELECT c FROM CandidateLog c WHERE c.id = :id"),
    @NamedQuery(name = "CandidateLog.findByStamp", query = "SELECT c FROM CandidateLog c WHERE c.stamp = :stamp"),
    @NamedQuery(name = "CandidateLog.findByCandiateId", query = "SELECT c FROM CandidateLog c LEFT JOIN FETCH c.candidateId cid WHERE cid.id = :candidateId ORDER BY c.stamp DESC")
})
public class CandidateLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stamp;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "note")
    private String note;
    @JoinColumn(name = "recruiter_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Recruiter recruiterId;
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Candidate candidateId;

    public CandidateLog() {
    }

    public CandidateLog(Integer id) {
        this.id = id;
    }

    public CandidateLog(Integer id, Date stamp) {
        this.id = id;
        this.stamp = stamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Recruiter getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Recruiter recruiterId) {
        this.recruiterId = recruiterId;
    }

    public Candidate getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Candidate candidateId) {
        this.candidateId = candidateId;
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
        if (!(object instanceof CandidateLog)) {
            return false;
        }
        CandidateLog other = (CandidateLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.CandidateLog[ id=" + id + " ]";
    }
}
