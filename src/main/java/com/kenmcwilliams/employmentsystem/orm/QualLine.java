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
@Table(name = "qual_line")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QualLine.findAll", query = "SELECT q FROM QualLine q"),
    @NamedQuery(name = "QualLine.findById", query = "SELECT q FROM QualLine q WHERE q.id = :id"),
    @NamedQuery(name = "QualLine.findByOrdinal", query = "SELECT q FROM QualLine q WHERE q.ordinal = :ordinal"),
    @NamedQuery(name = "QualLine.findByMandatory", query = "SELECT q FROM QualLine q WHERE q.mandatory = :mandatory"),
    @NamedQuery(name = "QualLine.findByMonths", query = "SELECT q FROM QualLine q WHERE q.months = :months")})
public class QualLine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(name = "ordinal") //TODO: number should be refactored to ordinal
    private Integer ordinal;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Column(name = "mandatory", columnDefinition = "BIT", length = 1)
    @NotNull
    private Boolean mandatory;
    @Column(name = "months")
    private Integer months;
    @JoinTable(name = "qual_line_position_point", joinColumns = {
        @JoinColumn(name = "qual_line_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "position_point_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<PositionPoint> positionPointCollection;
    @JoinColumn(name = "qual_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Qual qualId;

    public QualLine() {
    }

    public QualLine(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    @XmlTransient
    public Collection<PositionPoint> getPositionPointCollection() {
        return positionPointCollection;
    }

    public void setPositionPointCollection(Collection<PositionPoint> positionPointCollection) {
        this.positionPointCollection = positionPointCollection;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QualLine)) {
            return false;
        }
        QualLine other = (QualLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.QualLine[ id=" + id + " ]";
    }
    
}
