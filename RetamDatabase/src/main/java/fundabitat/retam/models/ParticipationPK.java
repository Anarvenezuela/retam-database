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
public class ParticipationPK implements Serializable {

    @Column(name = "idProject")
    private Integer idProject;

    @Column(name = "idOrganization")
    private Integer idOrganization;

    @Column(name = "idParticipationType")
    private Integer idParticipationType;

    public ParticipationPK() {
    }

    public ParticipationPK(Integer idProject, Integer idOrganization, Integer idParticipationType) {
        this.idProject = idProject;
        this.idOrganization = idOrganization;
        this.idParticipationType = idParticipationType;
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

    public Integer getIdParticipationType() {
        return idParticipationType;
    }

    public void setIdParticipationType(Integer idParticipationType) {
        this.idParticipationType = idParticipationType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idProject);
        hash = 97 * hash + Objects.hashCode(this.idOrganization);
        hash = 97 * hash + Objects.hashCode(this.idParticipationType);
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
        final ParticipationPK other = (ParticipationPK) obj;
        if (!Objects.equals(this.idProject, other.idProject)) {
            return false;
        }
        if (!Objects.equals(this.idOrganization, other.idOrganization)) {
            return false;
        }
        if (!Objects.equals(this.idParticipationType, other.idParticipationType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProjectOrganizationParticipationPK{" + "idProject=" + idProject
                + ", idOrganization=" + idOrganization + ", idParticipationType="
                + idParticipationType + '}';
    }

}
