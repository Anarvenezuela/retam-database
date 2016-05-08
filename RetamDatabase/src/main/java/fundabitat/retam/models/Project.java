/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "project")
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByIdProject", query = "SELECT p FROM Project p WHERE p.idProject = :idProject"),
    @NamedQuery(name = "Project.findByCode", query = "SELECT p FROM Project p WHERE p.code = :code"),
    @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idProject")
    private Integer idProject;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "project_subDescriptor", joinColumns = {
        @JoinColumn(name = "idProject", referencedColumnName = "idProject")}, inverseJoinColumns = {
        @JoinColumn(name = "idSubDescriptor", referencedColumnName = "idSubDescriptor")})
    @ManyToMany
    private Collection<SubDescriptor> subDescriptorCollection;
    @ManyToMany(mappedBy = "projectCollection")
    private Collection<Descriptor> descriptorCollection;
    @JoinColumn(name = "idCountry", referencedColumnName = "idCountry")
    @ManyToOne(optional = false)
    private Country idCountry;
    @JoinColumn(name = "idRepresentative", referencedColumnName = "idRepresentative")
    @ManyToOne(optional = false)
    private Representative idRepresentative;

    public Project() {
    }

    public Project(Integer idProject) {
        this.idProject = idProject;
    }

    public Project(Integer idProject, String code, String name) {
        this.idProject = idProject;
        this.code = code;
        this.name = name;
    }

    public Integer getIdProject() {
        return idProject;
    }

    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SubDescriptor> getSubDescriptorCollection() {
        return subDescriptorCollection;
    }

    public void setSubDescriptorCollection(Collection<SubDescriptor> subDescriptorCollection) {
        this.subDescriptorCollection = subDescriptorCollection;
    }

    public Collection<Descriptor> getDescriptorCollection() {
        return descriptorCollection;
    }

    public void setDescriptorCollection(Collection<Descriptor> descriptorCollection) {
        this.descriptorCollection = descriptorCollection;
    }

    public Country getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Country idCountry) {
        this.idCountry = idCountry;
    }

    public Representative getIdRepresentative() {
        return idRepresentative;
    }

    public void setIdRepresentative(Representative idRepresentative) {
        this.idRepresentative = idRepresentative;
    }
    
    public String getCountryName(){
        return idCountry.getName();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProject != null ? idProject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        return !((this.idProject == null && other.idProject != null) || 
                (this.idProject != null && !this.idProject.equals(other.idProject)));
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Project[ idProject=" + idProject + " ]";
    }
    
}
