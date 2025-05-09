package Pruebas;

import BO.CompraBO;
import DTO.CompraDTO;
import DTO.DetalleCompraDTO;
import DTO.ProductoDTO;
import DTO.TallaDTO;
import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import Exception.NegocioException;
import Persistencia.PersistenciaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de prueba para registrar y listar compras.
 *
 * @author SDavidLedesma
 */
public class PruebaCompraBO {

    public static void main(String[] args) {
        try {
            CompraBO compraBO = new CompraBO();

            // Crear ProductoDTO
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setNombre("Camisa Prueba");
            productoDTO.setTipo(Tipo.CAMISA);
            productoDTO.setCategoria(Categoria.CABALLERO);
            productoDTO.setColor(Color.ROJO);
            productoDTO.setPrecioUnitario(199.99);
            productoDTO.setCaja("Caja A");
            productoDTO.setEstado(Estado.ACTIVO);

            // Crear TallaDTO
            TallaDTO tallaDTO = new TallaDTO();
            tallaDTO.setCodigo("M");
            tallaDTO.setDescripcion("Mediana");

            // Crear DetalleCompraDTO
            DetalleCompraDTO detalle = new DetalleCompraDTO();
            detalle.setCantidad(3);
            detalle.setPrecioUnitario(199.99);
            detalle.setProducto(productoDTO);
            detalle.setTalla(tallaDTO);

            List<DetalleCompraDTO> listaDetalles = new ArrayList<>();
            listaDetalles.add(detalle);

            // Crear CompraDTO
            CompraDTO compraDTO = new CompraDTO();
            compraDTO.setFechaCompra(LocalDateTime.now());
            compraDTO.setTotal(3 * 199.99);
            compraDTO.setDetalleCompras(listaDetalles);

            // Registrar compra
            CompraDTO compraRegistrada = compraBO.registrarCompra(compraDTO);
            System.out.println("✅ Compra registrada exitosamente:");
            System.out.println(compraRegistrada);

            // Listar compras en el rango de hoy
            System.out.println("\n📦 Listando compras de hoy...");
            LocalDateTime desde = LocalDateTime.now().minusDays(1);
            LocalDateTime hasta = LocalDateTime.now().plusDays(1);
            List<CompraDTO> compras = compraBO.listarCompras(desde, hasta, null);

            for (CompraDTO c : compras) {
                System.out.println(c);
            }

        } catch (NegocioException | PersistenciaException e) {
            System.err.println("❌ Error durante la operación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
