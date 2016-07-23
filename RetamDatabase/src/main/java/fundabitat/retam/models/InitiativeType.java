/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "initiative_type")
@NamedQueries({
    @NamedQuery(name = "InitiativeType.findAll", query = "SELECT i FROM InitiativeType i"),
    @NamedQuery(name = "InitiativeType.findByIdInitiativeType", query = "SELECT i FROM InitiativeType i WHERE i.idInitiativeType = :idInitiativeType"),
    @NamedQuery(name = "InitiativeType.findByName", query = "SELECT i FROM InitiativeType i WHERE i.name = :name")})
public class InitiativeType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idInitiativeType")
    private Integer idInitiativeType;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "initiativeTypeCollection")
    private Collection<Project> projectCollection;

    public InitiativeType() {
    }

    public InitiativeType(String name) {
        this.name = name;
    }

    public Integer getIdInitiativeType() {
        return idInitiativeType;
    }

    public void setIdInitiativeType(Integer idInitiativeType) {
        this.idInitiativeType = idInitiativeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.idInitiativeType);
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
        final InitiativeType other = (InitiativeType) obj;
        if (!Objects.equals(this.idInitiativeType, other.idInitiativeType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InitiativeType{" + "idInitiativeType=" + idInitiativeType
                + ", name=" + name
                + ", projectCollection=" + projectCollection + '}';
    }

}
