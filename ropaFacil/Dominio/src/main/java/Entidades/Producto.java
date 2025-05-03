/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author SDavidLedesma
 */
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_producto", nullable = false, length = 150)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_producto", nullable = false)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "color", nullable = false)
    private Color color;

    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;

    @Column(name = "caja", nullable = false)
    private String caja;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVentas;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleCompra> detalleCompras;

    public Producto() {
    }

    public Producto(String nombre, Tipo tipo, Categoria categoria, Color color, double precioUnitario, String caja, Estado estado, List<DetalleVenta> detalleVentas, List<DetalleCompra> detalleCompras) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.color = color;
        this.precioUnitario = precioUnitario;
        this.caja = caja;
        this.estado = estado;
        this.detalleVentas = detalleVentas;
        this.detalleCompras = detalleCompras;
    }

    public Producto(Long id, String nombre, Tipo tipo, Categoria categoria, Color color, double precioUnitario, String caja, Estado estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.color = color;
        this.precioUnitario = precioUnitario;
        this.caja = caja;
        this.estado = estado;
    }

    public Producto(Long id, List<DetalleVenta> detalleVentas, List<DetalleCompra> detalleCompras) {
        this.id = id;
        this.detalleVentas = detalleVentas;
        this.detalleCompras = detalleCompras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
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

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public List<DetalleCompra> getDetalleCompras() {
        return detalleCompras;
    }

    public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
        this.detalleCompras = detalleCompras;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", categoria=" + categoria + ", color=" + color + ", precioUnitario=" + precioUnitario + ", caja=" + caja + ", estado=" + estado + ", detalleVentas=" + detalleVentas + ", detalleCompras=" + detalleCompras + '}';
    }

}
