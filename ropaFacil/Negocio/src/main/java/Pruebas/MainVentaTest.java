package Pruebas;

import BO.VentaBO;
import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import DTO.TallaDTO;
import DTO.VentaDTO;
import Enums.Color;
import Exception.NegocioException;
import Persistencia.PersistenciaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author SDavidLedesma
 */
public class MainVentaTest {

    public static void main(String[] args) {
        try {
            VentaBO ventaBO = new VentaBO();

            // Producto existente en BD 
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdProducto(9L); 
            productoDTO.setColor(Color.ROJO); 

            // Talla existente en BD con código "S"
            TallaDTO tallaDTO = new TallaDTO();
            tallaDTO.setCodigo("SS");

            // Crear detalle de venta
            DetalleVentaDTO detalle = new DetalleVentaDTO();
            detalle.setProducto(productoDTO);
            detalle.setTalla(tallaDTO);
            detalle.setCantidad(1);
            detalle.setPrecioUnitario(250.00); 

            List<DetalleVentaDTO> detalles = new ArrayList<>();
            detalles.add(detalle);

            // Crear la venta
            VentaDTO ventaDTO = new VentaDTO();
            ventaDTO.setFechaVenta(LocalDateTime.now());
            ventaDTO.setTotal(250.00); 
            ventaDTO.setDetalleVentas(detalles);

            // Registrar
            VentaDTO ventaRegistrada = ventaBO.registrarVenta(ventaDTO);
            System.out.println("Venta registrada con éxito: ");
            System.out.println(ventaRegistrada);

        } catch (NegocioException | PersistenciaException e) {
            System.err.println("Error al registrar venta: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
