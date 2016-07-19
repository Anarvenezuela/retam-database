/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "ProjectOrganizationParticipation.findAll", query = "SELECT p FROM ProjectOrganizationParticipation p")})
public class ProjectOrganizationParticipation implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ProjectOrganizationParticipationPK projectOrganizationParticipationPK;

    public ProjectOrganizationParticipation() {
    }

    public ProjectOrganizationParticipation(ProjectOrganizationParticipationPK projectOrganizationParticipationPK) {
        this.projectOrganizationParticipationPK = projectOrganizationParticipationPK;
    }

    public ProjectOrganizationParticipationPK getProjectOrganizationParticipationPK() {
        return projectOrganizationParticipationPK;
    }

    public void setProjectOrganizationParticipationPK(ProjectOrganizationParticipationPK projectOrganizationParticipationPK) {
        this.projectOrganizationParticipationPK = projectOrganizationParticipationPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectOrganizationParticipationPK != null ? projectOrganizationParticipationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectOrganizationParticipation)) {
            return false;
        }
        ProjectOrganizationParticipation other = (ProjectOrganizationParticipation) object;
        if ((this.projectOrganizationParticipationPK == null && other.projectOrganizationParticipationPK != null) || (this.projectOrganizationParticipationPK != null && !this.projectOrganizationParticipationPK.equals(other.projectOrganizationParticipationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.ProjectOrganizationParticipation[ "
                + "projectOrganizationParticipationPK="
                + projectOrganizationParticipationPK + " ]";
    }

}
