/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Producto;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface IProductoDAO {

    public void insertar(Producto producto) throws PersistenciaException;

    public void actualizar(Producto producto) throws PersistenciaException;

    public void eliminar(Long idProducto) throws PersistenciaException;

    public Producto buscarPorId(Long idProducto) throws PersistenciaException;

    public List<Producto> buscarTodos();

}
