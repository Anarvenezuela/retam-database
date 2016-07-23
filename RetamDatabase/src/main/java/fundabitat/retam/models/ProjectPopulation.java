/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "ProjectPopulation.findAll", query = "SELECT p FROM ProjectPopulation p")})
public class ProjectPopulation implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ProjectPopulationPK projectPopulationPK;

    @MapsId("idPopulationSegment")
    @JoinColumn(name = "idPopulationSegment", referencedColumnName = "idPopulationSegment")
    @ManyToOne(optional = false)
    private PopulationSegment idPopulationSegment;

    @MapsId("idPopulationType")
    @JoinColumn(name = "idPopulationType", referencedColumnName = "idPopulationType")
    @ManyToOne(optional = false)
    private PopulationType idPopulationType;

    @MapsId("idProject")
    @JoinColumn(name = "idProject", referencedColumnName = "idProject")
    @ManyToOne(optional = false)
    private Project idProject;

    public ProjectPopulation() {
    }

    public ProjectPopulation(ProjectPopulationPK projectPopulationPK) {
        this.projectPopulationPK = projectPopulationPK;
    }

    public ProjectPopulationPK getProjectPopulationPK() {
        return projectPopulationPK;
    }

    public void setProjectPopulationPK(ProjectPopulationPK projectPopulationPK) {
        this.projectPopulationPK = projectPopulationPK;
    }

    public PopulationSegment getIdPopulationSegment() {
        return idPopulationSegment;
    }

    public void setIdPopulationSegment(PopulationSegment idPopulationSegment) {
        this.idPopulationSegment = idPopulationSegment;
    }

    public PopulationType getIdPopulationType() {
        return idPopulationType;
    }

    public void setIdPopulationType(PopulationType idPopulationType) {
        this.idPopulationType = idPopulationType;
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectPopulationPK != null ? projectPopulationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectPopulation)) {
            return false;
        }
        ProjectPopulation other = (ProjectPopulation) object;
        if ((this.projectPopulationPK == null && other.projectPopulationPK != null) || (this.projectPopulationPK != null && !this.projectPopulationPK.equals(other.projectPopulationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.ProjectPopulation[ projectPopulationPK=" + projectPopulationPK + " ]";
    }

}
