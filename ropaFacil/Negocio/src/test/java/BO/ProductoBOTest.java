package BO;

import DTO.ProductoDTO;
import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import Persistencia.PersistenciaException;
import Exception.NegocioException;
import Interfaces.IProductoBO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductoBOTest {

    private IProductoBO productoBO;

    @BeforeAll
    void setup() {
        productoBO = new ProductoBO();
    }

    @Test
    void crearYBuscarProducto() throws PersistenciaException, NegocioException {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre("TestCamisa");
        dto.setTipo(Tipo.CAMISA);
        dto.setCategoria(Categoria.DAMA);
        dto.setColor(Color.ROJO);
        dto.setPrecioUnitario(100.0);
        dto.setCaja("1");
        dto.setEstado(Estado.ACTIVO);

        // creo
        ProductoDTO creado = productoBO.crearProducto(dto);
        assertTrue(creado.getIdProducto() > 0);

        // busco
        ProductoDTO encontrado = productoBO.buscarProductoPorId(creado.getIdProducto());
        assertNotNull(encontrado);
        assertEquals("TestCamisa", encontrado.getNombre());
    }

    @Test
    void listarTodos() throws PersistenciaException {
        List<ProductoDTO> todos = productoBO.listarTodos();
        assertNotNull(todos);
        assertFalse(todos.isEmpty());
    }

    @Test
    void actualizarProducto() throws PersistenciaException, NegocioException {
        // toma el primero
        ProductoDTO primero = productoBO.listarTodos().get(0);
        String old = primero.getNombre();
        primero.setNombre(old + "_X");
        ProductoDTO upd = productoBO.actualizarProducto(primero);
        assertEquals(old + "_X", upd.getNombre());
    }

    @Test
    void eliminarProducto() throws PersistenciaException, NegocioException {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre("ToDelete");
        dto.setTipo(Tipo.VESTIDO);
        dto.setCategoria(Categoria.CABALLERO);
        dto.setColor(Color.AZUL);
        dto.setPrecioUnitario(50.0);
        dto.setCaja("2");
        dto.setEstado(Estado.ACTIVO);

        ProductoDTO creado = productoBO.crearProducto(dto);
        assertNotNull(productoBO.buscarProductoPorId(creado.getIdProducto()));

        productoBO.eliminarProducto(creado.getIdProducto());
        ProductoDTO borrado = productoBO.buscarProductoPorId(creado.getIdProducto());
        assertNull(borrado);
    }
}
