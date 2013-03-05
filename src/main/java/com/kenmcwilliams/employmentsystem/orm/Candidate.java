/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.orm;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ken
 */
@Entity
@Table(name = "candidate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Candidate.findAll", query = "SELECT c FROM Candidate c"),
    @NamedQuery(name = "Candidate.findById", query = "SELECT c FROM Candidate c WHERE c.id = :id"),
    @NamedQuery(name = "Candidate.findByFname", query = "SELECT c FROM Candidate c WHERE c.fname = :fname"),
    @NamedQuery(name = "Candidate.findByMname", query = "SELECT c FROM Candidate c WHERE c.mname = :mname"),
    @NamedQuery(name = "Candidate.findByLname", query = "SELECT c FROM Candidate c WHERE c.lname = :lname"),
    @NamedQuery(name = "Candidate.findByHomePhone", query = "SELECT c FROM Candidate c WHERE c.homePhone = :homePhone"),
    @NamedQuery(name = "Candidate.findByCellPhone", query = "SELECT c FROM Candidate c WHERE c.cellPhone = :cellPhone"),
    @NamedQuery(name = "Candidate.findByOtherPhone", query = "SELECT c FROM Candidate c WHERE c.otherPhone = :otherPhone"),
    @NamedQuery(name = "Candidate.findByEmail", query = "SELECT c FROM Candidate c WHERE c.email = :email"),
    @NamedQuery(name = "Candidate.findByAltEmail", query = "SELECT c FROM Candidate c WHERE c.altEmail = :altEmail"),
    @NamedQuery(name = "Candidate.findBySkype", query = "SELECT c FROM Candidate c WHERE c.skype = :skype"),
    @NamedQuery(name = "Candidate.findByStreet", query = "SELECT c FROM Candidate c WHERE c.street = :street"),
    @NamedQuery(name = "Candidate.findByCity", query = "SELECT c FROM Candidate c WHERE c.city = :city"),
    @NamedQuery(name = "Candidate.findByState", query = "SELECT c FROM Candidate c WHERE c.state = :state"),
    @NamedQuery(name = "Candidate.findByPoCode", query = "SELECT c FROM Candidate c WHERE c.poCode = :poCode"),
    @NamedQuery(name = "Candidate.findByDesiredRateHour", query = "SELECT c FROM Candidate c WHERE c.desiredRateHour = :desiredRateHour")})
public class Candidate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "fname")
    private String fname;
    @Size(max = 45)
    @Column(name = "mname")
    private String mname;
    @Size(max = 45)
    @Column(name = "lname")
    private String lname;
    @Size(max = 45)
    @Column(name = "home_phone")
    private String homePhone;
    @Size(max = 45)
    @Column(name = "cell_phone")
    private String cellPhone;
    @Size(max = 45)
    @Column(name = "other_phone")
    private String otherPhone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "alt_email")
    private String altEmail;
    @Size(max = 45)
    @Column(name = "skype")
    private String skype;
    @Size(max = 45)
    @Column(name = "street")
    private String street;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "state")
    private String state;
    @Size(max = 45)
    @Column(name = "po_code")
    private String poCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desired_rate_hour")
    private Float desiredRateHour;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidateId")
    private Collection<CandidateLog> candidateLogCollection;

    public Candidate() {
    }

    public Candidate(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPoCode() {
        return poCode;
    }

    public void setPoCode(String poCode) {
        this.poCode = poCode;
    }

    public Float getDesiredRateHour() {
        return desiredRateHour;
    }

    public void setDesiredRateHour(Float desiredRateHour) {
        this.desiredRateHour = desiredRateHour;
    }

    @XmlTransient
    public Collection<CandidateLog> getCandidateLogCollection() {
        return candidateLogCollection;
    }

    public void setCandidateLogCollection(Collection<CandidateLog> candidateLogCollection) {
        this.candidateLogCollection = candidateLogCollection;
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
        if (!(object instanceof Candidate)) {
            return false;
        }
        Candidate other = (Candidate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kenmcwilliams.employmentsystem.orm.Candidate[ id=" + id + " ]";
    }
    
}
