/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.oldmodels;

import com.opencsv.bean.CsvBind;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author marcos
 */
public class OtrasParticipantes {

    @CsvBind
    private int CodigoOtras;

    @CsvBind
    private String NombreInstitucion;

    @CsvBind
    private String Participacion;

    @CsvBind
    private String Codigo;

    public OtrasParticipantes() {
    }

    public OtrasParticipantes(int CodigoOtras, String NombreInstitucion,
            String Participacion, String Codigo) {
        this.CodigoOtras = CodigoOtras;
        this.NombreInstitucion = NombreInstitucion;
        this.Participacion = Participacion;
        this.Codigo = Codigo;
    }

    public int getCodigoOtras() {
        return CodigoOtras;
    }

    public void setCodigoOtras(int CodigoOtras) {
        this.CodigoOtras = CodigoOtras;
    }

    public String getNombreInstitucion() {
        return NombreInstitucion;
    }

    public void setNombreInstitucion(String NombreInstitucion) {
        this.NombreInstitucion = NombreInstitucion;
    }

    public String getParticipacion() {
        return Participacion;
    }

    public void setParticipacion(String Participacion) {
        this.Participacion = Participacion;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    /**
     * Separates the single participation string into letters. Also removes
     * trailing commas
     */
    public List<String> getListParticipationType() {
        String[] partTypesArr = Participacion.replaceAll(",$", "").split(",");
        return Arrays.asList(partTypesArr);
    }

    @Override
    public String toString() {
        return "OtrasParticipantes{" + "CodigoOtras=" + CodigoOtras
                + ", NombreInstitucion=" + NombreInstitucion
                + ", Participacion=" + Participacion
                + ", Codigo=" + Codigo + '}';
    }

}
