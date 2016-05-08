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
public class Representante {

    @CsvBind
    private int Cod_InstitucionEjecutora;

    @CsvBind
    private String Representante;

    @CsvBind
    private String RepresentanteInstitucion;

    @CsvBind
    private String NombreProfesional;

    @CsvBind
    private String CargoRepresentante;

    @CsvBind
    private String CargoRepresentanteInst;

    @CsvBind
    private String Profesion;

    public Representante() {
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

    public String getCargoRepresentante() {
        return CargoRepresentante;
    }

    public void setCargoRepresentante(String CargoRepresentante) {
        this.CargoRepresentante = CargoRepresentante;
    }

    public String getCargoRepresentanteInst() {
        return CargoRepresentanteInst;
    }

    public void setCargoRepresentanteInst(String CargoRepresentanteInst) {
        this.CargoRepresentanteInst = CargoRepresentanteInst;
    }

    public String getProfesion() {
        return Profesion;
    }

    public void setProfesion(String Profesion) {
        this.Profesion = Profesion;
    }

    @Override
    public String toString() {
        return "Representante{" + "Cod_InstitucionEjecutora=" + Cod_InstitucionEjecutora + ", Representante=" + Representante + ", RepresentanteInstitucion=" + RepresentanteInstitucion + ", NombreProfesional=" + NombreProfesional + ", CargoRepresentante=" + CargoRepresentante + ", CargoRepresentanteInst=" + CargoRepresentanteInst + ", Profesion=" + Profesion + '}';
    }

}
