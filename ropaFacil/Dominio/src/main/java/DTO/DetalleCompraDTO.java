/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author SDavidLedesma
 */
public class DetalleCompraDTO {

    private int idDetalle;
    private CompraDTO compra;
    private ProductoDTO producto;
    private TallaDTO talla;
    private int cantidad;
    private double precioUnitario;

    public DetalleCompraDTO() {
    }

    public DetalleCompraDTO(int idDetalle, CompraDTO compra, ProductoDTO producto, TallaDTO talla, int cantidad, double precioUnitario) {
        this.idDetalle = idDetalle;
        this.compra = compra;
        this.producto = producto;
        this.talla = talla;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public DetalleCompraDTO(CompraDTO compra, ProductoDTO producto, TallaDTO talla, int cantidad, double precioUnitario) {
        this.compra = compra;
        this.producto = producto;
        this.talla = talla;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public CompraDTO getCompra() {
        return compra;
    }

    public void setCompra(CompraDTO compra) {
        this.compra = compra;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
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
        return "DetalleCompraDTO{" + "idDetalle=" + idDetalle + ", compra=" + compra + ", producto=" + producto + ", talla=" + talla + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + '}';
    }

}
