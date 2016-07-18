/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Beneficiary;
import fundabitat.retam.models.Project;
import fundabitat.retam.retammigration.oldmodels.Beneficiarios;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class BeneficiaryMigrator extends AbstractMigrator<Beneficiarios> {

    public BeneficiaryMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Beneficiarios> elements) {

        List<Beneficiary> savedBenef = saveBeneficiaries(elements);

        associateWithProjects(elements, savedBenef);

    }

    private void associateWithProjects(List<Beneficiarios> elements, List<Beneficiary> savedBenef) {

        Query findProjects = em.createNamedQuery("Project.findAll");
        List<Project> projects = findProjects.getResultList();

        for (Beneficiarios b : elements) {

            String projectCode = b.getCod_Proyecto();
            Project project = projects.stream().
                    filter(p -> p.getCode().equals(projectCode)).
                    findFirst().get();

            String benefCode = b.getCod_Beneficiario();
            Beneficiary benef = savedBenef.stream().
                    filter(be -> be.getCode().equals(benefCode)).
                    findFirst().get();

            project.getBeneficiaryCollection().add(benef);
        }
    }

    private List<Beneficiary> saveBeneficiaries(List<Beneficiarios> beneficiaries) {

        List<Beneficiary> savedBenef = new ArrayList();

        //Initialize first element
        String currentCode = beneficiaries.get(0).getCod_Beneficiario();
        String longestName = "";

        for (Beneficiarios b : beneficiaries) {

            // We changed id and collected all its info, store it.
            if (!b.getCod_Beneficiario().equals(currentCode)) {
                Beneficiary beneficiary = new Beneficiary(currentCode, longestName);
                em.persist(beneficiary);
                savedBenef.add(beneficiary);
                longestName = "";
                currentCode = b.getCod_Beneficiario();
            }

            longestName = getLongest(longestName, b.getNombreInstitucion());
        }

        //Insert last
        Beneficiary beneficiary = new Beneficiary(currentCode, longestName);
        em.persist(beneficiary);
        savedBenef.add(beneficiary);

        return savedBenef;
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Beneficiarios> list = read(Beneficiarios.class);
        write(list);
    }

}
