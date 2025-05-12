/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Entidades.Compra;
import Entidades.DetalleCompra;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DTO de compra
 * @author SDavidLedesma
 */
public class CompraDTO {

    private Long id;
    private LocalDateTime fechaCompra;
    private Double total;
    private List<DetalleCompraDTO> detalleCompras;

    public CompraDTO() {
        this.detalleCompras = new ArrayList<>();
    }

    public CompraDTO(Long id, LocalDateTime fechaCompra, Double total, List<DetalleCompraDTO> detalleCompras) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.detalleCompras = detalleCompras;
    }

    // Constructor desde entidad
    public CompraDTO(Compra compra) {
        this.id = compra.getId();
        this.fechaCompra = compra.getFechaCompra();
        this.total = compra.getTotal();
        this.detalleCompras = new ArrayList<>();
        for (DetalleCompra dc : compra.getDetalleCompras()) {
            this.detalleCompras.add(new DetalleCompraDTO(dc));
        }
    }

    // Convertir a entidad
    public Compra toEntity() {
        Compra compra = new Compra();
        compra.setId(this.id);
        compra.setFechaCompra(this.fechaCompra);
        compra.setTotal(this.total);

        List<DetalleCompra> detalles = new ArrayList<>();
        for (DetalleCompraDTO dto : this.detalleCompras) {
            DetalleCompra entidad = dto.toEntity();
            entidad.setCompra(compra); // establecer la relación inversa
            detalles.add(entidad);
        }

        compra.setDetalleCompras(detalles);
        return compra;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetalleCompraDTO> getDetalleCompras() {
        return detalleCompras;
    }

    public void setDetalleCompras(List<DetalleCompraDTO> detalleCompras) {
        this.detalleCompras = detalleCompras;
    }

    @Override
    public String toString() {
        return "Compra ID: " + id + ", Total: $" + total;
    }
}
