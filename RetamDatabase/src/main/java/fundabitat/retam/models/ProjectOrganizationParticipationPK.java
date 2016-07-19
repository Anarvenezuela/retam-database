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
public class ProjectOrganizationParticipationPK implements Serializable {

    @Column(name = "idProject")
    private Integer idProject;

    @Column(name = "idOrganization")
    private Integer idOrganization;

    @Column(name = "idParticipation")
    private Integer idParticipation;

    public ProjectOrganizationParticipationPK() {
    }

    public ProjectOrganizationParticipationPK(Integer idProject, Integer idOrganization, Integer idParticipation) {
        this.idProject = idProject;
        this.idOrganization = idOrganization;
        this.idParticipation = idParticipation;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public Integer getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Integer idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Integer getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(Integer idParticipation) {
        this.idParticipation = idParticipation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idProject);
        hash = 97 * hash + Objects.hashCode(this.idOrganization);
        hash = 97 * hash + Objects.hashCode(this.idParticipation);
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
        final ProjectOrganizationParticipationPK other = (ProjectOrganizationParticipationPK) obj;
        if (!Objects.equals(this.idProject, other.idProject)) {
            return false;
        }
        if (!Objects.equals(this.idOrganization, other.idOrganization)) {
            return false;
        }
        if (!Objects.equals(this.idParticipation, other.idParticipation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProjectOrganizationParticipationPK{" + "idProject=" + idProject
                + ", idOrganization=" + idOrganization + ", idParticipation="
                + idParticipation + '}';
    }

}
