/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
@Table
@NamedQueries({
    @NamedQuery(name = "Participation.findAll", query = "SELECT p FROM Participation p")})
public class Participation implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ParticipationPK participationPK;

    @MapsId("idParticipationType")
    @JoinColumn(name = "idParticipationType", referencedColumnName = "idParticipationType")
    @ManyToOne(optional = false)
    private ParticipationType idParticipationType;

    @MapsId("idOrganization")
    @JoinColumn(name = "idOrganization", referencedColumnName = "idOrganization")
    @ManyToOne(optional = false)
    private Organization idOrganization;

    @MapsId("idProject")
    @JoinColumn(name = "idProject", referencedColumnName = "idProject")
    @ManyToOne(optional = false)
    private Project idProject;

    public Participation() {
    }

    public Participation(ParticipationPK participationPK) {
        this.participationPK = participationPK;
    }

    public Participation(Project project, Organization organization,
            ParticipationType participationType) {

        this.idProject = project;
        this.idOrganization = organization;
        this.idParticipationType = participationType;
        this.participationPK
                = new ParticipationPK(project.getIdProject(),
                        organization.getIdOrganization(),
                        participationType.getIdParticipationType());
    }

    public ParticipationPK getParticipationPK() {
        return participationPK;
    }

    public void setParticipationPK(ParticipationPK participationPK) {
        this.participationPK = participationPK;
    }

    public ParticipationType getIdParticipationType() {
        return idParticipationType;
    }

    public void setIdParticipationType(ParticipationType idParticipationType) {
        this.idParticipationType = idParticipationType;
    }

    public Organization getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Organization idOrganization) {
        this.idOrganization = idOrganization;
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
        hash += (participationPK != null ? participationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participation)) {
            return false;
        }
        Participation other = (Participation) object;
        if ((this.participationPK == null && other.participationPK != null) || (this.participationPK != null && !this.participationPK.equals(other.participationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Participation[ participationPK=" + participationPK + " ]";
    }

    public static List<Organization> getOrgFromCollection(
            Collection<Participation> participations) {

        List<Organization> list = new ArrayList();

        for (Participation part : participations) {
            list.add(part.idOrganization);
        }

        return list;
    }

    public static List<ParticipationType> getPartTypeByOrgFromCollection(
            Collection<Participation> participations, Organization org) {

        List<ParticipationType> list = new ArrayList();

        for (Participation part : participations) {
            if (part.idOrganization.getIdOrganization().equals(org.getIdOrganization())) {
                list.add(part.idParticipationType);
            }
        }

        return list;
    }

}
