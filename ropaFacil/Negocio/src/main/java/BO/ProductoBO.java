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

    final ProductoDAO productoDAO;

    public ProductoBO() {
        this.productoDAO = ProductoDAO.getInstanceDAO();
    }

    // Constructor alternativo para pruebas
    public ProductoBO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO dto)
            throws NegocioException, PersistenciaException {

        if (dto == null) {
            throw new NegocioException("El producto no puede ser null.");
        }

        // Validar nombre
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new NegocioException("El nombre es obligatorio.");
        }

        // Evitar duplicados (nombre + color + caja)
        boolean existe = productoDAO.buscarTodos().stream()
                .anyMatch(p
                        -> p.getNombre().equalsIgnoreCase(dto.getNombre())
                && p.getColor() == dto.getColor()
                && p.getCaja().equals(dto.getCaja())
                );
        if (existe) {
            throw new NegocioException("Ya existe un producto con ese nombre/color/caja.");
        }

        // Persistir entidad
        Producto entidad = dto.toEntity();
        productoDAO.insertar(entidad);

        // Reflejar el ID generado en el DTO
        dto.setIdProducto(entidad.getId());
        return dto;
    }

    @Override
    public ProductoDTO buscarProductoPorId(Long id)
            throws PersistenciaException {
        Producto p = productoDAO.buscarPorId(id);
        return (p != null) ? new ProductoDTO(p) : null;
    }

    @Override
    public List<ProductoDTO> listarTodos()
            throws PersistenciaException {
        return productoDAO.buscarTodos().stream()
                .map(ProductoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoDTO dto)
            throws NegocioException, PersistenciaException {

        if (dto.getIdProducto() == null || dto.getIdProducto() <= 0) {
            throw new NegocioException("ID de producto inválido.");
        }

        Producto entidad = dto.toEntity();
        productoDAO.actualizar(entidad);
        return dto;
    }

    @Override
    public void eliminarProducto(Long id)
            throws PersistenciaException, NegocioException {

        productoDAO.eliminar(id);
    }
}
