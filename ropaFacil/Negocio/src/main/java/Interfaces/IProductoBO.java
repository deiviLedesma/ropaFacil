/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.ProductoDTO;
import Exception.NegocioException;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface IProductoBO {

    ProductoDTO crearProducto(ProductoDTO dto)
            throws NegocioException, PersistenciaException;

    ProductoDTO actualizarProducto(ProductoDTO dto)
            throws NegocioException, PersistenciaException;

    void eliminarProducto(Long id)
            throws PersistenciaException, NegocioException;

    ProductoDTO buscarProductoPorId(Long id)
            throws PersistenciaException;

    List<ProductoDTO> listarTodos()
            throws PersistenciaException;
}
