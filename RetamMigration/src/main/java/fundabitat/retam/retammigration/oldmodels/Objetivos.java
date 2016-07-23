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
public class Objetivos {

    @CsvBind
    private String Codigo;

    @CsvBind
    private String ObjetivoGeneral;

    @CsvBind
    private String ObjetivosEspecificos;

    @CsvBind
    private String Metodologia;

    @CsvBind
    private String FechaInicio;

    @CsvBind
    private String Duracion;

    @CsvBind
    private boolean IE;

    @CsvBind
    private boolean CBeneficiada;

    @CsvBind
    private boolean ONG;

    @CsvBind
    private boolean AFinanciadora;

    @CsvBind
    private boolean IGubernamental;

    @CsvBind
    private boolean OReligiosa;

    @CsvBind
    private boolean NinosU;

    @CsvBind
    private boolean NinosR;

    @CsvBind
    private boolean NinosI;

    @CsvBind
    private boolean MujeresU;

    @CsvBind
    private boolean MujeresR;

    @CsvBind
    private boolean MujeresI;

    @CsvBind
    private boolean OtrosSU;

    @CsvBind
    private boolean OtrosSR;

    @CsvBind
    private boolean OtrosSI;

    @CsvBind
    private boolean Concepcion;

    @CsvBind
    private boolean Planificacion;

    @CsvBind
    private boolean Implementacion;

    @CsvBind
    private boolean Difusion;

    @CsvBind
    private boolean Capacitacion;

    @CsvBind
    private boolean Seguimiento;

    public Objetivos() {
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getObjetivoGeneral() {
        return ObjetivoGeneral;
    }

    public void setObjetivoGeneral(String ObjetivoGeneral) {
        this.ObjetivoGeneral = ObjetivoGeneral;
    }

    public String getObjetivosEspecificos() {
        return ObjetivosEspecificos;
    }

    public void setObjetivosEspecificos(String ObjetivosEspecificos) {
        this.ObjetivosEspecificos = ObjetivosEspecificos;
    }

    public String getMetodologia() {
        return Metodologia;
    }

    public void setMetodologia(String Metodologia) {
        this.Metodologia = Metodologia;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String Duracion) {
        this.Duracion = Duracion;
    }

    public boolean isIE() {
        return IE;
    }

    public void setIE(boolean IE) {
        this.IE = IE;
    }

    public boolean isCBeneficiada() {
        return CBeneficiada;
    }

    public void setCBeneficiada(boolean CBeneficiada) {
        this.CBeneficiada = CBeneficiada;
    }

    public boolean isONG() {
        return ONG;
    }

    public void setONG(boolean ONG) {
        this.ONG = ONG;
    }

    public boolean isAFinanciadora() {
        return AFinanciadora;
    }

    public void setAFinanciadora(boolean AFinanciadora) {
        this.AFinanciadora = AFinanciadora;
    }

    public boolean isIGubernamental() {
        return IGubernamental;
    }

    public void setIGubernamental(boolean IGubernamental) {
        this.IGubernamental = IGubernamental;
    }

    public boolean isOReligiosa() {
        return OReligiosa;
    }

    public void setOReligiosa(boolean OReligiosa) {
        this.OReligiosa = OReligiosa;
    }

    public boolean isNinosU() {
        return NinosU;
    }

    public void setNinosU(boolean NinosU) {
        this.NinosU = NinosU;
    }

    public boolean isNinosR() {
        return NinosR;
    }

    public void setNinosR(boolean NinosR) {
        this.NinosR = NinosR;
    }

    public boolean isNinosI() {
        return NinosI;
    }

    public void setNinosI(boolean NinosI) {
        this.NinosI = NinosI;
    }

    public boolean isMujeresU() {
        return MujeresU;
    }

    public void setMujeresU(boolean MujeresU) {
        this.MujeresU = MujeresU;
    }

    public boolean isMujeresR() {
        return MujeresR;
    }

    public void setMujeresR(boolean MujeresR) {
        this.MujeresR = MujeresR;
    }

    public boolean isMujeresI() {
        return MujeresI;
    }

    public void setMujeresI(boolean MujeresI) {
        this.MujeresI = MujeresI;
    }

    public boolean isOtrosSU() {
        return OtrosSU;
    }

    public void setOtrosSU(boolean OtrosSU) {
        this.OtrosSU = OtrosSU;
    }

    public boolean isOtrosSR() {
        return OtrosSR;
    }

    public void setOtrosSR(boolean OtrosSR) {
        this.OtrosSR = OtrosSR;
    }

    public boolean isOtrosSI() {
        return OtrosSI;
    }

    public void setOtrosSI(boolean OtrosSI) {
        this.OtrosSI = OtrosSI;
    }

    public boolean isConcepcion() {
        return Concepcion;
    }

    public void setConcepcion(boolean Concepcion) {
        this.Concepcion = Concepcion;
    }

    public boolean isPlanificacion() {
        return Planificacion;
    }

    public void setPlanificacion(boolean Planificacion) {
        this.Planificacion = Planificacion;
    }

    public boolean isImplementacion() {
        return Implementacion;
    }

    public void setImplementacion(boolean Implementacion) {
        this.Implementacion = Implementacion;
    }

    public boolean isDifusion() {
        return Difusion;
    }

    public void setDifusion(boolean Difusion) {
        this.Difusion = Difusion;
    }

    public boolean isCapacitacion() {
        return Capacitacion;
    }

    public void setCapacitacion(boolean Capacitacion) {
        this.Capacitacion = Capacitacion;
    }

    public boolean isSeguimiento() {
        return Seguimiento;
    }

    public void setSeguimiento(boolean Seguimiento) {
        this.Seguimiento = Seguimiento;
    }

    @Override
    public String toString() {
        return "Objetivos{" + "Codigo=" + Codigo
                + ", ObjetivoGeneral=" + ObjetivoGeneral
                + ", ObjetivosEspecificos=" + ObjetivosEspecificos
                + ", Metodologia=" + Metodologia
                + ", FechaInicio=" + FechaInicio
                + ", Duracion=" + Duracion
                + ", IE=" + IE
                + ", CBeneficiada=" + CBeneficiada
                + ", ONG=" + ONG
                + ", AFinanciadora=" + AFinanciadora
                + ", IGubernamental=" + IGubernamental
                + ", OReligiosa=" + OReligiosa
                + ", NinosU=" + NinosU
                + ", NinosR=" + NinosR
                + ", NinosI=" + NinosI
                + ", MujeresU=" + MujeresU
                + ", MujeresR=" + MujeresR
                + ", MujeresI=" + MujeresI
                + ", OtrosSU=" + OtrosSU
                + ", OtrosSR=" + OtrosSR
                + ", OtrosSI=" + OtrosSI
                + ", Concepcion=" + Concepcion
                + ", Planificacion=" + Planificacion
                + ", Implementacion=" + Implementacion
                + ", Difusion=" + Difusion
                + ", Capacitacion=" + Capacitacion
                + ", Seguimiento=" + Seguimiento + '}';
    }

}
