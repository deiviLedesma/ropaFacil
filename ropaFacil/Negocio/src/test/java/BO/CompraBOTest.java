package BO;

import DAO.CompraDAO;
import DAO.ProductoDAO;
import DAO.TallaDAO;
import DTO.CompraDTO;
import DTO.DetalleCompraDTO;
import DTO.ProductoDTO;
import DTO.TallaDTO;
import Entidades.Compra;
import Entidades.DetalleCompra;
import Entidades.Producto;
import Entidades.Talla;
import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import Exception.NegocioException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 
 * @author SDavidLedesma
 */
public class CompraBOTest {

    private CompraDAO mockCompraDAO;
    private ProductoDAO mockProductoDAO;
    private TallaDAO mockTallaDAO;
    private CompraBO compraBO;

    @BeforeEach
    public void setUp() {
        mockCompraDAO = mock(CompraDAO.class);
        mockProductoDAO = mock(ProductoDAO.class);
        mockTallaDAO = mock(TallaDAO.class);

        // Clase anónima para inyectar DAOs mockeados
        compraBO = new CompraBO(mockCompraDAO, mockProductoDAO, mockTallaDAO) {

        };
    }

    @Test
    public void testRegistrarCompra_Exito() throws Exception {
        // DTOs de entrada
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Camisa");
        productoDTO.setTipo(Tipo.CAMISA);
        productoDTO.setCategoria(Categoria.CABALLERO);
        productoDTO.setColor(Color.ROJO);
        productoDTO.setPrecioUnitario(200);
        productoDTO.setCaja("Caja1");
        productoDTO.setEstado(Estado.ACTIVO);

        TallaDTO tallaDTO = new TallaDTO();
        tallaDTO.setCodigo("M");
        tallaDTO.setDescripcion("Mediana");

        DetalleCompraDTO detalle = new DetalleCompraDTO();
        detalle.setCantidad(2);
        detalle.setPrecioUnitario(200);
        detalle.setProducto(productoDTO);
        detalle.setTalla(tallaDTO);

        List<DetalleCompraDTO> detalles = new ArrayList<>();
        detalles.add(detalle);

        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setFechaCompra(LocalDateTime.now());
        compraDTO.setTotal(400.00);
        compraDTO.setDetalleCompras(detalles);

        // Simular comportamiento de DAOs
        doAnswer(invocation -> {
            Producto p = invocation.getArgument(0);
            p.setId(1L); // Simula que la BD le asigna ID
            return null;
        }).when(mockProductoDAO).insertar(any(Producto.class));

        doNothing().when(mockProductoDAO).actualizar(any(Producto.class));
        doNothing().when(mockCompraDAO).insertar(any());

        CompraDTO resultado = compraBO.registrarCompra(compraDTO);

        assertNotNull(resultado);
        assertEquals(1, resultado.getDetalleCompras().size());
    }

    @Test
    public void testRegistrarCompra_Nula() {
        assertThrows(NegocioException.class, () -> {
            compraBO.registrarCompra(null);
        });
    }

    @Test
    public void testRegistrarCompra_SinDetalles() {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setDetalleCompras(new ArrayList<>());

        assertThrows(NegocioException.class, () -> {
            compraBO.registrarCompra(compraDTO);
        });
    }

    @Test
    public void testRegistrarCompra_CantidadNegativa() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre("Camisa");

        TallaDTO tallaDTO = new TallaDTO();
        tallaDTO.setCodigo("M");

        DetalleCompraDTO detalle = new DetalleCompraDTO();
        detalle.setCantidad(0);
        detalle.setProducto(productoDTO);
        detalle.setTalla(tallaDTO);

        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setTotal(100.00);
        compraDTO.setDetalleCompras(Collections.singletonList(detalle));

        assertThrows(NegocioException.class, () -> {
            compraBO.registrarCompra(compraDTO);
        });
    }

    @Test
    public void testListarCompras_FiltradoPorRangoYProducto() throws Exception {
        // Simular datos
        Producto producto = new Producto();
        producto.setId(1L);

        Talla talla = new Talla();
        DetalleCompra detalle = new DetalleCompra();
        detalle.setProducto(producto);
        detalle.setTalla(talla);

        Compra compra = new Compra();
        compra.setFechaCompra(LocalDateTime.now());
        compra.setDetalleCompras(List.of(detalle));

        when(mockCompraDAO.buscarTodas()).thenReturn(List.of(compra));

        // Ejecutar método
        List<CompraDTO> resultado = compraBO.listarCompras(
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                1L);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    public void testListarCompras_SinFiltros() throws Exception {
        when(mockCompraDAO.buscarTodas()).thenReturn(Collections.emptyList());

        List<CompraDTO> resultado = compraBO.listarCompras(null, null, null);
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
    }

}
