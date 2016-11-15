/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "exchange")
@NamedQueries({
    @NamedQuery(name = "Exchange.findAll", query = "SELECT e FROM Exchange e"),
    @NamedQuery(name = "Exchange.findByProjectName", query = "SELECT e FROM Exchange e WHERE e.projectName = :projectName"),
    @NamedQuery(name = "Exchange.findByOrganization", query = "SELECT e FROM Exchange e WHERE e.organization = :organization"),
    @NamedQuery(name = "Exchange.findByRepresentative", query = "SELECT e FROM Exchange e WHERE e.representative = :representative"),
    @NamedQuery(name = "Exchange.findByAddress", query = "SELECT e FROM Exchange e WHERE e.address = :address"),
    @NamedQuery(name = "Exchange.findByCity", query = "SELECT e FROM Exchange e WHERE e.city = :city"),
    @NamedQuery(name = "Exchange.findByPhone", query = "SELECT e FROM Exchange e WHERE e.phone = :phone"),
    @NamedQuery(name = "Exchange.findByFax", query = "SELECT e FROM Exchange e WHERE e.fax = :fax"),
    @NamedQuery(name = "Exchange.findByEmail", query = "SELECT e FROM Exchange e WHERE e.email = :email"),
    @NamedQuery(name = "Exchange.findByWebpage", query = "SELECT e FROM Exchange e WHERE e.webpage = :webpage"),
    @NamedQuery(name = "Exchange.findByExchangeType", query = "SELECT e FROM Exchange e WHERE e.exchangeType = :exchangeType"),
    @NamedQuery(name = "Exchange.findByContactExists", query = "SELECT e FROM Exchange e WHERE e.contactExists = :contactExists"),
    @NamedQuery(name = "Exchange.findByNoContactReason", query = "SELECT e FROM Exchange e WHERE e.noContactReason = :noContactReason")})
public class Exchange implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer idProject;
    @Column(name = "projectName")
    private String projectName;
    @Column(name = "organization")
    private String organization;
    @Column(name = "representative")
    private String representative;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "phone")
    private String phone;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;
    @Column(name = "webpage")
    private String webpage;
    @Column(name = "exchangeType")
    private String exchangeType;
    @Basic(optional = false)
    @Column(name = "contactExists")
    private int contactExists;
    @Column(name = "noContactReason")
    private String noContactReason;

    public Exchange() {
    }

    public Exchange(Integer idProject) {
        this.idProject = idProject;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public int getContactExists() {
        return contactExists;
    }

    public void setContactExists(int contactExists) {
        this.contactExists = contactExists;
    }

    public String getNoContactReason() {
        return noContactReason;
    }

    public void setNoContactReason(String noContactReason) {
        this.noContactReason = noContactReason;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idProject);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Exchange other = (Exchange) obj;
        if (!Objects.equals(this.idProject, other.idProject)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exchange {"
                + "idProject=" + idProject
                + ", projectName=" + projectName
                + ", organization=" + organization
                + ", representative=" + representative
                + ", address=" + address
                + ", city=" + city
                + ", phone=" + phone
                + ", fax=" + fax
                + ", email=" + email
                + ", webpage=" + webpage
                + ", exchangeType=" + exchangeType
                + ", contactExists=" + contactExists
                + ", noContactReason=" + noContactReason + '}';
    }

}
