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
    @MapsId("isForeign")
    @Column(name = "isForeign")
    private boolean isForeign;

    @Basic(optional = false)
    @MapsId("isVolunteer")
    @Column(name = "isVolunteer")
    private boolean isVolunteer;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;

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

    public ProjectStaff(boolean isForeign, boolean isVolunteer, int quantity,
            StaffJobType idStaffJobType, Project idProject) {
        this.isForeign = isForeign;
        this.isVolunteer = isVolunteer;
        this.quantity = quantity;
        this.idStaffJobType = idStaffJobType;
        this.idProject = idProject;
        this.projectStaffPK = new ProjectStaffPK(idProject.getIdProject(),
                idStaffJobType.getIdStaffJobType());
    }

    public ProjectStaff(ProjectStaffPK projectStaffPK, boolean isForeign, boolean isVolunteer) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
