/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author marcos
 */
@Embeddable
public class ProjectPopulationPK implements Serializable {

    @Column(name = "idProject")
    private Integer idProject;

    @Column(name = "idPopulationType")
    private Integer idPopulationType;

    @Column(name = "idPopulationSegment")
    private Integer idPopulationSegment;

    public ProjectPopulationPK() {
    }

    public ProjectPopulationPK(Integer idProject, Integer idPopulationType, Integer idPopulationSegment) {
        this.idProject = idProject;
        this.idPopulationType = idPopulationType;
        this.idPopulationSegment = idPopulationSegment;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public Integer getIdPopulationType() {
        return idPopulationType;
    }

    public void setIdPopulationType(Integer idPopulationType) {
        this.idPopulationType = idPopulationType;
    }

    public Integer getIdPopulationSegment() {
        return idPopulationSegment;
    }

    public void setIdPopulationSegment(Integer idPopulationSegment) {
        this.idPopulationSegment = idPopulationSegment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idProject);
        hash = 97 * hash + Objects.hashCode(this.idPopulationType);
        hash = 97 * hash + Objects.hashCode(this.idPopulationSegment);
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
        final ProjectPopulationPK other = (ProjectPopulationPK) obj;
        if (!Objects.equals(this.idProject, other.idProject)) {
            return false;
        }
        if (!Objects.equals(this.idPopulationType, other.idPopulationType)) {
            return false;
        }
        if (!Objects.equals(this.idPopulationSegment, other.idPopulationSegment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProjectPopulationPK{" + "idProject=" + idProject
                + ", idPopulationType=" + idPopulationType
                + ", idPopulationSegment=" + idPopulationSegment + '}';
    }

}
