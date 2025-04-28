/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import Entidades.Venta;
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
public class VentaDAOTest {

    private VentaDAO ventaDAO;

    @BeforeEach
    public void setUp() {
        ventaDAO = new VentaDAO();
    }

    @Test
    public void testInsertarYBuscarPorId() throws PersistenciaException {
        Venta venta = new Venta();
        venta.setFechaHora(LocalDateTime.now());
        venta.setTotal(3000.00);

        ventaDAO.insertar(venta);

        Venta ventaEncontrada = ventaDAO.buscarPorId(venta.getId());
        assertNotNull(ventaEncontrada);
        assertEquals(3000.00, ventaEncontrada.getTotal(), 0.01);
    }

    @Test
    public void testBuscarTodas() throws PersistenciaException {
        List<Venta> ventas = ventaDAO.buscarTodas();
        assertNotNull(ventas);
    }
}
