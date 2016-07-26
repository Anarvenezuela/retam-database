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
@Table(name = "population_segment")
@NamedQueries({
    @NamedQuery(name = "PopulationSegment.findAll", query = "SELECT p FROM PopulationSegment p"),
    @NamedQuery(name = "PopulationSegment.findByIdPopulationSegment", query = "SELECT p FROM PopulationSegment p WHERE p.idPopulationSegment = :idPopulationSegment"),
    @NamedQuery(name = "PopulationSegment.findByName", query = "SELECT p FROM PopulationSegment p WHERE p.name = :name")})
public class PopulationSegment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idPopulationSegment")
    private Integer idPopulationSegment;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPopulationSegment")
    private Collection<ProjectPopulation> projectPopulationCollection;

    public PopulationSegment() {
    }

    public PopulationSegment(String name) {
        this.name = name;
    }

    public Integer getIdPopulationSegment() {
        return idPopulationSegment;
    }

    public void setIdPopulationSegment(Integer idPopulationSegment) {
        this.idPopulationSegment = idPopulationSegment;
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
        hash = 17 * hash + Objects.hashCode(this.idPopulationSegment);
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
        final PopulationSegment other = (PopulationSegment) obj;
        if (!Objects.equals(this.idPopulationSegment, other.idPopulationSegment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PopulationSegment{" + "idPopulationSegment=" + idPopulationSegment
                + ", name=" + name
                + ", projectPopulationCollection=" + projectPopulationCollection + '}';
    }

}
