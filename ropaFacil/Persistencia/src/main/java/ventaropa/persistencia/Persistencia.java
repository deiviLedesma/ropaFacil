package ventaropa.persistencia;

import DAO.CompraDAO;
import DAO.VentaDAO;
import Entidades.Compra;
import Entidades.Venta;
import Persistencia.PersistenciaException;
import static java.lang.System.out;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Persistencia {

    public static void main(String[] args) {
        CompraDAO compraDAO = new CompraDAO();

        // ProductoDAO productoDAO = ProductoDAO.getInstanceDAO();
        // TallaDAO tallaDAO = new TallaDAO();
        // Crear el objeto Talla
        VentaDAO ventaDAO = new VentaDAO();

        // Crear una venta
        Venta venta = new Venta();
        venta.setFechaHora(LocalDateTime.now()); // Fecha y hora actual
        venta.setTotal(1500.00); // Total de la venta
        venta.setDetalleVentas(new ArrayList<>()); // Inicializar la lista de detalles vacía (para evitar errores)

        try {
            // Insertar la venta
            ventaDAO.insertar(venta);
            System.out.println("Venta insertada exitosamente. ID generado: " + venta.getId());
        } catch (PersistenciaException e) {
            System.err.println("Error al insertar venta: " + e.getMessage());
            e.printStackTrace();
        }

        // Buscar la venta que insertamos (por ID)
        try {
            Venta ventaBuscada = ventaDAO.buscarPorId(venta.getId()); // Convertimos a int
            if (ventaBuscada != null) {
                System.out.println("Venta encontrada: ");
                System.out.println("ID: " + ventaBuscada.getId());
                System.out.println("Fecha y hora: " + ventaBuscada.getFechaHora());
                System.out.println("Total: $" + ventaBuscada.getTotal());
            } else {
                System.out.println("No se encontró la venta.");
            }
        } catch (PersistenciaException e) {
            System.err.println("Error al buscar venta: " + e.getMessage());
            e.printStackTrace();
        }

        /**
         * Talla talla = new Talla(); talla.setCodigo("M"); // Código de talla
         * (puede ser "S", "M", "L", "XL", etc.)
         * talla.setDescripcion("mediana"); talla.setDetalleCompra(new
         * ArrayList<>()); // Inicializamos listas vacías
         * talla.setDetalleVentas(new ArrayList<>());
         *
         * try { tallaDAO.insertar(talla); System.out.println("Talla insertada
         * exitosamente con código: " + talla.getCodigo()); } catch (Exception
         * e) { System.err.println("Error al insertar talla: " +
         * e.getMessage()); e.printStackTrace(); }
         *
         * // Ahora buscar la talla para verificar try { Talla tallaBuscada =
         * tallaDAO.buscarPorCodigo("M"); if (tallaBuscada != null) {
         * System.out.println("Talla encontrada: " + tallaBuscada.getCodigo());
         * } else { System.out.println("No se encontró la talla con código M.");
         * } } catch (Exception e) { System.err.println("Error al buscar talla:
         * " + e.getMessage()); e.printStackTrace(); } *
         */
        /**
         * // Crear el objeto Producto Producto producto = new Producto();
         * producto.setNombre("Zapato Deportivo");
         * producto.setTipo(Tipo.PLAYERA);
         * producto.setCategoria(Categoria.CABALLERO);
         * producto.setColor(Enums.Color.AZUL);
         * producto.setPrecioUnitario(799.99); producto.setCaja("Caja A1");
         * producto.setEstado(Estado.ACTIVO); producto.setDetalleVentas(new
         * ArrayList<>()); // Vacío por ahora producto.setDetalleCompras(new
         * ArrayList<>()); // Vacío por ahora
         *
         * try { productoDAO.insertar(producto); System.out.println("Producto
         * insertado exitosamente con ID: " + producto.getId()); } catch
         * (Exception e) { System.err.println("Error al insertar producto: " +
         * e.getMessage()); e.printStackTrace(); } *
         */
        // Crear un objeto 
        Compra compra = new Compra();

        compra.setFechaCompra(LocalDateTime.now()); // Fecha y hora actual
        compra.setTotal(1500.50); // Total de la compra
        compra.setDetalleCompras(new ArrayList<>()); //lista vacía por
        System.out.println("Compra insertada exitosamente con ID: "
                + compra.getId());
    }

}
