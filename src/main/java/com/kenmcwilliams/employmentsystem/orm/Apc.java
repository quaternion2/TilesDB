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
@Table(name = "apc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apc.findAll", query = "SELECT a FROM Apc a"),
    @NamedQuery(name = "Apc.findByGuid", query = "SELECT a FROM Apc a WHERE a.guid = :guid"),
    @NamedQuery(name = "Apc.findByStatus", query = "SELECT a FROM Apc a WHERE a.status = :status"),
    @NamedQuery(name = "Apc.findByTitle", query = "SELECT a FROM Apc a WHERE a.title = :title"),
    @NamedQuery(name = "Apc.findByPageRankingNumber", query = "SELECT a FROM Apc a WHERE a.pageRankingNumber = :pageRankingNumber"),
    @NamedQuery(name = "Apc.findByClosing", query = "SELECT a FROM Apc a WHERE a.closing = :closing"),
    @NamedQuery(name = "Apc.findByPosting", query = "SELECT a FROM Apc a WHERE a.posting = :posting")})
public class Apc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "guid")
    private String guid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pageRankingNumber")
    private int pageRankingNumber;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 0, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "closing")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closing;
    @Basic(optional = false)
    @NotNull
    @Column(name = "posting")
    @Temporal(TemporalType.TIMESTAMP)
    private Date posting;

    public Apc() {
    }

    public Apc(String guid) {
        this.guid = guid;
    }

    public Apc(String guid, String status, String title, int pageRankingNumber, String description, Date closing, Date posting) {
        this.guid = guid;
        this.status = status;
        this.title = title;
        this.pageRankingNumber = pageRankingNumber;
        this.description = description;
        this.closing = closing;
        this.posting = posting;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageRankingNumber() {
        return pageRankingNumber;
    }

    public void setPageRankingNumber(int pageRankingNumber) {
        this.pageRankingNumber = pageRankingNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getClosing() {
        return closing;
    }

    public void setClosing(Date closing) {
        this.closing = closing;
    }

    public Date getPosting() {
        return posting;
    }

    public void setPosting(Date posting) {
        this.posting = posting;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guid != null ? guid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apc)) {
            return false;
        }
        Apc other = (Apc) object;
        if ((this.guid == null && other.guid != null) || (this.guid != null && !this.guid.equals(other.guid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.Apc[ guid=" + guid + " ]";
    }
    
}
