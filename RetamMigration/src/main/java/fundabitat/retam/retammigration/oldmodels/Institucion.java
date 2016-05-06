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
public class Institucion {

    @CsvBind
    private int Cod_InstitucionEjecutora;

    @CsvBind
    private int Cod_InstitucionEjecutora2;

    @CsvBind
    private String Institucion;

    @CsvBind
    private String NombreInstitucion;

    @CsvBind
    private String Institucion2;

    @CsvBind
    private String Direccion;

    @CsvBind
    private String DireccionInstitucion;

    @CsvBind
    private String DireccionProfesional;

    @CsvBind
    private String Ciudad;

    @CsvBind
    private String CiudadInstitucion;

    @CsvBind
    private String CiudadProfesional;

    @CsvBind
    private String PaisInstitucion;

    @CsvBind
    private String PaisProfesional;

    @CsvBind
    private String Tlf1Proyecto;

    @CsvBind
    private String Telefono1;

    @CsvBind
    private String Telefono2;

    @CsvBind
    private String FaxProyecto;

    @CsvBind
    private String Fax1Proyecto;

    @CsvBind
    private String Fax2Proyecto;

    @CsvBind
    private String EmailProyecto;

    @CsvBind
    private String Email;

    @CsvBind
    private String PaginaWeb;

    @CsvBind
    private String ApartadoAereo;

    public int getCod_InstitucionEjecutora() {
        return Cod_InstitucionEjecutora;
    }

    public void setCod_InstitucionEjecutora(int Cod_InstitucionEjecutora) {
        this.Cod_InstitucionEjecutora = Cod_InstitucionEjecutora;
    }

    public int getCod_InstitucionEjecutora2() {
        return Cod_InstitucionEjecutora2;
    }

    public void setCod_InstitucionEjecutora2(int Cod_InstitucionEjecutora2) {
        this.Cod_InstitucionEjecutora2 = Cod_InstitucionEjecutora2;
    }

    public String getInstitucion() {
        return Institucion;
    }

    public void setInstitucion(String Institucion) {
        this.Institucion = Institucion;
    }

    public String getNombreInstitucion() {
        return NombreInstitucion;
    }

    public void setNombreInstitucion(String NombreInstitucion) {
        this.NombreInstitucion = NombreInstitucion;
    }

    public String getInstitucion2() {
        return Institucion2;
    }

    public void setInstitucion2(String Institucion2) {
        this.Institucion2 = Institucion2;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getDireccionInstitucion() {
        return DireccionInstitucion;
    }

    public void setDireccionInstitucion(String DireccionInstitucion) {
        this.DireccionInstitucion = DireccionInstitucion;
    }

    public String getDireccionProfesional() {
        return DireccionProfesional;
    }

    public void setDireccionProfesional(String DireccionProfesional) {
        this.DireccionProfesional = DireccionProfesional;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getCiudadInstitucion() {
        return CiudadInstitucion;
    }

    public void setCiudadInstitucion(String CiudadInstitucion) {
        this.CiudadInstitucion = CiudadInstitucion;
    }

    public String getCiudadProfesional() {
        return CiudadProfesional;
    }

    public void setCiudadProfesional(String CiudadProfesional) {
        this.CiudadProfesional = CiudadProfesional;
    }

    public String getPaisInstitucion() {
        return PaisInstitucion;
    }

    public void setPaisInstitucion(String PaisInstitucion) {
        this.PaisInstitucion = PaisInstitucion;
    }

    public String getPaisProfesional() {
        return PaisProfesional;
    }

    public void setPaisProfesional(String PaisProfesional) {
        this.PaisProfesional = PaisProfesional;
    }

    public String getTlf1Proyecto() {
        return Tlf1Proyecto;
    }

    public void setTlf1Proyecto(String Tlf1Proyecto) {
        this.Tlf1Proyecto = Tlf1Proyecto;
    }

    public String getTelefono1() {
        return Telefono1;
    }

    public void setTelefono1(String Telefono1) {
        this.Telefono1 = Telefono1;
    }

    public String getTelefono2() {
        return Telefono2;
    }

    public void setTelefono2(String Telefono2) {
        this.Telefono2 = Telefono2;
    }

    public String getFaxProyecto() {
        return FaxProyecto;
    }

    public void setFaxProyecto(String FaxProyecto) {
        this.FaxProyecto = FaxProyecto;
    }

    public String getFax1Proyecto() {
        return Fax1Proyecto;
    }

    public void setFax1Proyecto(String Fax1Proyecto) {
        this.Fax1Proyecto = Fax1Proyecto;
    }

    public String getFax2Proyecto() {
        return Fax2Proyecto;
    }

    public void setFax2Proyecto(String Fax2Proyecto) {
        this.Fax2Proyecto = Fax2Proyecto;
    }

    public String getEmailProyecto() {
        return EmailProyecto;
    }

    public void setEmailProyecto(String EmailProyecto) {
        this.EmailProyecto = EmailProyecto;
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

    public String getApartadoAereo() {
        return ApartadoAereo;
    }

    public void setApartadoAereo(String ApartadoAereo) {
        this.ApartadoAereo = ApartadoAereo;
    }

    @Override
    public String toString() {
        return "Institucion{" + "Cod_InstitucionEjecutora="
                + Cod_InstitucionEjecutora
                + ", Cod_InstitucionEjecutora2="
                + Cod_InstitucionEjecutora2
                + ", Institucion=" + Institucion
                + ", NombreInstitucion=" + NombreInstitucion
                + ", Institucion2=" + Institucion2
                + ", Direccion=" + Direccion
                + ", DireccionInstitucion=" + DireccionInstitucion
                + ", DireccionProfesional=" + DireccionProfesional
                + ", Ciudad=" + Ciudad
                + ", CiudadInstitucion=" + CiudadInstitucion
                + ", CiudadProfesional=" + CiudadProfesional
                + ", PaisInstitucion=" + PaisInstitucion
                + ", PaisProfesional=" + PaisProfesional + '}';
    }

}
