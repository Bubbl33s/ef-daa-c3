/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author maste
 */
public class DetalleReporteVentas {
    private String idReporteVentas;
    private String idVenta;
    
    public DetalleReporteVentas(String idReporteVentas, String idVenta) {
        this.idReporteVentas = idReporteVentas;
        this.idVenta = idVenta;
    }

    /**
     * @return the idReporteVentas
     */
    public String getIdReporteVentas() {
        return idReporteVentas;
    }

    /**
     * @param idReporteVentas the idReporteVentas to set
     */
    public void setIdReporteVentas(String idReporteVentas) {
        this.idReporteVentas = idReporteVentas;
    }

    /**
     * @return the idVenta
     */
    public String getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }
}
