/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author maste
 */
public class DetalleReporteInventario {
    private String idReporteInventario;
    private String idProducto;
    private int cantidadActualProducto;
    
    public DetalleReporteInventario(String idReporteInventario, String idProducto, int cantidadActualProducto) {
        this.idReporteInventario = idReporteInventario;
        this.idProducto = idProducto;
        this.cantidadActualProducto = cantidadActualProducto;
    }

    /**
     * @return the idReporteInventario
     */
    public String getIdReporteInventario() {
        return idReporteInventario;
    }

    /**
     * @param idReporteInventario the idReporteInventario to set
     */
    public void setIdReporteInventario(String idReporteInventario) {
        this.idReporteInventario = idReporteInventario;
    }

    /**
     * @return the idProducto
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the cantidadActualProducto
     */
    public int getCantidadActualProducto() {
        return cantidadActualProducto;
    }

    /**
     * @param cantidadActualProducto the cantidadActualProducto to set
     */
    public void setCantidadActualProducto(int cantidadActualProducto) {
        this.cantidadActualProducto = cantidadActualProducto;
    }
    
    
}
