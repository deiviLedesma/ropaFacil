/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.CompraDTO;
import Exception.NegocioException;
import Persistencia.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface ICompraBO {

    /**
     * Registra una compra. Si el producto es nuevo, crea tanto Producto como
     * Compra; si es reposición, sólo agrega stock.
     *
     * @param compraDTO
     * @return 
     * @throws Exception.NegocioException
     * @throws Persistencia.PersistenciaException
     */
    CompraDTO registrarCompra(CompraDTO compraDTO)
            throws NegocioException, PersistenciaException;

    /**
     * Obtiene el historial de compras, opcionalmente filtrado.
     * @param desde
     * @param hasta
     * @param idProducto
     * @return 
     * @throws Persistencia.PersistenciaException 
     */
    List<CompraDTO> listarCompras(LocalDateTime desde, LocalDateTime hasta,
            Long idProducto)
            throws PersistenciaException;
}
