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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "beneficiary")
@NamedQueries({
    @NamedQuery(name = "Beneficiary.findAll", query = "SELECT b FROM Beneficiary b"),
    @NamedQuery(name = "Beneficiary.findByIdBeneficiary", query = "SELECT b FROM Beneficiary b WHERE b.idBeneficiary = :idBeneficiary"),
    @NamedQuery(name = "Beneficiary.findByCode", query = "SELECT b FROM Beneficiary b WHERE b.code = :code"),
    @NamedQuery(name = "Beneficiary.findByName", query = "SELECT b FROM Beneficiary b WHERE b.name = :name")})
public class Beneficiary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idBeneficiary")
    private Integer idBeneficiary;
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "beneficiaryCollection")
    private Collection<Project> projectCollection;

    public Beneficiary() {
    }

    public Beneficiary(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getIdBeneficiary() {
        return idBeneficiary;
    }

    public void setIdBeneficiary(Integer idBeneficiary) {
        this.idBeneficiary = idBeneficiary;
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

    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idBeneficiary);
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
        final Beneficiary other = (Beneficiary) obj;
        if (!Objects.equals(this.idBeneficiary, other.idBeneficiary)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Beneficiary[ idBeneficiary=" + idBeneficiary + " ]";
    }

}
