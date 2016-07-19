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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o ORDER BY o.code"),
    @NamedQuery(name = "Organization.findByIdOrganization", query = "SELECT o FROM Organization o WHERE o.idOrganization = :idOrganization"),
    @NamedQuery(name = "Organization.findByCode", query = "SELECT o FROM Organization o WHERE o.code = :code"),
    @NamedQuery(name = "Organization.findByName", query = "SELECT o FROM Organization o WHERE o.name = :name")})
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer idOrganization;
    @Column(name = "code")
    private Integer code;
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
    @JoinColumn(name = "idCountry", referencedColumnName = "idCountry")
    @ManyToOne(optional = false)
    private Country idCountry;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrganization")
    private Collection<Representative> representativeCollection;
    @ManyToMany(mappedBy = "organizationCollection")
    private Collection<Project> projectCollection;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<ProjectOrganizationParticipation> projectOrganizationParticipationCollection;

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    /**
     * Sets fields to null if they are empty. Fields are set to empty to make
     * migration easier. But when saving I'd rather store nulls than empty
     * strings.
     */
    public void setFieldsToNullIfEmpty() {

        if (address.isEmpty()) {
            address = null;
        }

        if (city.isEmpty()) {
            city = null;
        }

        if (email.isEmpty()) {
            email = null;
        }

        if (fax1.isEmpty()) {
            fax1 = null;
        }

        if (fax2.isEmpty()) {
            fax2 = null;
        }

        if (name.isEmpty()) {
            name = null;
        }

        if (phone1.isEmpty()) {
            phone1 = null;
        }

        if (phone2.isEmpty()) {
            phone2 = null;
        }

        if (postalCode.isEmpty()) {
            postalCode = null;
        }

        if (website.isEmpty()) {
            website = null;
        }
    }

    public String getCountryName() {
        return idCountry.getName();
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
        return !((this.idOrganization == null && other.idOrganization != null)
                || (this.idOrganization != null && !this.idOrganization.equals(other.idOrganization)));
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Organization[ name=" + name + " ]";
    }

    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public Collection<ProjectOrganizationParticipation> getProjectOrganizationParticipationCollection() {
        return projectOrganizationParticipationCollection;
    }

    public void setProjectOrganizationParticipationCollection(Collection<ProjectOrganizationParticipation> projectOrganizationParticipationCollection) {
        this.projectOrganizationParticipationCollection = projectOrganizationParticipationCollection;
    }

}
