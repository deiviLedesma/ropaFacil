/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Compra;
import Interfaces.ICompraDAO;
import Persistencia.PersistenciaException;
import conexion.Conexion;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author SDavidLedesma
 */
public class CompraDAO implements ICompraDAO {

    @Override
    public void insertar(Compra compra) {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(compra);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Compra buscarPorId(int id) {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Compra> buscarTodas() {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT c FROM Compra c", Compra.class).getResultList();
        } finally {
            em.close();
        }
    }
}
