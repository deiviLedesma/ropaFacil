package Pruebas;

import BO.VentaBO;
import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import DTO.TallaDTO;
import DTO.VentaDTO;
import Exception.NegocioException;
import Persistencia.PersistenciaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainVentaTest {

    public static void main(String[] args) {
        VentaBO ventaBO = new VentaBO();

        // PRUEBA 1: Registrar una venta válida
        try {
            // Datos de prueba (ajusta estos valores según tu base de datos)
            Long idProductoExistente = 5L; // reemplaza con un ID real
            String codigoTalla = "S";    // reemplaza con una talla existente

            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdProducto(idProductoExistente);

            TallaDTO tallaDTO = new TallaDTO();
            tallaDTO.setCodigo(codigoTalla);

            DetalleVentaDTO detalle = new DetalleVentaDTO();
            detalle.setProducto(productoDTO);
            detalle.setTalla(tallaDTO);
            detalle.setCantidad(1);
            detalle.setPrecioUnitario(250.0);

            VentaDTO venta = new VentaDTO();
            List<DetalleVentaDTO> detalles = new ArrayList<>();
            detalles.add(detalle);
            venta.setDetalleVentas(detalles);
            venta.setTotal(250.0);

            VentaDTO resultado = ventaBO.registrarVenta(venta);
            System.out.println(" Venta registrada correctamente. Total: $" + resultado.getTotal());
        } catch (NegocioException | PersistenciaException e) {
            System.err.println(" Error al registrar venta: " + e.getMessage());
        }

        // PRUEBA 2: Listar ventas filtradas (últimas 48 horas)
        try {
            LocalDateTime desde = LocalDateTime.now().minusDays(2);
            LocalDateTime hasta = LocalDateTime.now();
            List<VentaDTO> ventas = ventaBO.listarVentas(null, null, null, desde, hasta);
            System.out.println(" Ventas en los ultimos 2 días:");
            for (VentaDTO v : ventas) {
                System.out.println(" - Fecha: " + v.getFechaVenta()+ ", Total: $" + v.getTotal());
            }
        } catch (PersistenciaException e) {
            System.err.println(" Error al listar ventas: " + e.getMessage());
        }
    }
}
