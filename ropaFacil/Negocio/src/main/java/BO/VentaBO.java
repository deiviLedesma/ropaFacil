package BO;

import DTO.VentaDTO;
import DTO.DetalleVentaDTO;
import Entidades.Venta;
import Entidades.DetalleVenta;
import Entidades.Producto;
import Entidades.Talla;
import Persistencia.PersistenciaException;
import DAO.VentaDAO;
import DAO.ProductoDAO;
import DAO.TallaDAO;
import Enums.Estado;
import Exception.NegocioException;
import Interfaces.IVentaBO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author SDavidLedesma
 */
public class VentaBO implements IVentaBO {

    private final VentaDAO ventaDAO;
    private final ProductoDAO productoDAO;
    private final TallaDAO tallaDAO;

    public VentaBO() {
        this.ventaDAO = new VentaDAO();
        this.productoDAO = ProductoDAO.getInstanceDAO();
        this.tallaDAO = new TallaDAO();
    }

    @Override
    public VentaDTO registrarVenta(VentaDTO dto)
            throws NegocioException, PersistenciaException {

        if (dto.getDetalleVentas().isEmpty()) {
            throw new NegocioException("La venta debe contener al menos un detalle.");
        }
        Venta v = new Venta();
        v.setFechaHora(LocalDateTime.now());
        v.setTotal(dto.getTotal());

        List<DetalleVenta> detallesEnt = new ArrayList<>();
        for (DetalleVentaDTO dDTO : dto.getDetalleVentas()) {
            // validar existencia y stock
            Producto p = productoDAO.buscarPorId(dDTO.getProducto().getIdProducto());
            if (p == null || p.getEstado() != Estado.ACTIVO) {
                throw new NegocioException("Producto no válido: " + dDTO.getProducto().getNombre());
            }
            Talla t = tallaDAO.buscarPorCodigo(dDTO.getTalla().getCodigo());
            if (t == null) {
                throw new NegocioException("Talla no válida: " + dDTO.getTalla().getCodigo());
            }
            // verificar stock
            long disp = p.getDetalleCompras().stream()
                    .filter(dc -> dc.getTalla().equals(t))
                    .mapToLong(dc -> dc.getCantidad())
                    .sum();
            if (dDTO.getCantidad() > disp) {
                throw new NegocioException("Stock insuficiente para " + p.getNombre());
            }
            // crear detalle
            DetalleVenta dv = dDTO.toEntity();
            dv.setVenta(v);
            detallesEnt.add(dv);
            // descontar stock (lógica en memoria)
            p.getDetalleCompras().stream()
                    .filter(dc -> dc.getTalla().equals(t))
                    .findFirst()
                    .ifPresent(dc -> dc.setCantidad(dc.getCantidad() - dDTO.getCantidad()));
            productoDAO.actualizar(p);
        }
        v.setDetalleVentas(detallesEnt);
        
        ventaDAO.insertar(v);
        
        return new VentaDTO(v);
    }

    @Override
    public List<VentaDTO> listarVentas(String color, String talla,
            Integer idProducto,
            LocalDateTime desde, LocalDateTime hasta)
            throws PersistenciaException {
        List<Venta> ventas = ventaDAO.buscarTodas();
        List<VentaDTO> out = new ArrayList<>();
        for (Venta v : ventas) {
            boolean ok = true;
            if (desde != null && v.getFechaHora().isBefore(desde)) {
                ok = false;
            }
            if (hasta != null && v.getFechaHora().isAfter(hasta)) {
                ok = false;
            }
            if (idProducto != null) {
                ok &= v.getDetalleVentas().stream()
                        .anyMatch(d -> Objects.equals(d.getProducto().getId(), idProducto));
            }
            if (color != null) {
                ok &= v.getDetalleVentas().stream()
                        .anyMatch(d -> d.getProducto().getColor().name().equals(color));
            }
            if (talla != null) {
                ok &= v.getDetalleVentas().stream()
                        .anyMatch(d -> d.getTalla().getCodigo().equals(talla));
            }
            if (ok) {
                out.add(new VentaDTO(v));
            }
        }
        return out;
    }
}
