/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "participation_type")
@NamedQueries({
    @NamedQuery(name = "ParticipationType.findAll", query = "SELECT p FROM ParticipationType p"),
    @NamedQuery(name = "ParticipationType.findByIdParticipationType", query = "SELECT p FROM ParticipationType p WHERE p.idParticipationType = :idParticipationType"),
    @NamedQuery(name = "ParticipationType.findByCode", query = "SELECT p FROM ParticipationType p WHERE p.code = :code"),
    @NamedQuery(name = "ParticipationType.findByName", query = "SELECT p FROM ParticipationType p WHERE p.name = :name")})
public class ParticipationType implements Serializable {

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idParticipationType")
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idParticipationType")
    private Integer idParticipationType;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idParticipationType")
    private Collection<Participation> participationCollection;

    public ParticipationType() {
    }

    public Integer getIdParticipationType() {
        return idParticipationType;
    }

    public void setIdParticipationType(Integer idParticipationType) {
        this.idParticipationType = idParticipationType;
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

    public Collection<Participation> getParticipationCollection() {
        return participationCollection;
    }

    public void setParticipationCollection(Collection<Participation> participationCollection) {
        this.participationCollection = participationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParticipationType != null ? idParticipationType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipationType)) {
            return false;
        }
        ParticipationType other = (ParticipationType) object;
        if ((this.idParticipationType == null && other.idParticipationType != null) || (this.idParticipationType != null && !this.idParticipationType.equals(other.idParticipationType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Participation[ idParticipationType=" + idParticipationType + " ]";
    }

}
