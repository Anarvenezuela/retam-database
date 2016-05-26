/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Descriptor;
import fundabitat.retam.models.Project;
import fundabitat.retam.models.SubDescriptor;
import fundabitat.retam.retammigration.oldmodels.Descriptores;
import java.io.FileNotFoundException;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class DescriptorMigrator extends AbstractMigrator<Descriptores> {

    public DescriptorMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Descriptores> elements) {

        Query findDescriptors = em.createNamedQuery("Descriptor.findAll");
        List<Descriptor> descriptors = findDescriptors.getResultList();

        Query findSubDescriptors = em.createNamedQuery("SubDescriptor.findAll");
        List<SubDescriptor> subdescriptors = findSubDescriptors.getResultList();

        Query findProjects = em.createNamedQuery("Project.findAll");
        List<Project> projects = findProjects.getResultList();

        for (Descriptores d : elements) {

            Project p = getProjectByCode(projects, d.getCod_Proyecto());

            addConstructionDescriptors(d, p, descriptors, subdescriptors);
            addBasicSanitationDescriptors(d, p, descriptors, subdescriptors);
            addAltEnergiesDescriptors(d, p, descriptors, subdescriptors);
            addNetworkDescriptor(d, p, descriptors);

            em.persist(p);
        }
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Descriptores> list = read(Descriptores.class);
        write(list);
    }

    private void addConstructionDescriptors(Descriptores originalDesc, Project p,
            List<Descriptor> descriptors, List<SubDescriptor> subdescriptors) {

        if (originalDesc.isConstruccion()) {
            addDescriptor(p, descriptors, "Construcción");

            if (originalDesc.isMateriales()) {
                addSubDescriptor(p, subdescriptors, "Materiales");
            }

            if (originalDesc.isSistConstruc()) {
                addSubDescriptor(p, subdescriptors, "Sistemas Constructivos");
            }

            if (originalDesc.isElemConstruc()) {
                addSubDescriptor(p, subdescriptors, "Elementos Constructivos");
            }

            if (originalDesc.isProcConstruc()) {
                addSubDescriptor(p, subdescriptors, "Procesos Constructivos");
            }

            if (originalDesc.isProdConstruc()) {
                addSubDescriptor(p, subdescriptors, "Productos Constructivos");
            }

            if (originalDesc.isSistemasInformacionC()) {
                addSubDescriptor(p, subdescriptors, "I-Sistemas de Información");
            }
        }
    }

    private void addBasicSanitationDescriptors(Descriptores originalDesc, Project p,
            List<Descriptor> descriptors, List<SubDescriptor> subdescriptors) {

        if (originalDesc.isSaneamiento()) {
            addDescriptor(p, descriptors, "Saneamiento básico");

            if (originalDesc.isAguaPotable()) {
                addSubDescriptor(p, subdescriptors, "Agua Potable");
            }

            if (originalDesc.isAguaServidas()) {
                addSubDescriptor(p, subdescriptors, "Agua Servidas");
            }

            // WTF? Why would they do this?
            if (originalDesc.isRedesDistAgua()) {
                addSubDescriptor(p, subdescriptors, "Biogas y Energías Renovables");
            }

            if (originalDesc.isBasura()) {
                addSubDescriptor(p, subdescriptors, "Basura");
            }

            if (originalDesc.isDispExcretas()) {
                addSubDescriptor(p, subdescriptors, "Disposición de Excretas");
            }

            if (originalDesc.isSistemasInformacionA()) {
                addSubDescriptor(p, subdescriptors, "II-Sistemas de Información");
            }
        }
    }

    private void addAltEnergiesDescriptors(Descriptores originalDesc, Project p,
            List<Descriptor> descriptors, List<SubDescriptor> subdescriptors) {

        if (originalDesc.isEnergiasAlternas()) {
            addDescriptor(p, descriptors, "Energías alternas");

            if (originalDesc.isEolica()) {
                addSubDescriptor(p, subdescriptors, "Eólica");
            }

            if (originalDesc.isSolar()) {
                addSubDescriptor(p, subdescriptors, "Solar");
            }

            if (originalDesc.isFotovoltaica()) {
                addSubDescriptor(p, subdescriptors, "Fotovoltálica");
            }

            if (originalDesc.isBiogas()) {
                addSubDescriptor(p, subdescriptors, "Biogas");
            }

            if (originalDesc.isBiodisel()) {
                addSubDescriptor(p, subdescriptors, "Biodisel");
            }

            if (originalDesc.isBiocombustibles()) {
                addSubDescriptor(p, subdescriptors, "Biocombustible");
            }

            if (originalDesc.isHidraulica()) {
                addSubDescriptor(p, subdescriptors, "Hiráulica");
            }

            if (originalDesc.isBioEnerRenov()) {
                addSubDescriptor(p, subdescriptors, "Renovables");
            }

            if (originalDesc.isGeotermica()) {
                addSubDescriptor(p, subdescriptors, "Geotérmica");
            }

            if (originalDesc.isBiomasa()) {
                addSubDescriptor(p, subdescriptors, "Biomasa");
            }

            if (originalDesc.isSistemasInformacionE()) {
                addSubDescriptor(p, subdescriptors, "III-Sistemas de Información");
            }
        }
    }

    private void addNetworkDescriptor(Descriptores originalDesc, Project p,
            List<Descriptor> descriptors) {

        if (originalDesc.isRedesSistemas()) {
            addDescriptor(p, descriptors, "Redes y Sistemas de Información");
        }
    }

    private void addDescriptor(Project p, List<Descriptor> descriptors,
            String name) {
        Descriptor desc = getDescriptorByName(descriptors, name);
        p.getDescriptorCollection().add(desc);
    }

    private void addSubDescriptor(Project p, List<SubDescriptor> subDescriptors,
            String name) {
        SubDescriptor subDesc = getSubDescriptorByName(subDescriptors, name);
        p.getSubDescriptorCollection().add(subDesc);
    }

    private Descriptor getDescriptorByName(List<Descriptor> descriptors,
            String name) {
        return descriptors.stream().filter(d -> d.getName().equals(name))
                .findFirst().get();
    }

    private SubDescriptor getSubDescriptorByName(List<SubDescriptor> descriptors,
            String name) {
        return descriptors.stream().filter(d -> d.getName().equals(name))
                .findFirst().get();
    }

    private Project getProjectByCode(List<Project> projects, String code) {
        return projects.stream().filter(p -> p.getCode().equals(code.toUpperCase()))
                .findFirst().get();
    }

}
