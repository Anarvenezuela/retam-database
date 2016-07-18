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

}
