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
public class ProjectStaffPK implements Serializable {

    @Column(name = "idProject")
    private Integer idProject;

    @Column(name = "idStaffJobType")
    private Integer idStaffJobType;

    @Column(name = "isForeign")
    private boolean isForeign;

    @Column(name = "isVolunteer")
    private boolean isVolunteer;

    public ProjectStaffPK() {
    }

    public ProjectStaffPK(Integer idProject, Integer idStaffJobType,
            boolean isForeign, boolean isVolunteer) {
        this.idProject = idProject;
        this.idStaffJobType = idStaffJobType;
        this.isForeign = isForeign;
        this.isVolunteer = isVolunteer;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public Integer getIdStaffJobType() {
        return idStaffJobType;
    }

    public void setIdStaffJobType(Integer idStaffJobType) {
        this.idStaffJobType = idStaffJobType;
    }

    public boolean getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(boolean isForeign) {
        this.isForeign = isForeign;
    }

    public boolean getIsVolunteer() {
        return isVolunteer;
    }

    public void setIsVolunteer(boolean isVolunteer) {
        this.isVolunteer = isVolunteer;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.idProject);
        hash = 47 * hash + Objects.hashCode(this.idStaffJobType);
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
        final ProjectStaffPK other = (ProjectStaffPK) obj;
        if (!Objects.equals(this.idProject, other.idProject)) {
            return false;
        }
        if (!Objects.equals(this.idStaffJobType, other.idStaffJobType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProjectStaffPK{" + "idProject=" + idProject + ", idStaffJobType=" + idStaffJobType + '}';
    }

}
