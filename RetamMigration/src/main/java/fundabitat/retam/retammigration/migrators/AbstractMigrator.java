/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author marcos
 * @param <E>
 */
public abstract class AbstractMigrator<E> {

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

    public List<E> read(Class<E> elemClass) throws FileNotFoundException {
        HeaderColumnNameMappingStrategy<E> strategy;
        strategy = new HeaderColumnNameMappingStrategy();
        strategy.setType(elemClass);
        CsvToBean<E> csvToBean = new CsvToBean();
        return csvToBean.parse(strategy, createReader());
    }

    public abstract void write(List<E> elements);

    protected CSVReader createReader() throws FileNotFoundException {
        return new CSVReader(new FileReader(filename), separator);
    }

    public abstract void run() throws FileNotFoundException;

}
