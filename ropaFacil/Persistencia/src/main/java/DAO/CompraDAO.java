/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Compra;
import Interfaces.ICompraDAO;
import conexion.Conexion;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author SDavidLedesma
 */
public class CompraDAO implements ICompraDAO {

    private static CompraDAO instanceCompraDAO;

    public CompraDAO() {
    }

    //singleton
    public static CompraDAO getInstanceDAO() {
        if (instanceCompraDAO == null) {
            instanceCompraDAO = new CompraDAO();
        }

        return instanceCompraDAO;
    }

    @Override
    public Compra insertar(Compra compra) {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            // Al hacer merge, JPA:
            //  • persiste o actualiza la Compra
            //  • persiste/actualiza todos sus DetalleCompra (cascade ALL en @OneToMany)
            //  • persiste/actualiza cada Producto y Talla enlazado (cascade MERGE/PERSIST en @ManyToOne)
            Compra managed = em.merge(compra);
            em.getTransaction().commit();
            return managed;
        } finally {
            em.close();
        }
    }

    @Override
    public Compra buscarPorId(Long id) {
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
