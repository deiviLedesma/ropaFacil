/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import Entidades.Compra;
import Entidades.DetalleCompra;
import Entidades.Producto;
import Entidades.Talla;
import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import Persistencia.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author SDavidLedesma
 */
public class CompraDAOTest {

    private CompraDAO compraDAO;
    private ProductoDAO productoDAO;
    private TallaDAO tallaDAO;

    @BeforeEach
    public void setUp() {
        compraDAO = new CompraDAO();
        productoDAO = new ProductoDAO();
        tallaDAO = new TallaDAO();
    }

    @Test
    public void testInsertarCompraConDetalles() throws PersistenciaException {
        // Insertar producto
        Producto producto = new Producto();
        producto.setNombre("Sudadera Roja");
        producto.setColor(Color.ROJO);
        producto.setPrecioUnitario(500.0);
        producto.setCaja("7");
        producto.setEstado(Estado.ACTIVO);
        producto.setTipo(Tipo.VESTIDO);
        producto.setCategoria(Categoria.DAMA);
        productoDAO.insertar(producto);

        // Insertar talla
        Talla talla = new Talla();
        talla.setCodigo("MM");
        talla.setDescripcion("Extra Extra Grande");
        tallaDAO.insertar(talla);

        DetalleCompra det = new DetalleCompra();
        det.setProducto(producto);
        det.setTalla(talla);
        det.setCantidad(5);
        det.setPrecioUnitario(500);

        Compra compra = new Compra();
        compra.setFechaCompra(LocalDateTime.now());
        compra.setTotal(2500);
        det.setCompra(compra);
        compra.setDetalleCompras(List.of(det));

        // 2) insertas y recibes la instancia managed
        Compra comprado = compraDAO.insertar(compra);

        // 3) compruebas que el ID ya existe y trae detalles
        assertNotNull(comprado.getId());
        assertEquals(1, comprado.getDetalleCompras().size());

    }

    @Test
    public void testInsertarYBuscarPorId() throws PersistenciaException {
        // Creamos y guardamos una compra simple
        Compra compra = new Compra();
        compra.setFechaCompra(LocalDateTime.now());
        compra.setTotal(1500.0);

        Compra comprado = compraDAO.insertar(compra);
        assertNotNull(comprado.getId(), "El ID no debe ser null tras persistir");

        // La buscamos por ID
        Compra encontrada = compraDAO.buscarPorId(comprado.getId());
        assertNotNull(encontrada, "Debe encontrar la compra por su ID");
        assertEquals(1500.0, encontrada.getTotal(), 0.01,
                "El total recuperado debe coincidir");
    }

    @Test
    public void testBuscarTodas() {
        List<Compra> compras = compraDAO.buscarTodas();
        assertNotNull(compras);
    }
}
