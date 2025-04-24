/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public class TallaDTO {

    private String codigo;
    private String descipcion;
    private List<DetalleVentaDTO> detalleVentaTalla;
    private List<DetalleCompraDTO> detalleCompraTalla;

    public TallaDTO() {
    }

    public TallaDTO(String codigo, String descipcion, List<DetalleVentaDTO> detalleVentaTalla, List<DetalleCompraDTO> detalleCompraTalla) {
        this.codigo = codigo;
        this.descipcion = descipcion;
        this.detalleVentaTalla = detalleVentaTalla;
        this.detalleCompraTalla = detalleCompraTalla;
    }

    public TallaDTO(String descipcion, List<DetalleVentaDTO> detalleVentaTalla, List<DetalleCompraDTO> detalleCompraTalla) {
        this.descipcion = descipcion;
        this.detalleVentaTalla = detalleVentaTalla;
        this.detalleCompraTalla = detalleCompraTalla;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
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
        return "TallaDTO{" + "codigo=" + codigo + ", descipcion=" + descipcion + ", detalleVentaTalla=" + detalleVentaTalla + ", detalleCompraTalla=" + detalleCompraTalla + '}';
    }

}
