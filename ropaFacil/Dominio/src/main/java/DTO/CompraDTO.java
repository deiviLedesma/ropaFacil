/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public class CompraDTO {

    private int idCompra;
    private LocalDateTime fechaHora;
    private double total;
    private List<DetalleCompraDTO> detalleCompraDTOs;
    //proveedor a futuro 

    public CompraDTO() {
    }

    public CompraDTO(int idCompra, LocalDateTime fechaHora, double total, List<DetalleCompraDTO> detalleCompraDTOs) {
        this.idCompra = idCompra;
        this.fechaHora = fechaHora;
        this.total = total;
        this.detalleCompraDTOs = detalleCompraDTOs;
    }

    public CompraDTO(LocalDateTime fechaHora, double total, List<DetalleCompraDTO> detalleCompraDTOs) {
        this.fechaHora = fechaHora;
        this.total = total;
        this.detalleCompraDTOs = detalleCompraDTOs;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleCompraDTO> getDetalleCompraDTOs() {
        return detalleCompraDTOs;
    }

    public void setDetalleCompraDTOs(List<DetalleCompraDTO> detalleCompraDTOs) {
        this.detalleCompraDTOs = detalleCompraDTOs;
    }

    @Override
    public String toString() {
        return "CompraDTO{" + "idCompra=" + idCompra + ", fechaHora=" + fechaHora + ", total=" + total + ", detalleCompraDTOs=" + detalleCompraDTOs + '}';
    }

}
