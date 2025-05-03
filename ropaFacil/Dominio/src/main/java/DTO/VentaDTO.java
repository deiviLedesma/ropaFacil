package DTO;

import Entidades.Venta;
import Entidades.DetalleVenta;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VentaDTO {

    private Long id;
    private LocalDateTime fechaVenta;
    private Double total;
    private List<DetalleVentaDTO> detalleVentas;

    public VentaDTO() {
        this.detalleVentas = new ArrayList<>();
    }

    public VentaDTO(Long id, LocalDateTime fechaVenta, Double total, List<DetalleVentaDTO> detalleVentas) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.detalleVentas = detalleVentas;
    }

    // Constructor desde entidad
    public VentaDTO(Venta venta) {
        this.id = venta.getId();
        this.fechaVenta = venta.getFechaHora();
        this.total = venta.getTotal();
        this.detalleVentas = new ArrayList<>();
        for (DetalleVenta dv : venta.getDetalleVentas()) {
            this.detalleVentas.add(new DetalleVentaDTO(dv));
        }
    }

    // Convertir a entidad
    public Venta toEntity() {
        Venta venta = new Venta();
        venta.setId(this.id);
        venta.setFechaHora(this.fechaVenta);
        venta.setTotal(this.total);

        List<DetalleVenta> detalles = new ArrayList<>();
        for (DetalleVentaDTO dto : this.detalleVentas) {
            DetalleVenta entidad = dto.toEntity();
            entidad.setVenta(venta); // establecer la relación inversa
            detalles.add(entidad);
        }

        venta.setDetalleVentas(detalles);
        return venta;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetalleVentaDTO> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVentaDTO> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    @Override
    public String toString() {
        return "Venta ID: " + id + ", Total: $" + total;
    }
}
