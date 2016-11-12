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
public class Intercambio {

    @CsvBind
    private int IdIntercambio;

    @CsvBind
    private String TipoIntercambio;

    @CsvBind
    private String Proyecto;

    @CsvBind
    private String Institucion;

    @CsvBind
    private String Direccion;

    @CsvBind
    private String Ciudad;

    @CsvBind
    private String Pais;

    @CsvBind
    private String Telefono;

    @CsvBind
    private String Fax;

    // Originally it was called E-mail
    @CsvBind
    private String Email;

    @CsvBind
    private String PaginaWeb;

    @CsvBind
    private String Codigo;

    @CsvBind
    private String Responsable;

    @CsvBind
    private boolean Contacto;

    @CsvBind
    private String TextoNoContacto;

    public int getIdIntercambio() {
        return IdIntercambio;
    }

    public void setIdIntercambio(int IdIntercambio) {
        this.IdIntercambio = IdIntercambio;
    }

    public String getTipoIntercambio() {
        return TipoIntercambio;
    }

    public void setTipoIntercambio(String TipoIntercambio) {
        this.TipoIntercambio = TipoIntercambio;
    }

    public String getProyecto() {
        return Proyecto;
    }

    public void setProyecto(String Proyecto) {
        this.Proyecto = Proyecto;
    }

    public String getInstitucion() {
        return Institucion;
    }

    public void setInstitucion(String Institucion) {
        this.Institucion = Institucion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPaginaWeb() {
        return PaginaWeb;
    }

    public void setPaginaWeb(String PaginaWeb) {
        this.PaginaWeb = PaginaWeb;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    public boolean isContacto() {
        return Contacto;
    }

    public void setContacto(boolean Contacto) {
        this.Contacto = Contacto;
    }

    public String getTextoNoContacto() {
        return TextoNoContacto;
    }

    public void setTextoNoContacto(String TextoNoContacto) {
        this.TextoNoContacto = TextoNoContacto;
    }

    @Override
    public String toString() {
        return "Intercambio{"
                + "IdIntercambio=" + IdIntercambio
                + ", TipoIntercambio=" + TipoIntercambio
                + ", Proyecto=" + Proyecto
                + ", Institucion=" + Institucion
                + ", Direccion=" + Direccion
                + ", Ciudad=" + Ciudad
                + ", Pais=" + Pais
                + ", Telefono=" + Telefono
                + ", Fax=" + Fax
                + ", Email=" + Email
                + ", PaginaWeb=" + PaginaWeb
                + ", Codigo=" + Codigo
                + ", Responsable=" + Responsable
                + ", Contacto=" + Contacto
                + ", TextoNoContacto=" + TextoNoContacto + '}';
    }

}
