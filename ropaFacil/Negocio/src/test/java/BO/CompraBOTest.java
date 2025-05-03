package BO;

import DTO.CompraDTO;
import DTO.DetalleCompraDTO;
import DTO.ProductoDTO;
import DTO.TallaDTO;
import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import Persistencia.PersistenciaException;
import Exception.NegocioException;
import Interfaces.ICompraBO;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author SDavidLedesma
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompraBOTest {

    private ICompraBO compraBO;
    private ProductoBO productoBO;
    private TallaBO tallaBO;

    @BeforeAll
    void setup() throws PersistenciaException, NegocioException {
        compraBO = new CompraBO();
        productoBO = new ProductoBO();
        tallaBO = new TallaBO();
        // asegurar al menos 1 producto y talla
        ProductoDTO p = new ProductoDTO();
        p.setNombre("CompraTest");
        p.setTipo(Tipo.CAMISA);
        p.setCategoria(Categoria.DAMA);
        p.setColor(Color.ROJO);
        p.setPrecioUnitario(200.0);
        p.setCaja("5");
        p.setEstado(Estado.ACTIVO);
        productoBO.crearProducto(p);

        TallaDTO t = new TallaDTO();
        t.setCodigo("CT");
        t.setDescripcion("CompraTestTalla");
        tallaBO.crearTalla(t);
    }

    @Test
    void registrarYListarCompra() throws PersistenciaException, NegocioException {
        // arma DTO
        CompraDTO c = new CompraDTO();
        c.setFechaCompra(LocalDateTime.now());
        // detalle
        DetalleCompraDTO d = new DetalleCompraDTO();
        d.setProducto(productoBO.listarTodos().get(0));
        d.setTalla(tallaBO.listarTodas().get(0));
        d.setCantidad(3);
        d.setPrecioUnitario(200.0);
        c.setDetalleCompras(List.of(d));
        c.setTotal(600.0);

        CompraDTO registrado = compraBO.registrarCompra(c);
        assertNotNull(registrado.getId());
        // listar y buscar
        List<CompraDTO> todas = compraBO.listarCompras(null, null, null);
        assertTrue(todas.stream().anyMatch(x -> x.getId().equals(registrado.getId())));
    }

    @Test
    void errorSinDetalles() {
        CompraDTO vacia = new CompraDTO();
        vacia.setTotal(0.0);
        assertThrows(NegocioException.class, () -> compraBO.registrarCompra(vacia));
    }
}
