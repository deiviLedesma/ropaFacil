package BO;


import DTO.TallaDTO;
import Entidades.Talla;
import Exception.NegocioException;
import Persistencia.PersistenciaException;
import DAO.TallaDAO;
import Interfaces.ITallaBO;

import java.util.List;
import java.util.stream.Collectors;

public class TallaBO implements ITallaBO {

    private final TallaDAO tallaDAO;

    
    public TallaBO() {
        this.tallaDAO = new TallaDAO();
    }
    

    @Override
    public TallaDTO crearTalla(TallaDTO dto)
            throws NegocioException, PersistenciaException {
        if (dto.getDescripcion()== null || dto.getDescripcion().isBlank()) {
            throw new NegocioException("La descripción de la talla es obligatoria.");
        }
        // Evitar duplicados
        if (tallaDAO.buscarPorCodigo(dto.getCodigo()) != null) {
            throw new NegocioException("La talla ya existe: " + dto.getCodigo());
        }
        Talla entity = dto.toEntity();
        tallaDAO.insertar(entity);
        dto.setCodigo(entity.getCodigo());
        return dto;
    }

    @Override
    public TallaDTO buscarTallaPorCodigo(String codigo)
            throws PersistenciaException {
        Talla t = tallaDAO.buscarPorCodigo(codigo);
        return t != null ? new TallaDTO(t) : null;
    }

    @Override
    public List<TallaDTO> listarTodas()
            throws PersistenciaException {
        return tallaDAO.buscarTodas().stream()
                .map(TallaDTO::new)
                .collect(Collectors.toList());
    }
}
