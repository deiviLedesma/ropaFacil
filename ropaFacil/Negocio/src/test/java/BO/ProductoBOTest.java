package BO;

import DAO.ProductoDAO;
import DTO.ProductoDTO;
import Entidades.Producto;
import Enums.Color;
import Exception.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoBOTest {

    public ProductoDAO mockDAO;
    public ProductoBO productoBO;

    @BeforeEach
    public void setup() {
        mockDAO = mock(ProductoDAO.class);
        productoBO = new ProductoBO(mockDAO); // inyección directa
    }

    @Test
    public void testCrearProducto_Exitoso() throws Exception {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre("Producto ROSa");
        dto.setColor(Color.ROSA);
        dto.setCaja("Caja A");

        when(mockDAO.buscarTodos()).thenReturn(Collections.emptyList());
        doAnswer(invoc -> {
            Producto p = invoc.getArgument(0);
            p.setId(1L); // Simular ID generado
            return null;
        }).when(mockDAO).insertar(any(Producto.class));

        ProductoDTO resultado = productoBO.crearProducto(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProducto());
    }

    @Test
    public void testCrearProducto_Nulo() {
        assertThrows(NegocioException.class, () -> {
            productoBO.crearProducto(null);
        });
    }

    @Test
    public void testCrearProducto_SinNombre() {
        ProductoDTO dto = new ProductoDTO();
        dto.setColor(Color.AZUL);
        dto.setCaja("Caja A");

        assertThrows(NegocioException.class, () -> {
            productoBO.crearProducto(dto);
        });
    }

    @Test
    public void testCrearProducto_Duplicado() throws Exception {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre("Producto A");
        dto.setColor(Color.ROSA);
        dto.setCaja("Caja A");

        Producto duplicado = new Producto();
        duplicado.setNombre("Producto A");
        duplicado.setColor(Color.ROSA);
        duplicado.setCaja("Caja A");

        when(mockDAO.buscarTodos()).thenReturn(Collections.singletonList(duplicado));

        assertThrows(NegocioException.class, () -> {
            productoBO.crearProducto(dto);
        });
    }

}
