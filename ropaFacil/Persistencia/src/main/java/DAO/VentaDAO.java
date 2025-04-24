/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Venta;
import Interfaces.IVentaDAO;
import Persistencia.PersistenciaException;
import conexion.Conexion;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author SDavidLedesma
 */
public class VentaDAO implements IVentaDAO {

    @Override
    public void insertar(Venta venta) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Venta buscarPorId(int id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Venta> buscarTodas() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try{
            return em.createQuery("SELECT v FROM Venta v", Venta.class).getResultList();
        } finally{
            em.close();
        }
    }

}
