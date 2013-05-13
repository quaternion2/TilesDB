/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "qual_term")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QualTerm.findAll", query = "SELECT q FROM QualTerm q"),
    @NamedQuery(name = "QualTerm.findById", query = "SELECT q FROM QualTerm q WHERE q.id = :id"),
    @NamedQuery(name = "QualTerm.findByPeriod", query = "SELECT q FROM QualTerm q WHERE q.period = :period"),
    @NamedQuery(name = "QualTerm.findByQualId", query = "SELECT q FROM QualTerm q WHERE q.qualId = :qualId"),
    @NamedQuery(name = "QualTerm.findByDuration", query = "SELECT q FROM QualTerm q WHERE q.duration = :duration")})
public class QualTerm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "period")
    private int period;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qual_id")
    private int qualId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration")
    private int duration;

    public QualTerm() {
    }

    public QualTerm(Integer id) {
        this.id = id;
    }

    public QualTerm(Integer id, int period, int qualId, int duration) {
        this.id = id;
        this.period = period;
        this.qualId = qualId;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getQualId() {
        return qualId;
    }

    public void setQualId(int qualId) {
        this.qualId = qualId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
        if (!(object instanceof QualTerm)) {
            return false;
        }
        QualTerm other = (QualTerm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.QualTerm[ id=" + id + " ]";
    }
    
}
