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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "population_type")
@NamedQueries({
    @NamedQuery(name = "PopulationType.findAll", query = "SELECT p FROM PopulationType p"),
    @NamedQuery(name = "PopulationType.findByIdPopulationType", query = "SELECT p FROM PopulationType p WHERE p.idPopulationType = :idPopulationType"),
    @NamedQuery(name = "PopulationType.findByName", query = "SELECT p FROM PopulationType p WHERE p.name = :name")})
public class PopulationType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idPopulationType")
    private Integer idPopulationType;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPopulationType")
    private Collection<ProjectPopulation> projectPopulationCollection;

    public PopulationType() {
    }

    public PopulationType(String name) {
        this.name = name;
    }

    public Integer getIdPopulationType() {
        return idPopulationType;
    }

    public void setIdPopulationType(Integer idPopulationType) {
        this.idPopulationType = idPopulationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ProjectPopulation> getProjectPopulationCollection() {
        return projectPopulationCollection;
    }

    public void setProjectPopulationCollection(Collection<ProjectPopulation> projectPopulationCollection) {
        this.projectPopulationCollection = projectPopulationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idPopulationType);
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
        final PopulationType other = (PopulationType) obj;
        if (!Objects.equals(this.idPopulationType, other.idPopulationType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PopulationType{" + "idPopulationType=" + idPopulationType
                + ", name=" + name
                + ", projectPopulationCollection=" + projectPopulationCollection + '}';
    }

}
