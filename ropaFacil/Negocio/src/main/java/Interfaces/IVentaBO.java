package Interfaces;


import DTO.VentaDTO;
import Exception.NegocioException;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 * 
 * @author SDavidLedesma
 */
public interface IVentaBO {

    VentaDTO registrarVenta(VentaDTO dto)
            throws NegocioException, PersistenciaException;

    List<VentaDTO> listarVentas(String color, String talla,
            Integer idProducto,
            java.time.LocalDateTime desde,
            java.time.LocalDateTime hasta)
            throws PersistenciaException;
}
