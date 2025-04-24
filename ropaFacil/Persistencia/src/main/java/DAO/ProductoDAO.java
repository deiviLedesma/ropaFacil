/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Producto;
import Interfaces.IProductoDAO;
import Persistencia.PersistenciaException;
import conexion.Conexion;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoDAO implements IProductoDAO {

    private static ProductoDAO instanceProductoDAO;

    public ProductoDAO() {
    }

    public static ProductoDAO getInstanceDAO() {
        if (instanceProductoDAO == null) {
            instanceProductoDAO = new ProductoDAO();
        }

        return instanceProductoDAO;
    }

    @Override
    public void insertar(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Producto producto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(int idProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Producto producto = em.find(Producto.class, idProducto);
            if (producto != null) {
                em.getTransaction().begin();
                em.remove(producto);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public Producto buscarPorId(int idProducto) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Producto.class, idProducto);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Producto> buscarTodos() {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        } finally {
            em.close();
        }
    }

}
