/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Venta;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface IVentaDAO {

    public void insertar(Venta venta) throws PersistenciaException;

    public Venta buscarPorId(Long id) throws PersistenciaException;

    public List<Venta> buscarTodas() throws PersistenciaException;

}
