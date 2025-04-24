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
public class VentaDTO {

    private int idVenta;
    private LocalDateTime fechaHora;
    private double total;
    private List<DetalleVentaDTO> detalleVentaDTOs;

    public VentaDTO() {
    }

    public VentaDTO(LocalDateTime fechaHora, double total, List<DetalleVentaDTO> detalleVentaDTOs) {
        this.fechaHora = fechaHora;
        this.total = total;
        this.detalleVentaDTOs = detalleVentaDTOs;
    }

    public VentaDTO(int idVenta, LocalDateTime fechaHora, double total, List<DetalleVentaDTO> detalleVentaDTOs) {
        this.idVenta = idVenta;
        this.fechaHora = fechaHora;
        this.total = total;
        this.detalleVentaDTOs = detalleVentaDTOs;
    }
    
    

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    public List<DetalleVentaDTO> getDetalleVentaDTOs() {
        return detalleVentaDTOs;
    }

    public void setDetalleVentaDTOs(List<DetalleVentaDTO> detalleVentaDTOs) {
        this.detalleVentaDTOs = detalleVentaDTOs;
    }

    @Override
    public String toString() {
        return "VentaDTO{" + "idVenta=" + idVenta + ", fechaHora=" + fechaHora + ", total=" + total + ", detalleVentaDTOs=" + detalleVentaDTOs + '}';
    }

}
