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
public class Descriptores {

    @CsvBind
    private String Cod_Proyecto;

    @CsvBind
    private boolean Construccion;

    @CsvBind
    private boolean Materiales;

    @CsvBind
    private boolean SistConstruc;

    @CsvBind
    private boolean ElemConstruc;

    @CsvBind
    private boolean ProcConstruc;

    @CsvBind
    private boolean ProdConstruc;

    @CsvBind
    private boolean AguaPotable;

    @CsvBind
    private boolean AguaServidas;

    @CsvBind
    private boolean Basura;

    @CsvBind
    private boolean DispExcretas;

    @CsvBind
    private boolean BioEnerRenov;

    @CsvBind
    private boolean Saneamiento;

    @CsvBind
    private boolean EnergiasAlternas;

    @CsvBind
    private boolean RedesSistemas;

    @CsvBind
    private boolean SistemasInformacionC;

    @CsvBind
    private boolean RedesDistAgua;

    @CsvBind
    private boolean SistemasInformacionA;

    @CsvBind
    private boolean Eolica;

    @CsvBind
    private boolean Solar;

    @CsvBind
    private boolean Fotovoltaica;

    @CsvBind
    private boolean Biogas;

    @CsvBind
    private boolean Biodisel;

    @CsvBind
    private boolean Biocombustibles;

    @CsvBind
    private boolean Hidraulica;

    @CsvBind
    private boolean Geotermica;

    @CsvBind
    private boolean Biomasa;

    @CsvBind
    private boolean SistemasInformacionE;

    public Descriptores() {
    }

    public String getCod_Proyecto() {
        return Cod_Proyecto;
    }

    public void setCod_Proyecto(String Cod_Proyecto) {
        this.Cod_Proyecto = Cod_Proyecto;
    }

    public boolean isConstruccion() {
        return Construccion;
    }

    public void setConstruccion(boolean Construccion) {
        this.Construccion = Construccion;
    }

    public boolean isMateriales() {
        return Materiales;
    }

    public void setMateriales(boolean Materiales) {
        this.Materiales = Materiales;
    }

    public boolean isSistConstruc() {
        return SistConstruc;
    }

    public void setSistConstruc(boolean SistConstruc) {
        this.SistConstruc = SistConstruc;
    }

    public boolean isElemConstruc() {
        return ElemConstruc;
    }

    public void setElemConstruc(boolean ElemConstruc) {
        this.ElemConstruc = ElemConstruc;
    }

    public boolean isProcConstruc() {
        return ProcConstruc;
    }

    public void setProcConstruc(boolean ProcConstruc) {
        this.ProcConstruc = ProcConstruc;
    }

    public boolean isProdConstruc() {
        return ProdConstruc;
    }

    public void setProdConstruc(boolean ProdConstruc) {
        this.ProdConstruc = ProdConstruc;
    }

    public boolean isAguaPotable() {
        return AguaPotable;
    }

    public void setAguaPotable(boolean AguaPotable) {
        this.AguaPotable = AguaPotable;
    }

    public boolean isAguaServidas() {
        return AguaServidas;
    }

    public void setAguaServidas(boolean AguaServidas) {
        this.AguaServidas = AguaServidas;
    }

    public boolean isBasura() {
        return Basura;
    }

    public void setBasura(boolean Basura) {
        this.Basura = Basura;
    }

    public boolean isDispExcretas() {
        return DispExcretas;
    }

    public void setDispExcretas(boolean DispExcretas) {
        this.DispExcretas = DispExcretas;
    }

    public boolean isBioEnerRenov() {
        return BioEnerRenov;
    }

    public void setBioEnerRenov(boolean BioEnerRenov) {
        this.BioEnerRenov = BioEnerRenov;
    }

    public boolean isSaneamiento() {
        return Saneamiento;
    }

    public void setSaneamiento(boolean Saneamiento) {
        this.Saneamiento = Saneamiento;
    }

    public boolean isEnergiasAlternas() {
        return EnergiasAlternas;
    }

    public void setEnergiasAlternas(boolean EnergiasAlternas) {
        this.EnergiasAlternas = EnergiasAlternas;
    }

