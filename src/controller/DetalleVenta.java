/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.DetalleVentaDB;

/**
 *
 * @author maste
 */
public class DetalleVenta {
    private String idVenta;
    private String idProducto;
    private double precioUnitarioActual;
    private int cantidadVendida;
    private double totalProducto;
    
    public DetalleVenta(String idVenta, String idProducto, double precioUnitarioActual,
            int cantidadVendida, double totalProducto) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.precioUnitarioActual = precioUnitarioActual;
        this.cantidadVendida = cantidadVendida;
        this.totalProducto = totalProducto;
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
     * @return the precioUnitarioActual
     */
    public double getPrecioUnitarioActual() {
        return precioUnitarioActual;
    }

    /**
     * @param precioUnitarioActual the precioUnitarioActual to set
     */
    public void setPrecioUnitarioActual(double precioUnitarioActual) {
        this.precioUnitarioActual = precioUnitarioActual;
    }

    /**
     * @return the cantidadVendida
     */
    public int getCantidadVendida() {
        return cantidadVendida;
    }

    /**
     * @param cantidadVendida the cantidadVendida to set
     */
    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    /**
     * @return the totalProducto
     */
    public double getTotalProducto() {
        return totalProducto;
    }

    /**
     * @param totalProducto the totalProducto to set
     */
    public void setTotalProducto(double totalProducto) {
        this.totalProducto = totalProducto;
    }
}
