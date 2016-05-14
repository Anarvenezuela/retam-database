/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.oldmodels;

import com.opencsv.bean.CsvBind;

/**
 *
 * @author marcos
 */
public class Proyecto {

    @CsvBind
    private String Cod_Proyecto;

    @CsvBind
    private String NombreProyecto;

    @CsvBind
    private String Pais;

    @CsvBind
    private int Cod_InstitucionEjecutora;

    @CsvBind
    private String Representante;

    @CsvBind
    private String RepresentanteInstitucion;

    @CsvBind
    private String NombreProfesional;

    public Proyecto() {
    }

    public String getCod_Proyecto() {
        return Cod_Proyecto;
    }

    public void setCod_Proyecto(String Cod_Proyecto) {
        this.Cod_Proyecto = Cod_Proyecto;
    }

    public String getNombreProyecto() {
        return NombreProyecto;
    }

    public void setNombreProyecto(String NombreProyecto) {
        this.NombreProyecto = NombreProyecto;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public int getCod_InstitucionEjecutora() {
        return Cod_InstitucionEjecutora;
    }

    public void setCod_InstitucionEjecutora(int Cod_InstitucionEjecutora) {
        this.Cod_InstitucionEjecutora = Cod_InstitucionEjecutora;
    }

    public String getRepresentante() {
        return Representante;
    }

    public void setRepresentante(String Representante) {
        this.Representante = Representante;
    }

    public String getRepresentanteInstitucion() {
        return RepresentanteInstitucion;
    }

    public void setRepresentanteInstitucion(String RepresentanteInstitucion) {
        this.RepresentanteInstitucion = RepresentanteInstitucion;
    }

    public String getNombreProfesional() {
        return NombreProfesional;
    }

    public void setNombreProfesional(String NombreProfesional) {
        this.NombreProfesional = NombreProfesional;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "Cod_Proyecto=" + Cod_Proyecto
                + ", NombreProyecto=" + NombreProyecto
                + ", Pais=" + Pais
                + ", Cod_InstitucionEjecutora=" + Cod_InstitucionEjecutora
                + ", Representante=" + Representante
                + ", RepresentanteInstitucion=" + RepresentanteInstitucion
                + ", NombreProfesional=" + NombreProfesional + '}';
    }

}
