/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Entidades.DetalleCompra;

/**
 * Clase DetallaeCompraDTO
 * @author SDavidLedesma
 */
public class DetalleCompraDTO {

    private Long id;
    private ProductoDTO producto;
    private TallaDTO talla;
    private int cantidad;
    private double precioUnitario;

    // constructor por omision
    public DetalleCompraDTO() {
    }

    // constructor que inicializa los parametros
    public DetalleCompraDTO(Long id, ProductoDTO producto, TallaDTO talla, int cantidad, double precioUnitario) {
        this.id = id;
        this.producto = producto;
        this.talla = talla;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Constructor desde la entidad
    public DetalleCompraDTO(DetalleCompra detalle) {
        this.id = detalle.getId();
        this.producto = new ProductoDTO(detalle.getProducto());
        this.talla = new TallaDTO(detalle.getTalla());
        this.cantidad = detalle.getCantidad();
        this.precioUnitario = detalle.getPrecioUnitario();
    }

    // Método para convertir a entidad
    public DetalleCompra toEntity() {
        DetalleCompra entity = new DetalleCompra();
        entity.setId(this.id);
        entity.setProducto(this.producto.toEntity());
        entity.setTalla(this.talla.toEntity());
        entity.setCantidad(this.cantidad);
        entity.setPrecioUnitario(this.precioUnitario);
        return entity;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return producto.getNombre() + " - Talla: " + talla.getDescripcion() + " - Cantidad: " + cantidad;
    }
}
