/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "phone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Phone.findAll", query = "SELECT p FROM Phone p"),
    @NamedQuery(name = "Phone.findById", query = "SELECT p FROM Phone p WHERE p.id = :id"),
    @NamedQuery(name = "Phone.findByArea", query = "SELECT p FROM Phone p WHERE p.area = :area"),
    @NamedQuery(name = "Phone.findByExchange", query = "SELECT p FROM Phone p WHERE p.exchange = :exchange"),
    @NamedQuery(name = "Phone.findBySubscriber", query = "SELECT p FROM Phone p WHERE p.subscriber = :subscriber"),
    @NamedQuery(name = "Phone.findByExtension", query = "SELECT p FROM Phone p WHERE p.extension = :extension"),
    @NamedQuery(name = "Phone.findByPhonecol", query = "SELECT p FROM Phone p WHERE p.phonecol = :phonecol")})
public class Phone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 3)
    @Column(name = "area")
    private String area;
    @Size(max = 3)
    @Column(name = "exchange")
    private String exchange;
    @Size(max = 4)
    @Column(name = "subscriber")
    private String subscriber;
    @Size(max = 10)
    @Column(name = "extension")
    private String extension;
    @Size(max = 45)
    @Column(name = "phonecol")
    private String phonecol;

    public Phone() {
    }

    public Phone(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPhonecol() {
        return phonecol;
    }

    public void setPhonecol(String phonecol) {
        this.phonecol = phonecol;
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
        if (!(object instanceof Phone)) {
            return false;
        }
        Phone other = (Phone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.Phone[ id=" + id + " ]";
    }
    
}
