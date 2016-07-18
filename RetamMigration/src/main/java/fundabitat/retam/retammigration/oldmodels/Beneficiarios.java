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
public class Beneficiarios {

    @CsvBind
    private String NombreInstitucion;

    @CsvBind
    private String Cod_Proyecto;

    @CsvBind
    private String Cod_Beneficiario;

    public String getNombreInstitucion() {
        return NombreInstitucion;
    }

    public void setNombreInstitucion(String NombreInstitucion) {
        this.NombreInstitucion = NombreInstitucion;
    }

    public String getCod_Proyecto() {
        return Cod_Proyecto;
    }

    public void setCod_Proyecto(String Cod_Proyecto) {
        this.Cod_Proyecto = Cod_Proyecto;
    }

    public String getCod_Beneficiario() {
        return Cod_Beneficiario;
    }

    public void setCod_Beneficiario(String Cod_Beneficiario) {
        this.Cod_Beneficiario = Cod_Beneficiario;
    }

    @Override
    public String toString() {
        return "Beneficiarios{" + "NombreInstitucion=" + NombreInstitucion + ", Cod_Proyecto=" + Cod_Proyecto + ", Cod_Beneficiario=" + Cod_Beneficiario + '}';
    }

}
