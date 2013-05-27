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
@Table(name = "position_point",
        uniqueConstraints=@UniqueConstraint(columnNames={"role_id", "rank"}))
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PositionPoint.findAll", query = "SELECT p FROM PositionPoint p"),
    @NamedQuery(name = "PositionPoint.findById", query = "SELECT p FROM PositionPoint p WHERE p.id = :id"),
    @NamedQuery(name = "PositionPoint.findByRank", query = "SELECT p FROM PositionPoint p WHERE p.rank = :rank")})
public class PositionPoint implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rank")
    private int rank;
    @ManyToMany(mappedBy = "positionPointCollection")
    private Set<QualLine> qualLineCollection;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Position roleId;

    public PositionPoint() {
    }

    public PositionPoint(Integer id) {
        this.id = id;
    }

    public PositionPoint(Integer id, String description, int rank) {
        this.id = id;
        this.description = description;
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @XmlTransient
    public Set<QualLine> getQualLineCollection() {
        return qualLineCollection;
    }

    public void setQualLineCollection(Set<QualLine> qualLineCollection) {
        this.qualLineCollection = qualLineCollection;
    }

    public Position getRoleId() {
        return roleId;
    }

    public void setRoleId(Position roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if(id != null){
            hash = id.hashCode();
        }else{
            hash =  System.identityHashCode(this); //used to avoid collision on id's with nulls
        }
        //hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PositionPoint)) {
            return false;
        }
        PositionPoint other = (PositionPoint) object;
        //UNIQUE Constraint
        if(this.roleId.getId() != other.roleId.getId() && this.rank != other.rank){
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        //can't have the same role AND rank id
        if (this.rank != other.getRank() && this.roleId != other.roleId){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.PositionPoint[ id=" + id + " ]";
    }
    
}
