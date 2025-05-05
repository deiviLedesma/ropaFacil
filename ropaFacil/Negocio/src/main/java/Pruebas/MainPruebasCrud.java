package Pruebas;


import DAO.ProductoDAO;
import Entidades.Producto;
import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 * 
 * @author SDavidLedesma
 */
public class MainPruebasCrud {

    public static void main(String[] args) throws PersistenciaException {
        ProductoDAO productoDAO = new ProductoDAO();

        // 1. Insertar producto
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("TestProductoQ");
        nuevoProducto.setTipo(Tipo.CAMISA); // asegúrate que este valor existe en el enum
        nuevoProducto.setCategoria(Categoria.CABALLERO);
        nuevoProducto.setColor(Color.ROJO);
        nuevoProducto.setPrecioUnitario(15.5);
        nuevoProducto.setCaja("Caja 1");
        nuevoProducto.setEstado(Estado.ACTIVO);

        System.out.println("Insertando producto...");
        productoDAO.insertar(nuevoProducto);

        // 2. Consultar todos
        System.out.println("Lista de productos:");
        List<Producto> productos = productoDAO.buscarTodos();
        for (Producto p : productos) {
            System.out.println(p);
        }

        // 3. Actualizar el producto
        Producto productoExistente = productos.get(productos.size() - 1); // el último insertado
        productoExistente.setPrecioUnitario(20.0);
        System.out.println("Actualizando producto con ID " + productoExistente.getId());
        productoDAO.actualizar(productoExistente);

        // 4. Consultar por ID
        Producto consultado = productoDAO.buscarPorId(productoExistente.getId());
        System.out.println("Producto consultado por ID: " + consultado);

        // 5. Eliminar
        System.out.println("Eliminando producto con ID " + productoExistente.getId());
        productoDAO.eliminar(productoExistente.getId());

    }
}
