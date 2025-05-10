/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Entidades.Talla;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public class TallaDTO {

    private String codigo;
    private String descripcion;
    private List<DetalleVentaDTO> detalleVentaTalla;
    private List<DetalleCompraDTO> detalleCompraTalla;

    public TallaDTO() {
    }

    public TallaDTO(String codigo, String descripcion, List<DetalleVentaDTO> detalleVentaTalla, List<DetalleCompraDTO> detalleCompraTalla) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.detalleVentaTalla = detalleVentaTalla;
        this.detalleCompraTalla = detalleCompraTalla;
    }

    public TallaDTO(String descripcion, List<DetalleVentaDTO> detalleVentaTalla, List<DetalleCompraDTO> detalleCompraTalla) {
        this.descripcion = descripcion;
        this.detalleVentaTalla = detalleVentaTalla;
        this.detalleCompraTalla = detalleCompraTalla;
    }

    // Constructor desde la entidad
    public TallaDTO(Talla talla) {
        this.codigo = talla.getCodigo();
        this.descripcion = talla.getDescripcion();
        this.detalleVentaTalla = new ArrayList<>();
        this.detalleCompraTalla = new ArrayList<>();
    }

    // Método para convertir a entidad
    public Talla toEntity() {
        Talla talla = new Talla();
        talla.setCodigo(this.codigo);
        talla.setDescripcion(this.descripcion);

        // Asegura que las listas estén inicializadas (por si la entidad no lo hace)
        if (talla.getDetalleCompra() == null) {
            talla.setDetalleCompra(new ArrayList<>());
        }
        if (talla.getDetalleVentas() == null) {
            talla.setDetalleVentas(new ArrayList<>());
        }

        return talla;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DetalleVentaDTO> getDetalleVentaTalla() {
        return detalleVentaTalla;
    }

    public void setDetalleVentaTalla(List<DetalleVentaDTO> detalleVentaTalla) {
        this.detalleVentaTalla = detalleVentaTalla;
    }

    public List<DetalleCompraDTO> getDetalleCompraTalla() {
        return detalleCompraTalla;
    }

    public void setDetalleCompraTalla(List<DetalleCompraDTO> detalleCompraTalla) {
        this.detalleCompraTalla = detalleCompraTalla;
    }

    @Override
    public String toString() {
        return descripcion; // Esto es útil para JComboBox
    }
}