    public boolean isRedesSistemas() {
        return RedesSistemas;
    }

    public void setRedesSistemas(boolean RedesSistemas) {
        this.RedesSistemas = RedesSistemas;
    }

    public boolean isSistemasInformacionC() {
        return SistemasInformacionC;
    }

    public void setSistemasInformacionC(boolean SistemasInformacionC) {
        this.SistemasInformacionC = SistemasInformacionC;
    }

    public boolean isRedesDistAgua() {
        return RedesDistAgua;
    }

    public void setRedesDistAgua(boolean RedesDistAgua) {
        this.RedesDistAgua = RedesDistAgua;
    }

    public boolean isSistemasInformacionA() {
        return SistemasInformacionA;
    }

    public void setSistemasInformacionA(boolean SistemasInformacionA) {
        this.SistemasInformacionA = SistemasInformacionA;
    }

    public boolean isEolica() {
        return Eolica;
    }

    public void setEolica(boolean Eolica) {
        this.Eolica = Eolica;
    }

    public boolean isSolar() {
        return Solar;
    }

    public void setSolar(boolean Solar) {
        this.Solar = Solar;
    }

    public boolean isFotovoltaica() {
        return Fotovoltaica;
    }

    public void setFotovoltaica(boolean Fotovoltaica) {
        this.Fotovoltaica = Fotovoltaica;
    }

    public boolean isBiogas() {
        return Biogas;
    }

    public void setBiogas(boolean Biogas) {
        this.Biogas = Biogas;
    }

    public boolean isBiodisel() {
        return Biodisel;
    }

    public void setBiodisel(boolean Biodisel) {
        this.Biodisel = Biodisel;
    }

    public boolean isBiocombustibles() {
        return Biocombustibles;
    }

    public void setBiocombustibles(boolean Biocombustibles) {
        this.Biocombustibles = Biocombustibles;
    }

    public boolean isHidraulica() {
        return Hidraulica;
    }

    public void setHidraulica(boolean Hidraulica) {
        this.Hidraulica = Hidraulica;
    }

    public boolean isGeotermica() {
        return Geotermica;
    }

    public void setGeotermica(boolean Geotermica) {
        this.Geotermica = Geotermica;
    }

    public boolean isBiomasa() {
        return Biomasa;
    }

    public void setBiomasa(boolean Biomasa) {
        this.Biomasa = Biomasa;
    }

    public boolean isSistemasInformacionE() {
        return SistemasInformacionE;
    }

    public void setSistemasInformacionE(boolean SistemasInformacionE) {
        this.SistemasInformacionE = SistemasInformacionE;
    }

    @Override
    public String toString() {
        return "Descriptores{" + "Cod_Proyecto=" + Cod_Proyecto
                + ", Construccion=" + Construccion
                + ", Materiales=" + Materiales
                + ", SistConstruc=" + SistConstruc
                + ", ElemConstruc=" + ElemConstruc
                + ", ProcConstruc=" + ProcConstruc
                + ", ProdConstruc=" + ProdConstruc
                + ", AguaPotable=" + AguaPotable
                + ", AguaServidas=" + AguaServidas
                + ", Basura=" + Basura
                + ", DispExcretas=" + DispExcretas
                + ", BioEnerRenov=" + BioEnerRenov
                + ", Saneamiento=" + Saneamiento
                + ", EnergiasAlternas=" + EnergiasAlternas
                + ", RedesSistemas=" + RedesSistemas
                + ", SistemasInformacionC=" + SistemasInformacionC
                + ", RedesDistAgua=" + RedesDistAgua
                + ", SistemasInformacionA=" + SistemasInformacionA
                + ", Eolica=" + Eolica
                + ", Solar=" + Solar
                + ", Fotovoltaica=" + Fotovoltaica
                + ", Biogas=" + Biogas
                + ", Biodisel=" + Biodisel
                + ", Biocombustibles=" + Biocombustibles
                + ", Hidraulica=" + Hidraulica
                + ", Geotermica=" + Geotermica
                + ", Biomasa=" + Biomasa
                + ", SistemasInformacionE=" + SistemasInformacionE + '}';
    }

}
