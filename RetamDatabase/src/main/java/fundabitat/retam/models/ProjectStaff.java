/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "project_staff")
@NamedQueries({
    @NamedQuery(name = "ProjectStaff.findAll", query = "SELECT p FROM ProjectStaff p"),
    @NamedQuery(name = "ProjectStaff.findByIsForeign", query = "SELECT p FROM ProjectStaff p WHERE p.isForeign = :isForeign"),
    @NamedQuery(name = "ProjectStaff.findByIsVolunteer", query = "SELECT p FROM ProjectStaff p WHERE p.isVolunteer = :isVolunteer")})
public class ProjectStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ProjectStaffPK projectStaffPK;

    @Basic(optional = false)
    @Column(name = "isForeign")
    private int isForeign;
    @Basic(optional = false)
    @Column(name = "isVolunteer")
    private int isVolunteer;

    @MapsId("idStaffJobType")
    @JoinColumn(name = "idStaffJobType", referencedColumnName = "idStaffJobType")
    @ManyToOne(optional = false)
    private StaffJobType idStaffJobType;

    @MapsId("idProject")
    @JoinColumn(name = "idProject", referencedColumnName = "idProject")
    @ManyToOne(optional = false)
    private Project idProject;

    public ProjectStaff() {
    }

    public ProjectStaff(ProjectStaffPK projectStaffPK) {
        this.projectStaffPK = projectStaffPK;
    }

    public ProjectStaff(int isForeign, int isVolunteer,
            StaffJobType idStaffJobType, Project idProject) {
        this.isForeign = isForeign;
        this.isVolunteer = isVolunteer;
        this.idStaffJobType = idStaffJobType;
        this.idProject = idProject;
        this.projectStaffPK = new ProjectStaffPK(idProject.getIdProject(),
                idStaffJobType.getIdStaffJobType());
    }

    public ProjectStaff(ProjectStaffPK projectStaffPK, int isForeign, int isVolunteer) {
        this.projectStaffPK = projectStaffPK;
        this.isForeign = isForeign;
        this.isVolunteer = isVolunteer;
    }

    public ProjectStaffPK getProjectStaffPK() {
        return projectStaffPK;
    }

    public void setProjectStaffPK(ProjectStaffPK projectStaffPK) {
        this.projectStaffPK = projectStaffPK;
    }

    public int getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(int isForeign) {
        this.isForeign = isForeign;
    }

    public int getIsVolunteer() {
        return isVolunteer;
    }

    public void setIsVolunteer(int isVolunteer) {
        this.isVolunteer = isVolunteer;
    }

    public StaffJobType getIdStaffJobType() {
        return idStaffJobType;
    }

    public void setIdStaffJobType(StaffJobType idStaffJobType) {
        this.idStaffJobType = idStaffJobType;
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
        hash += (projectStaffPK != null ? projectStaffPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectStaff)) {
            return false;
        }
        ProjectStaff other = (ProjectStaff) object;
        if ((this.projectStaffPK == null && other.projectStaffPK != null) || (this.projectStaffPK != null && !this.projectStaffPK.equals(other.projectStaffPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.ProjectStaff[ projectStaffPK=" + projectStaffPK + " ]";
    }

}
