/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import Entidades.Talla;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author SDavidLedesma
 */
public class TallaDAOTest {
    
   
    private TallaDAO tallaDAO;

    @BeforeEach
    public void setUp() {
        tallaDAO = new TallaDAO();
    }

    @Test
    public void testInsertarYBuscarPorCodigo() {
        Talla talla = new Talla();
        talla.setCodigo("S");
        talla.setDescripcion("Chica");

        tallaDAO.insertar(talla);

        Talla tallaEncontrada = tallaDAO.buscarPorCodigo("S");
        assertNotNull(tallaEncontrada);
        assertEquals("Chica", tallaEncontrada.getDescripcion());
    }

    @Test
    public void testBuscarTodas() {
        List<Talla> tallas = tallaDAO.buscarTodas();
        assertNotNull(tallas);
    }
}