package Pruebas;

import BO.CompraBO;
import DTO.CompraDTO;
import DTO.DetalleCompraDTO;
import DTO.ProductoDTO;
import DTO.TallaDTO;
import Entidades.Producto;
import Entidades.Talla;
import Exception.NegocioException;
import Persistencia.PersistenciaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PruebaCompraBO {

    public static void main(String[] args) {
        CompraBO compraBO = new CompraBO();

        try {

            // Producto
            Producto producto = new Producto();
            producto.setId(1L); // Ajusta si se requiere ID real
            producto.setNombre("Camisa");
          //  producto.setDetalleCompras(new ArrayList<>()); // IMPORTANTE para evitar NullPointer

            ProductoDTO productoDTO = new ProductoDTO(producto);

            // Talla
            Talla talla = new Talla();
            talla.setCodigo("sxs"); // Ajusta si se requiere ID real
            talla.setDescripcion("superchicio");

            TallaDTO tallaDTO = new TallaDTO(talla);

            // Detalle de la compra
            DetalleCompraDTO detalle = new DetalleCompraDTO();
            detalle.setCantidad(2);
            detalle.setPrecioUnitario(12.00);
            detalle.setProducto(productoDTO);
            detalle.setTalla(tallaDTO);

            List<DetalleCompraDTO> detalles = new ArrayList<>();
            detalles.add(detalle);

            // CompraDTO
            CompraDTO compraDTO = new CompraDTO();
            compraDTO.setFechaCompra(LocalDateTime.now());
            compraDTO.setTotal(500.00);
            compraDTO.setDetalleCompras(detalles);

            // Registrar compra
            CompraDTO resultado = compraBO.registrarCompra(compraDTO);

            System.out.println("Compra registrada con éxito: " + resultado.getTotal());
        } catch (NegocioException | PersistenciaException e) {
            System.err.println("Error al registrar la compra: " + e.getMessage());
        }
    }
}
