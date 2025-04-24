/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Enums.Categoria;
import Enums.Estado;
import Enums.Tipo;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoDTO {

    private int idProducto;
    private String nombre;
    private Tipo tipo;
    private Categoria categoria;
    private String color; 
    private double precioUnitario;
    private String caja;
    private Estado estado;
    private List<DetalleVentaDTO> detallesVenta;
    private List<DetalleCompraDTO> detallesCompra;

    public ProductoDTO() {
    }

    public ProductoDTO(int idProducto, String nombre, Tipo tipo, Categoria categoria, String color, double precioUnitario, String caja, Estado estado, List<DetalleVentaDTO> detallesVenta, List<DetalleCompraDTO> detallesCompra) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.color = color;
        this.precioUnitario = precioUnitario;
        this.caja = caja;
        this.estado = estado;
        this.detallesVenta = detallesVenta;
        this.detallesCompra = detallesCompra;
    }

    public ProductoDTO(String nombre, Tipo tipo, Categoria categoria, String color, double precioUnitario, String caja, Estado estado, List<DetalleVentaDTO> detallesVenta, List<DetalleCompraDTO> detallesCompra) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.color = color;
        this.precioUnitario = precioUnitario;
        this.caja = caja;
        this.estado = estado;
        this.detallesVenta = detallesVenta;
        this.detallesCompra = detallesCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<DetalleVentaDTO> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVentaDTO> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    public List<DetalleCompraDTO> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<DetalleCompraDTO> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", tipo=" + tipo + ", categoria=" + categoria + ", color=" + color + ", precioUnitario=" + precioUnitario + ", caja=" + caja + ", estado=" + estado + ", detallesVenta=" + detallesVenta + ", detallesCompra=" + detallesCompra + '}';
    }

}
