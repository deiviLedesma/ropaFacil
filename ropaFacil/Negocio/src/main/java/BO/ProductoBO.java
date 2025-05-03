package BO;

import DTO.ProductoDTO;
import Entidades.Producto;
import Exception.NegocioException;
import Persistencia.PersistenciaException;
import DAO.ProductoDAO;
import Interfaces.IProductoBO;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoBO implements IProductoBO {

    private final ProductoDAO productoDAO;

    public ProductoBO() {
        this.productoDAO = ProductoDAO.getInstanceDAO();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO dto)
            throws NegocioException, PersistenciaException {
        // Validaciones de negocio
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new NegocioException("El nombre es obligatorio.");
        }
        // No permitir duplicar nombre + color + caja
        boolean existe = productoDAO.buscarTodos().stream()
                .anyMatch(p
                        -> p.getNombre().equalsIgnoreCase(dto.getNombre())
                && p.getColor() == dto.getColor()
                && p.getCaja().equals(dto.getCaja())
                );
        if (existe) {
            throw new NegocioException("Ya existe un producto con ese nombre/color/caja.");
        }
        // Persistir
        Producto entity = dto.toEntity();
        productoDAO.insertar(entity);
        dto.setIdProducto(entity.getId());
        return dto;
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO dto)
            throws NegocioException, PersistenciaException {
        if (dto.getIdProducto() <= 0) {
            throw new NegocioException("ID de producto inválido.");
        }
        // Validaciones opcionales...
        Producto entity = dto.toEntity();
        productoDAO.actualizar(entity);
        return dto;
    }

    @Override
    public void eliminarProducto(Long id)
            throws PersistenciaException, NegocioException {
        // Podrías validar que no haya ventas o compras asociadas
        productoDAO.eliminar(id);
    }

    @Override
    public ProductoDTO buscarProductoPorId(Long id)
            throws PersistenciaException {
        Producto p = productoDAO.buscarPorId(id);
        return p != null ? new ProductoDTO(p) : null;
    }

    @Override
    public List<ProductoDTO> listarTodos()
            throws PersistenciaException {
        return productoDAO.buscarTodos().stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }
}
