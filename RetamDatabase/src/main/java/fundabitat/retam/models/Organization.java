/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "organization")
@NamedQueries({
    @NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o"),
    @NamedQuery(name = "Organization.findByIdOrganization", query = "SELECT o FROM Organization o WHERE o.idOrganization = :idOrganization"),
    @NamedQuery(name = "Organization.findByCode", query = "SELECT o FROM Organization o WHERE o.code = :code"),
    @NamedQuery(name = "Organization.findByName", query = "SELECT o FROM Organization o WHERE o.name = :name"),
    @NamedQuery(name = "Organization.findByAddress", query = "SELECT o FROM Organization o WHERE o.address = :address"),
    @NamedQuery(name = "Organization.findByCity", query = "SELECT o FROM Organization o WHERE o.city = :city"),
    @NamedQuery(name = "Organization.findByPhone1", query = "SELECT o FROM Organization o WHERE o.phone1 = :phone1"),
    @NamedQuery(name = "Organization.findByPhone2", query = "SELECT o FROM Organization o WHERE o.phone2 = :phone2"),
    @NamedQuery(name = "Organization.findByFax1", query = "SELECT o FROM Organization o WHERE o.fax1 = :fax1"),
    @NamedQuery(name = "Organization.findByFax2", query = "SELECT o FROM Organization o WHERE o.fax2 = :fax2"),
    @NamedQuery(name = "Organization.findByEmail", query = "SELECT o FROM Organization o WHERE o.email = :email"),
    @NamedQuery(name = "Organization.findByWebsite", query = "SELECT o FROM Organization o WHERE o.website = :website"),
    @NamedQuery(name = "Organization.findByPostalCode", query = "SELECT o FROM Organization o WHERE o.postalCode = :postalCode"),
    @NamedQuery(name = "Organization.findByTelex", query = "SELECT o FROM Organization o WHERE o.telex = :telex")})
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idOrganization;
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "city")
    private String city;
    @Column(name = "phone1")
    private String phone1;
    @Column(name = "phone2")
    private String phone2;
    @Column(name = "fax1")
    private String fax1;
    @Column(name = "fax2")
    private String fax2;
    @Column(name = "email")
    private String email;
    @Column(name = "website")
    private String website;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "telex")
    private String telex;
    @JoinColumn(name = "idCountry", referencedColumnName = "idCountry")
    @ManyToOne(optional = false)
    private Country idCountry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrganization")
    private Collection<Representative> representativeCollection;

    public Organization() {
    }

    public Organization(Integer idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Organization(Integer idOrganization, String name, String address, String city) {
        this.idOrganization = idOrganization;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public Integer getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Integer idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getFax1() {
        return fax1;
    }

    public void setFax1(String fax1) {
        this.fax1 = fax1;
    }

    public String getFax2() {
        return fax2;
    }

    public void setFax2(String fax2) {
        this.fax2 = fax2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTelex() {
        return telex;
    }

    public void setTelex(String telex) {
        this.telex = telex;
    }

    public Country getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Country idCountry) {
        this.idCountry = idCountry;
    }

    public Collection<Representative> getRepresentativeCollection() {
        return representativeCollection;
    }

    public void setRepresentativeCollection(Collection<Representative> representativeCollection) {
        this.representativeCollection = representativeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrganization != null ? idOrganization.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) object;
        return !((this.idOrganization == null && other.idOrganization != null) || 
                (this.idOrganization != null && !this.idOrganization.equals(other.idOrganization)));
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Organization[ idOrganization=" + idOrganization + " ]";
    }
    
}
