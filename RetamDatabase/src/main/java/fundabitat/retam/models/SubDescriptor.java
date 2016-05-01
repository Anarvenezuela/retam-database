/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "subDescriptor")
@NamedQueries({
    @NamedQuery(name = "SubDescriptor.findAll", query = "SELECT s FROM SubDescriptor s"),
    @NamedQuery(name = "SubDescriptor.findByIdSubDescriptor", query = "SELECT s FROM SubDescriptor s WHERE s.idSubDescriptor = :idSubDescriptor"),
    @NamedQuery(name = "SubDescriptor.findByName", query = "SELECT s FROM SubDescriptor s WHERE s.name = :name")})
public class SubDescriptor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer idSubDescriptor;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "subDescriptorCollection")
    private Collection<Project> projectCollection;
    @JoinColumn(name = "idDescriptor", referencedColumnName = "idDescriptor")
    @ManyToOne(optional = false)
    private Descriptor idDescriptor;

    public SubDescriptor() {
    }

    public SubDescriptor(Integer idSubDescriptor) {
        this.idSubDescriptor = idSubDescriptor;
    }

    public Integer getIdSubDescriptor() {
        return idSubDescriptor;
    }

    public void setIdSubDescriptor(Integer idSubDescriptor) {
        this.idSubDescriptor = idSubDescriptor;
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

    public Descriptor getIdDescriptor() {
        return idDescriptor;
    }

    public void setIdDescriptor(Descriptor idDescriptor) {
        this.idDescriptor = idDescriptor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubDescriptor != null ? idSubDescriptor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubDescriptor)) {
            return false;
        }
        SubDescriptor other = (SubDescriptor) object;
        return !((this.idSubDescriptor == null && other.idSubDescriptor != null) || (this.idSubDescriptor != null && !this.idSubDescriptor.equals(other.idSubDescriptor)));
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.SubDescriptor[ idSubDescriptor=" + idSubDescriptor + " ]";
    }
    
}
