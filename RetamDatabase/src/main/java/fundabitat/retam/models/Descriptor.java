/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "descriptor")
@NamedQueries({
    @NamedQuery(name = "Descriptor.findAll", query = "SELECT d FROM Descriptor d"),
    @NamedQuery(name = "Descriptor.findByIdDescriptor", query = "SELECT d FROM Descriptor d WHERE d.idDescriptor = :idDescriptor"),
    @NamedQuery(name = "Descriptor.findByName", query = "SELECT d FROM Descriptor d WHERE d.name = :name")})
public class Descriptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer idDescriptor;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "descriptorCollection")
    private Collection<Project> projectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDescriptor")
    private Collection<SubDescriptor> subDescriptorCollection;

    public Descriptor() {
    }

    public Descriptor(Integer idDescriptor) {
        this.idDescriptor = idDescriptor;
    }

    public Integer getIdDescriptor() {
        return idDescriptor;
    }

    public void setIdDescriptor(Integer idDescriptor) {
        this.idDescriptor = idDescriptor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public Collection<SubDescriptor> getSubDescriptorCollection() {
        return subDescriptorCollection;
    }

    public void setSubDescriptorCollection(Collection<SubDescriptor> subDescriptorCollection) {
        this.subDescriptorCollection = subDescriptorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDescriptor != null ? idDescriptor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Descriptor)) {
            return false;
        }
        Descriptor other = (Descriptor) object;
        return !((this.idDescriptor == null && other.idDescriptor != null)
                || (this.idDescriptor != null && !this.idDescriptor.equals(other.idDescriptor)));
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Descriptor[ idDescriptor=" + idDescriptor + " ]";
    }

}
