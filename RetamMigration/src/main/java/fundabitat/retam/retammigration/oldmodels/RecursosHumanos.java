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
public class RecursosHumanos {

    @CsvBind
    private String Codigo;

    @CsvBind
    private String ResidentesAmazonia;

    @CsvBind
    private String MiembrosComunidadbenefi;

    @CsvBind
    private int NaciRemuT;

    @CsvBind
    private int NaciRemuA;

    @CsvBind
    private int NaciRemuO;

    @CsvBind
    private int NaciVolunT;

    @CsvBind
    private int NaciVolunA;

    @CsvBind
    private int NaciVolunO;

    @CsvBind
    private int ExtanRemuT;

    @CsvBind
    private int ExtranRemuA;

    @CsvBind
    private int ExtranRemuO;

    @CsvBind
    private int ExtanVolunT;

    @CsvBind
    private int ExtanVolunA;

    @CsvBind
    private int ExtanVolunO;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getResidentesAmazonia() {
        return ResidentesAmazonia;
    }

    public void setResidentesAmazonia(String ResidentesAmazonia) {
        this.ResidentesAmazonia = ResidentesAmazonia;
    }

    public String getMiembrosComunidadbenefi() {
        return MiembrosComunidadbenefi;
    }

    public void setMiembrosComunidadbenefi(String MiembrosComunidadbenefi) {
        this.MiembrosComunidadbenefi = MiembrosComunidadbenefi;
    }

    public int getNaciRemuT() {
        return NaciRemuT;
    }

    public void setNaciRemuT(int NaciRemuT) {
        this.NaciRemuT = NaciRemuT;
    }

    public int getNaciRemuA() {
        return NaciRemuA;
    }

    public void setNaciRemuA(int NaciRemuA) {
        this.NaciRemuA = NaciRemuA;
    }

    public int getNaciRemuO() {
        return NaciRemuO;
    }

    public void setNaciRemuO(int NaciRemuO) {
        this.NaciRemuO = NaciRemuO;
    }

    public int getNaciVolunT() {
        return NaciVolunT;
    }

    public void setNaciVolunT(int NaciVolunT) {
        this.NaciVolunT = NaciVolunT;
    }

    public int getNaciVolunA() {
        return NaciVolunA;
    }

    public void setNaciVolunA(int NaciVolunA) {
        this.NaciVolunA = NaciVolunA;
    }

    public int getNaciVolunO() {
        return NaciVolunO;
    }

    public void setNaciVolunO(int NaciVolunO) {
        this.NaciVolunO = NaciVolunO;
    }

    public int getExtanRemuT() {
        return ExtanRemuT;
    }

    public void setExtanRemuT(int ExtanRemuT) {
        this.ExtanRemuT = ExtanRemuT;
    }

    public int getExtranRemuA() {
        return ExtranRemuA;
    }

    public void setExtranRemuA(int ExtranRemuA) {
        this.ExtranRemuA = ExtranRemuA;
    }

    public int getExtranRemuO() {
        return ExtranRemuO;
    }

    public void setExtranRemuO(int ExtranRemuO) {
        this.ExtranRemuO = ExtranRemuO;
    }

    public int getExtanVolunT() {
        return ExtanVolunT;
    }

    public void setExtanVolunT(int ExtanVolunT) {
        this.ExtanVolunT = ExtanVolunT;
    }

    public int getExtanVolunA() {
        return ExtanVolunA;
    }

    public void setExtanVolunA(int ExtanVolunA) {
        this.ExtanVolunA = ExtanVolunA;
    }

    public int getExtanVolunO() {
        return ExtanVolunO;
    }

    public void setExtanVolunO(int ExtanVolunO) {
        this.ExtanVolunO = ExtanVolunO;
    }

    @Override
    public String toString() {
        return "RecursosHumanos{" + "Codigo=" + Codigo + ", ResidentesAmazonia=" + ResidentesAmazonia + ", MiembrosComunidadbenefi=" + MiembrosComunidadbenefi + ", NaciRemuT=" + NaciRemuT + ", NaciRemuA=" + NaciRemuA + ", NaciRemuO=" + NaciRemuO + ", NaciVolunT=" + NaciVolunT + ", NaciVolunA=" + NaciVolunA + ", NaciVolunO=" + NaciVolunO + ", ExtanRemuT=" + ExtanRemuT + ", ExtranRemuA=" + ExtranRemuA + ", ExtranRemuO=" + ExtranRemuO + ", ExtanVolunT=" + ExtanVolunT + ", ExtanVolunA=" + ExtanVolunA + ", ExtanVolunO=" + ExtanVolunO + '}';
    }

}
