package BO;

import DTO.CompraDTO;
import DTO.DetalleCompraDTO;
import Entidades.Compra;
import Entidades.DetalleCompra;
import Entidades.Producto;
import Entidades.Talla;
import Persistencia.PersistenciaException;
import DAO.CompraDAO;
import DAO.ProductoDAO;
import DAO.TallaDAO;
import Exception.NegocioException;
import Interfaces.ICompraBO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompraBO implements ICompraBO {

    private final CompraDAO compraDAO;
    private final ProductoDAO productoDAO;
    private final TallaDAO tallaDAO;

    public CompraBO() {
        this.compraDAO = new CompraDAO();
        this.productoDAO = ProductoDAO.getInstanceDAO();
        this.tallaDAO = new TallaDAO();
    }

    //constructor para pruebas 
    public CompraBO(CompraDAO compraDAO, ProductoDAO productoDAO, TallaDAO tallaDAO) {
        this.compraDAO = compraDAO;
        this.productoDAO = productoDAO;
        this.tallaDAO = tallaDAO;
    }

    @Override
    public CompraDTO registrarCompra(CompraDTO compraDTO)
            throws NegocioException, PersistenciaException {

        if (compraDTO == null || compraDTO.getDetalleCompras().isEmpty()) {
            throw new NegocioException("La compra debe incluir al menos un detalle.");
        }

        Compra compra = new Compra();
        compra.setFechaCompra(compraDTO.getFechaCompra() != null
                ? compraDTO.getFechaCompra() : LocalDateTime.now());
        compra.setTotal(compraDTO.getTotal());

        List<DetalleCompra> detalles = new ArrayList<>();

        for (DetalleCompraDTO dto : compraDTO.getDetalleCompras()) {
            if (dto.getCantidad() <= 0) {
                throw new NegocioException("La cantidad debe ser mayor que 0.");
            }

            Producto prod = dto.getProducto().toEntity();

            // Asegurar lista no nula
            if (prod.getDetalleCompras() == null) {
                prod.setDetalleCompras(new ArrayList<>());
            }

            Talla talla = dto.getTalla().toEntity();

            DetalleCompra det = new DetalleCompra();
            det.setCompra(compra);
            det.setProducto(prod);
            det.setTalla(talla);
            det.setCantidad(dto.getCantidad());
            det.setPrecioUnitario(dto.getPrecioUnitario());

            detalles.add(det);
            productoDAO.insertar(prod);

            // Insertar o actualizar según el caso
            if (prod.getId() != null) {
                productoDAO.actualizar(prod);
            } else {
                productoDAO.insertar(prod);
            }
        }

        compra.setDetalleCompras(detalles);
        compraDAO.insertar(compra);

        return new CompraDTO(compra);
    }

    @Override
    public List<CompraDTO> listarCompras(LocalDateTime desde, LocalDateTime hasta,
            Long idProducto)
            throws PersistenciaException {
        List<Compra> compras = compraDAO.buscarTodas();
        List<CompraDTO> dtos = new ArrayList<>();
        for (Compra c : compras) {
            boolean ok = true;
            if (desde != null && c.getFechaCompra().isBefore(desde)) {
                ok = false;
            }
            if (hasta != null && c.getFechaCompra().isAfter(hasta)) {
                ok = false;
            }
            if (idProducto != null) {
                ok &= c.getDetalleCompras().stream()
                        .anyMatch(d -> Objects.equals(d.getProducto().getId(), idProducto));
            }
            // proveedor no modelado aquí, omitir
            if (ok) {
                dtos.add(new CompraDTO(c));
            }
        }
        return dtos;
    }

}
