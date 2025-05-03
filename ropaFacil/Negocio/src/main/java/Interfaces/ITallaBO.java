/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.TallaDTO;
import Exception.NegocioException;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public interface ITallaBO {

    TallaDTO crearTalla(TallaDTO dto)
            throws NegocioException, PersistenciaException;

    TallaDTO buscarTallaPorCodigo(String codigo)
            throws PersistenciaException;

    List<TallaDTO> listarTodas()
            throws PersistenciaException;
}
