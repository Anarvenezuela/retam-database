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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "participation")
@NamedQueries({
    @NamedQuery(name = "Participation.findAll", query = "SELECT p FROM Participation p"),
    @NamedQuery(name = "Participation.findByIdParticipation", query = "SELECT p FROM Participation p WHERE p.idParticipation = :idParticipation"),
    @NamedQuery(name = "Participation.findByCode", query = "SELECT p FROM Participation p WHERE p.code = :code"),
    @NamedQuery(name = "Participation.findByName", query = "SELECT p FROM Participation p WHERE p.name = :name")})
public class Participation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idParticipation")
    private Integer idParticipation;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idParticipation")
    private Collection<ProjectOrganizationParticipation> projectOrganizationParticipationCollection;

    public Participation() {
    }

    public Integer getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(Integer idParticipation) {
        this.idParticipation = idParticipation;
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

    public Collection<ProjectOrganizationParticipation> getProjectOrganizationParticipationCollection() {
        return projectOrganizationParticipationCollection;
    }

    public void setProjectOrganizationParticipationCollection(Collection<ProjectOrganizationParticipation> projectOrganizationParticipationCollection) {
        this.projectOrganizationParticipationCollection = projectOrganizationParticipationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParticipation != null ? idParticipation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participation)) {
            return false;
        }
        Participation other = (Participation) object;
        if ((this.idParticipation == null && other.idParticipation != null) || (this.idParticipation != null && !this.idParticipation.equals(other.idParticipation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Participation[ idParticipation=" + idParticipation + " ]";
    }

}
