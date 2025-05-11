/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author SDavidLedesma
 */
@Entity
@Table(name = "talla")
public class Talla implements Serializable {

    @Id
    @Column(length = 5)
    private String codigo;

    @OneToMany(mappedBy = "talla", cascade = CascadeType.ALL)
    private List<DetalleCompra> detalleCompra;

    @OneToMany(mappedBy = "talla", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVentas;

    @Column(name = "descripcion")
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<DetalleCompra> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(List<DetalleCompra> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public Talla() {
        this.detalleCompra = new ArrayList<>();
        this.detalleVentas = new ArrayList<>();
    }

    public Talla(String codigo, List<DetalleCompra> detalleCompra, List<DetalleVenta> detalleVentas) {
        this.codigo = codigo;
        this.detalleCompra = detalleCompra;
        this.detalleVentas = detalleVentas;
    }

    public Talla(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Talla(String codigo, List<DetalleCompra> detalleCompra, List<DetalleVenta> detalleVentas, String descripcion) {
        this.codigo = codigo;
        this.detalleCompra = detalleCompra;
        this.detalleVentas = detalleVentas;
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Talla other = (Talla) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return "Talla{" + "codigo=" + codigo + ", descripcion=" + descripcion + '}';
    }

}
