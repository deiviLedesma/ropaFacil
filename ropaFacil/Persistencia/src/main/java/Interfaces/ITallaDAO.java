/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Talla;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface ITallaDAO {

    public void insertar(Talla talla) throws PersistenciaException;

    public Talla buscarPorCodigo(String codigo) throws PersistenciaException;

    public List<Talla> buscarTodas() throws PersistenciaException;
}
