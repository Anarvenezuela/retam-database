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
public class ProyectoInstitucion {

    @CsvBind
    private int Cod_InstitucionEjecutora;

    @CsvBind
    private String Cod_Proyecto;

    public ProyectoInstitucion() {
    }

    public int getCod_InstitucionEjecutora() {
        return Cod_InstitucionEjecutora;
    }

    public void setCod_InstitucionEjecutora(int Cod_InstitucionEjecutora) {
        this.Cod_InstitucionEjecutora = Cod_InstitucionEjecutora;
    }

    public String getCod_Proyecto() {
        return Cod_Proyecto;
    }

    public void setCod_Proyecto(String Cod_Proyecto) {
        this.Cod_Proyecto = Cod_Proyecto;
    }

}
