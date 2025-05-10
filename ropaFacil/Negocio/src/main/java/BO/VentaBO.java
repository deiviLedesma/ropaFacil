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

        if (dto == null || dto.getDetalleVentas().isEmpty()) {
            throw new NegocioException("La venta debe contener al menos un detalle.");
        }

        Venta venta = new Venta();
        venta.setFechaHora(dto.getFechaVenta() != null ? dto.getFechaVenta() : LocalDateTime.now());
        venta.setTotal(dto.getTotal());

        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaDTO detDTO : dto.getDetalleVentas()) {
            if (detDTO.getCantidad() <= 0) {
                throw new NegocioException("La cantidad debe ser mayor que 0.");
            }

            Producto producto = productoDAO.buscarPorId(detDTO.getProducto().getIdProducto());
            if (producto == null || producto.getEstado() != Estado.ACTIVO) {
                throw new NegocioException("Producto no válido: " + detDTO.getProducto().getNombre());
            }

            Talla talla = tallaDAO.buscarPorCodigo(detDTO.getTalla().getCodigo());
            if (talla == null) {
                throw new NegocioException("Talla no válida: " + detDTO.getTalla().getCodigo());
            }

            // Verificar stock disponible
            long disponible = producto.getDetalleCompras().stream()
                    .filter(dc -> dc.getTalla().equals(talla))
                    .mapToLong(dc -> dc.getCantidad())
                    .sum();

            if (detDTO.getCantidad() > disponible) {
                throw new NegocioException("Stock insuficiente para " + producto.getNombre());
            }

            // Descontar stock (en memoria)
            producto.getDetalleCompras().stream()
                    .filter(dc -> dc.getTalla().equals(talla))
                    .findFirst()
                    .ifPresent(dc -> dc.setCantidad(dc.getCantidad() - detDTO.getCantidad()));

            productoDAO.actualizar(producto);

            // Crear detalle
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setTalla(talla);
            detalle.setCantidad(detDTO.getCantidad());
            detalle.setPrecioUnitario(detDTO.getPrecioUnitario());

            detalles.add(detalle);
        }

        venta.setDetalleVentas(detalles);
        ventaDAO.insertar(venta);

        return new VentaDTO(venta);
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
