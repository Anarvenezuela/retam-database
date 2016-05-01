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
@Table(name = "representative")
@NamedQueries({
    @NamedQuery(name = "Representative.findAll", query = "SELECT r FROM Representative r"),
    @NamedQuery(name = "Representative.findByIdRepresentative", query = "SELECT r FROM Representative r WHERE r.idRepresentative = :idRepresentative"),
    @NamedQuery(name = "Representative.findByName", query = "SELECT r FROM Representative r WHERE r.name = :name"),
    @NamedQuery(name = "Representative.findByPosition", query = "SELECT r FROM Representative r WHERE r.position = :position"),
    @NamedQuery(name = "Representative.findByProfession", query = "SELECT r FROM Representative r WHERE r.profession = :profession")})
public class Representative implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idRepresentative;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "position")
    private String position;
    @Column(name = "profession")
    private String profession;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRepresentative")
    private Collection<Project> projectCollection;
    @JoinColumn(name = "idOrganization", referencedColumnName = "idOrganization")
    @ManyToOne(optional = false)
    private Organization idOrganization;

    public Representative() {
    }

    public Representative(Integer idRepresentative) {
        this.idRepresentative = idRepresentative;
    }

    public Representative(Integer idRepresentative, String name) {
        this.idRepresentative = idRepresentative;
        this.name = name;
    }

    public Integer getIdRepresentative() {
        return idRepresentative;
    }

    public void setIdRepresentative(Integer idRepresentative) {
        this.idRepresentative = idRepresentative;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public Organization getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Organization idOrganization) {
        this.idOrganization = idOrganization;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRepresentative != null ? idRepresentative.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Representative)) {
            return false;
        }
        Representative other = (Representative) object;
        return !((this.idRepresentative == null && other.idRepresentative != null) ||
                (this.idRepresentative != null && !this.idRepresentative.equals(other.idRepresentative)));
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Representative[ idRepresentative=" + idRepresentative + " ]";
    }
    
}
