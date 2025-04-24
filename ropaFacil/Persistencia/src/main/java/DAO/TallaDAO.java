/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Talla;
import Interfaces.ITallaDAO;
import conexion.Conexion;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author SDavidLedesma
 */
public class TallaDAO implements ITallaDAO {

    @Override
    public void insertar(Talla talla) {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(talla);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Talla buscarPorCodigo(String codigo) {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Talla.class, codigo);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Talla> buscarTodas() {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT t FROM Talla t", Talla.class).getResultList();
        } finally {
            em.close();
        }
    }
}
