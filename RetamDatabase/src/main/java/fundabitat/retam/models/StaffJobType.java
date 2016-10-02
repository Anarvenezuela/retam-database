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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "staff_job_type")
@NamedQueries({
    @NamedQuery(name = "StaffJobType.findAll", query = "SELECT s FROM StaffJobType s"),
    @NamedQuery(name = "StaffJobType.findByIdStaffJobType", query = "SELECT s FROM StaffJobType s WHERE s.idStaffJobType = :idStaffJobType"),
    @NamedQuery(name = "StaffJobType.findByName", query = "SELECT s FROM StaffJobType s WHERE s.name = :name")})
public class StaffJobType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idStaffJobType")
    private Integer idStaffJobType;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStaffJobType")
    private Collection<ProjectStaff> projectStaffCollection;

    public StaffJobType() {
    }

    public StaffJobType(String name) {
        this.name = name;
    }

    public Integer getIdStaffJobType() {
        return idStaffJobType;
    }

    public void setIdStaffJobType(Integer idStaffJobType) {
        this.idStaffJobType = idStaffJobType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ProjectStaff> getProjectStaffCollection() {
        return projectStaffCollection;
    }

    public void setProjectStaffCollection(Collection<ProjectStaff> projectStaffCollection) {
        this.projectStaffCollection = projectStaffCollection;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final StaffJobType other = (StaffJobType) obj;
        if (!Objects.equals(this.idStaffJobType, other.idStaffJobType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StaffJobType{" + "idStaffJobType=" + idStaffJobType + ", name=" + name + ", projectStaffCollection=" + projectStaffCollection + '}';
    }

}
