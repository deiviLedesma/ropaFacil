/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import Entidades.Producto;
import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import Persistencia.PersistenciaException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoDAOTest {

    private ProductoDAO productoDAO;

    @BeforeEach
    public void setUp() {
        productoDAO = ProductoDAO.getInstanceDAO();
    }

    @Test
    public void testInsertarYBuscarPorId() throws PersistenciaException {
        Producto producto = new Producto();
        producto.setNombre("Playera Azul");
        producto.setColor(Color.ROJO);
        producto.setPrecioUnitario(299.99);
        producto.setCaja("5");
        producto.setEstado(Estado.ACTIVO); 
        producto.setTipo(Tipo.VESTIDO);
        producto.setCategoria(Categoria.DAMA);

        productoDAO.insertar(producto);

        Producto productoEncontrado = productoDAO.buscarPorId(producto.getId());
        assertNotNull(productoEncontrado);
        assertEquals("Playera Azul", productoEncontrado.getNombre());
    }

    @Test
    public void testActualizar() throws PersistenciaException {
        Producto producto = new Producto();
        producto.setNombre("Pantalón Negro");
        producto.setColor(Color.MORADO);
        producto.setPrecioUnitario(450.00);
        producto.setCaja("2");
        producto.setEstado(Estado.ACTIVO);
        producto.setTipo(Tipo.FALDA);
        producto.setCategoria(Categoria.CABALLERO);

        productoDAO.insertar(producto);

        producto.setColor(Color.NARANJA);
        productoDAO.actualizar(producto);

        Producto actualizado = productoDAO.buscarPorId(producto.getId());
        assertEquals(Color.NARANJA, actualizado.getColor());
    }

    @Test
    public void testEliminar() throws PersistenciaException {
        Producto producto = new Producto();
        producto.setNombre("Short Deportivo");
        producto.setColor(Enums.Color.VERDE);
        producto.setPrecioUnitario(199.99);
        producto.setCaja("1");
        producto.setEstado(Estado.ACTIVO);
        producto.setTipo(Tipo.VESTIDO);
        producto.setCategoria(Categoria.DAMA);

        productoDAO.insertar(producto);

        productoDAO.eliminar(producto.getId());

        Producto eliminado = productoDAO.buscarPorId(producto.getId());
        assertNull(eliminado);
    }

    @Test
    public void testBuscarTodos() {
        List<Producto> productos = productoDAO.buscarTodos();
        assertNotNull(productos);
    }
}
