/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author SDavidLedesma
 */
public class DetalleVentaDTO {

    private int idDetalleVenta;
    private VentaDTO venta;
    private TallaDTO talla;
    private int cantidad;
    private double precioUnitario;

    public DetalleVentaDTO() {
    }

    public DetalleVentaDTO(VentaDTO venta, TallaDTO talla, int cantidad, double precioUnitario) {
        this.venta = venta;
        this.talla = talla;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public DetalleVentaDTO(int idDetalleVenta, VentaDTO venta, TallaDTO talla, int cantidad, double precioUnitario) {
        this.idDetalleVenta = idDetalleVenta;
        this.venta = venta;
        this.talla = talla;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public VentaDTO getVenta() {
        return venta;
    }

    public void setVenta(VentaDTO venta) {
        this.venta = venta;
    }

    public TallaDTO getTalla() {
        return talla;
    }

    public void setTalla(TallaDTO talla) {
        this.talla = talla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "DetalleVentaDTO{" + "idDetalleVenta=" + idDetalleVenta + ", venta=" + venta + ", talla=" + talla + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + '}';
    }

}
