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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "population_participation_type")
@NamedQueries({
    @NamedQuery(name = "PopulationParticipationType.findAll", query = "SELECT p FROM PopulationParticipationType p"),
    @NamedQuery(name = "PopulationParticipationType.findByIdPopulationParticipationType", query = "SELECT p FROM PopulationParticipationType p WHERE p.idPopulationParticipationType = :idPopulationParticipationType"),
    @NamedQuery(name = "PopulationParticipationType.findByName", query = "SELECT p FROM PopulationParticipationType p WHERE p.name = :name")})
public class PopulationParticipationType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idPopulationParticipationType")
    private Integer idPopulationParticipationType;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "populationParticipationTypeCollection")
    private Collection<Project> projectCollection;

    public PopulationParticipationType() {
    }

    public PopulationParticipationType(String name) {
        this.name = name;
    }

    public Integer getIdPopulationParticipationType() {
        return idPopulationParticipationType;
    }

    public void setIdPopulationParticipationType(Integer idPopulationParticipationType) {
        this.idPopulationParticipationType = idPopulationParticipationType;
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
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idPopulationParticipationType);
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
        final PopulationParticipationType other = (PopulationParticipationType) obj;
        if (!Objects.equals(this.idPopulationParticipationType, other.idPopulationParticipationType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PopulationParticipationType{"
                + "idPopulationParticipationType=" + idPopulationParticipationType
                + ", name=" + name
                + ", projectCollection=" + projectCollection + '}';
    }

}
