/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Compra;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface ICompraDAO {

    public void insertar(Compra compra) throws PersistenciaException;

    public Compra buscarPorId(int id) throws PersistenciaException;

    public List<Compra> buscarTodas() throws PersistenciaException;

}
