package Pruebas;

import BO.TallaBO;
import DTO.TallaDTO;
import Exception.NegocioException;
import Persistencia.PersistenciaException;

import java.util.List;

public class MainTallaTest {

    public static void main(String[] args) {
        TallaBO tallaBO = new TallaBO();

        // PRUEBA 1: Crear una talla
        try {
            TallaDTO nuevaTalla = new TallaDTO();
            nuevaTalla.setCodigo("XL");
            nuevaTalla.setDescripcion("Extra Grande");

            TallaDTO creada = tallaBO.crearTalla(nuevaTalla);
            System.out.println(" Talla creada: " + creada.getDescripcion() + " (" + creada.getCodigo() + ")");
        } catch (NegocioException | PersistenciaException e) {
            System.err.println(" Error al crear talla: " + e.getMessage());
        }

        // PRUEBA 2: Buscar una talla por código
        try {
            String codigoBuscar = "M"; // cambia por un código que exista en tu BD
            TallaDTO encontrada = tallaBO.buscarTallaPorCodigo(codigoBuscar);
            if (encontrada != null) {
                System.out.println(" Talla encontrada: " + encontrada.getDescripcion() + " (" + encontrada.getCodigo() + ")");
            } else {
                System.out.println("️ No se encontró ninguna talla con código: " + codigoBuscar);
            }
        } catch (PersistenciaException e) {
            System.err.println(" Error al buscar talla: " + e.getMessage());
        }

        // PRUEBA 3: Listar todas las tallas
        try {
            List<TallaDTO> todas = tallaBO.listarTodas();
            System.out.println(" Lista de tallas:");
            for (TallaDTO t : todas) {
                System.out.println(" - " + t.getCodigo() + ": " + t.getDescripcion());
            }
        } catch (PersistenciaException e) {
            System.err.println(" Error al listar tallas: " + e.getMessage());
        }
    }
}
