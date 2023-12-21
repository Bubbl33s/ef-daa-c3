/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author maste
 */
public class Venta {
    private String id;
    private LocalDate fecha;
    private String idCliente;
    private String idVendedor;
    private double subTotal;
    private double igv;
    private double descuento;
    private double total;
    
    public Venta(LocalDate fecha, String idCliente, String idVendedor,
            double subTotal, double descuento) {
        this.id = "";
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.subTotal = subTotal;
        calcularIgvDescuento(descuento);
        this.total = this.subTotal + this.igv - this.descuento;
    }
    
    public Venta(String id, Date fecha, String idCliente, String idVendedor,
            double subTotal, double igv, double descuento, double total) {
        this.id = id;
        
        if (fecha != null) {
            Instant instant = fecha.toInstant();
            this.fecha = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        } else {
            this.fecha = null;
        }
        
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.subTotal = subTotal;
        calcularIgvDescuento(descuento);
        this.total = this.subTotal + this.igv - this.descuento;
    }
    
    public Venta(String id, LocalDate fecha, String idCliente, String idVendedor,
            double subTotal, double igv, double descuento, double total) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.subTotal = subTotal;
        calcularIgvDescuento(descuento);
        this.total = this.subTotal + this.igv - this.descuento;
    }
    
    private void calcularIgvDescuento(double descuento) {
        this.igv = this.subTotal * 0.18;
        this.descuento = (this.subTotal + this.igv) * (descuento / 100);
    }
        
    @Override
    public String toString() {
        return "Venta [id=" + id + ", fecha=" + fecha + ", total=" + total + "]";
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idCliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the idVendedor
     */
    public String getIdVendedor() {
        return idVendedor;
    }

    /**
     * @param idVendedor the idVendedor to set
     */
    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    /**
     * @return the subTotal
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return the igv
     */
    public double getIgv() {
        return igv;
    }

    /**
     * @param igv the igv to set
     */
    public void setIgv(double igv) {
        this.igv = igv;
    }

    /**
     * @return the descuento
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }
}
