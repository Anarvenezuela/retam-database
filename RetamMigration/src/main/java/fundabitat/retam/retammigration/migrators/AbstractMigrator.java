/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import fundabitat.retam.models.Country;
import fundabitat.retam.models.Project;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author marcos
 * @param <E>
 */
public abstract class AbstractMigrator<E> {

    protected static final String NOT_AVAILABLE = "No Disponible";

    protected String filename;
    protected EntityManager em;
    protected char separator;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    protected List<E> read(Class<E> elemClass) throws FileNotFoundException {
        HeaderColumnNameMappingStrategy<E> strategy;
        strategy = new HeaderColumnNameMappingStrategy();
        strategy.setType(elemClass);
        CsvToBean<E> csvToBean = new CsvToBean();
        return csvToBean.parse(strategy, createReader());
    }

    public void write(List<E> elements) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        process(elements);
        trans.commit();
    }

    protected abstract void process(List<E> elements);

    protected CSVReader createReader() throws FileNotFoundException {
        return new CSVReader(new FileReader(filename), separator);
    }

    public abstract void run() throws FileNotFoundException;

    /**
     * Gets the longest string form an array of strings.
     */
    protected String getLongest(String... strs) {
        String current = strs[0];
        current = (current == null ? "" : current.trim());

        for (int i = 1; i < strs.length; ++i) {

            String newStr = strs[i];

            if (newStr != null && newStr.length() > current.length()) {
                current = newStr.trim();
            }
        }

        return current;
    }

    /**
     * Gets a country from the list of countries.
     */
    protected Country getCountryByName(List<Country> countries, String name) {

        return countries.stream().
                filter(c -> c.getName().equals(name)).
                findFirst().get();
    }

    /**
     * Gets "Not available" from the list of countries.
     *
     * @param countries List of all countries in the DB.
     */
    protected Country getNotAvailableCountry(List<Country> countries) {
        return getCountryByName(countries, NOT_AVAILABLE);
    }

    /**
     * Gets a country from the list of countries.
     */
    protected Project getProjectByCode(List<Project> projects, String code) {

        return projects.stream().
                filter(c -> c.getCode().equals(code)).
                findFirst().get();
    }

}
