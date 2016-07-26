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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcos
 */
@Entity
@Table(name = "project")
@NamedQueries({
    @NamedQuery(name = "Project.findAll",
            query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByIdProject",
            query = "SELECT p FROM Project p WHERE p.idProject = :idProject"),
    @NamedQuery(name = "Project.findByCode",
            query = "SELECT p FROM Project p WHERE p.code = :code"),
    @NamedQuery(name = "Project.findByName",
            query = "SELECT p FROM Project p WHERE p.name = :name"),
    @NamedQuery(name = "Project.filterProjectsByDescs",
            query = "SELECT DISTINCT p "
            + "FROM Project p "
            + "JOIN p.descriptorCollection d "
            + "WHERE d.idDescriptor IN :descs "
            + "AND p.idCountry IN :countries"),
    @NamedQuery(name = "Project.filterProjects",
            query = "SELECT DISTINCT p "
            + "FROM Project p "
            + "JOIN p.descriptorCollection d "
            + "JOIN p.subDescriptorCollection s "
            + "WHERE d.idDescriptor IN :descs AND s.idSubDescriptor IN :subs "
            + "AND p.idCountry IN :countries")})

public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idProject")
    private Integer idProject;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "generalObjective")
    private String generalObjective;
    @Column(name = "specificObjective")
    private String specificObjective;
    @Column(name = "methodology")
    private String methodology;
    @Column(name = "startingDate")
    private String startingDate;
    @Column(name = "duration")
    private String duration;

    @JoinTable(name = "project_subDescriptor", joinColumns = {
        @JoinColumn(name = "idProject", referencedColumnName = "idProject")}, inverseJoinColumns = {
        @JoinColumn(name = "idSubDescriptor", referencedColumnName = "idSubDescriptor")})
    @ManyToMany
    private Collection<SubDescriptor> subDescriptorCollection;

    @JoinTable(name = "project_descriptor", joinColumns = {
        @JoinColumn(name = "idProject", referencedColumnName = "idProject")}, inverseJoinColumns = {
        @JoinColumn(name = "idDescriptor", referencedColumnName = "idDescriptor")})
    @ManyToMany
    private Collection<Descriptor> descriptorCollection;

    @JoinColumn(name = "idCountry", referencedColumnName = "idCountry")
    @ManyToOne(optional = false)
    private Country idCountry;

    @JoinColumn(name = "idRepresentative", referencedColumnName = "idRepresentative")
    @ManyToOne(optional = false)
    private Representative idRepresentative;

    @JoinTable(name = "project_organization", joinColumns = {
        @JoinColumn(name = "idProject", referencedColumnName = "idProject")}, inverseJoinColumns = {
        @JoinColumn(name = "idOrganization", referencedColumnName = "idOrganization")})
    @ManyToMany
    private Collection<Organization> organizationCollection;

    @JoinTable(name = "project_beneficiary", joinColumns = {
        @JoinColumn(name = "idProject", referencedColumnName = "idProject")}, inverseJoinColumns = {
        @JoinColumn(name = "idBeneficiary", referencedColumnName = "idBeneficiary")})
    @ManyToMany
    private Collection<Beneficiary> beneficiaryCollection;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProject")
    private Collection<Participation> participationCollection;

    @JoinTable(name = "project_initiative", joinColumns = {
        @JoinColumn(name = "idProject", referencedColumnName = "idProject")}, inverseJoinColumns = {
        @JoinColumn(name = "idInitiativeType", referencedColumnName = "idInitiativeType")})
    @ManyToMany
    private Collection<InitiativeType> initiativeTypeCollection;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProject")
    private Collection<ProjectPopulation> projectPopulationCollection;

    @JoinTable(name = "population_participation", joinColumns = {
        @JoinColumn(name = "idProject", referencedColumnName = "idProject")}, inverseJoinColumns = {
        @JoinColumn(name = "idPopulationParticipationType", referencedColumnName = "idPopulationParticipationType")})
    @ManyToMany
    private Collection<PopulationParticipationType> populationParticipationTypeCollection;

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

    public String getCountryName() {
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
        return !((this.idProject == null && other.idProject != null)
                || (this.idProject != null && !this.idProject.equals(other.idProject)));
    }

    @Override
    public String toString() {
        return "fundabitat.retam.models.Project[ idProject=" + idProject + " ]";
    }

    public Collection<Organization> getOrganizationCollection() {
        return organizationCollection;
    }

    public void setOrganizationCollection(Collection<Organization> organizationCollection) {
        this.organizationCollection = organizationCollection;
    }

    public Collection<Beneficiary> getBeneficiaryCollection() {
        return beneficiaryCollection;
    }

    public void setBeneficiaryCollection(Collection<Beneficiary> beneficiaryCollection) {
        this.beneficiaryCollection = beneficiaryCollection;
    }

    public String getGeneralObjective() {
        return generalObjective;
    }

    public void setGeneralObjective(String generalObjective) {
        this.generalObjective = generalObjective;
    }

    public String getSpecificObjective() {
        return specificObjective;
    }

    public void setSpecificObjective(String specificObjective) {
        this.specificObjective = specificObjective;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Collection<InitiativeType> getInitiativeTypeCollection() {
        return initiativeTypeCollection;
    }

    public void setInitiativeTypeCollection(Collection<InitiativeType> initiativeTypeCollection) {
        this.initiativeTypeCollection = initiativeTypeCollection;
    }

    public Collection<ProjectPopulation> getProjectPopulationCollection() {
        return projectPopulationCollection;
    }

    public void setProjectPopulationCollection(Collection<ProjectPopulation> projectPopulationCollection) {
        this.projectPopulationCollection = projectPopulationCollection;
    }

    public Collection<PopulationParticipationType> getPopulationParticipationTypeCollection() {
        return populationParticipationTypeCollection;
    }

    public void setPopulationParticipationTypeCollection(Collection<PopulationParticipationType> populationParticipationTypeCollection) {
        this.populationParticipationTypeCollection = populationParticipationTypeCollection;
    }

    public Collection<Participation> getParticipationCollection() {
        return participationCollection;
    }

    public void setParticipationCollection(Collection<Participation> participationCollection) {
        this.participationCollection = participationCollection;
    }

}
