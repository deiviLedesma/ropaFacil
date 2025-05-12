/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Entidades.Producto;
import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public class ProductoDTO {

    private Long idProducto;
    private String nombre;
    private Tipo tipo;
    private Categoria categoria;
    private Color color;
    private double precioUnitario;
    private String caja;
    private Estado estado;
    private List<DetalleVentaDTO> detallesVenta;
    private List<DetalleCompraDTO> detallesCompra;

    //constructor por omision
    public ProductoDTO() {
    }
    
    
    //constructor desde la entidad
    public ProductoDTO(Producto producto) {
        this.idProducto = producto.getId();
        this.nombre = producto.getNombre();
        this.tipo = producto.getTipo();
        this.categoria = producto.getCategoria();
        this.color = producto.getColor();
        this.precioUnitario = producto.getPrecioUnitario();
        this.caja = producto.getCaja();
        this.estado = producto.getEstado();
        this.detallesVenta = new ArrayList<>();
        this.detallesCompra = new ArrayList<>();
    }

    //constructor que inicializa los parametros
    public ProductoDTO(String nombre, Tipo tipo, Categoria categoria, Color color, double precioUnitario, String caja, Estado estado, List<DetalleVentaDTO> detallesVenta, List<DetalleCompraDTO> detallesCompra) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.color = color;
        this.precioUnitario = precioUnitario;
        this.caja = caja;
        this.estado = estado;
        this.detallesVenta = detallesVenta;
        this.detallesCompra = detallesCompra;
    }

    //getters y setters
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<DetalleVentaDTO> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVentaDTO> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    public List<DetalleCompraDTO> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<DetalleCompraDTO> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }

    public Producto toEntity() {

        Producto producto = new Producto();
        // Sólo setear el ID si viene no-nulo (para actualizaciones).
        if (this.idProducto != null) {
            producto.setId(this.idProducto);
        }
        producto.setNombre(this.nombre);
        producto.setTipo(this.tipo);
        producto.setCategoria(this.categoria);
        producto.setColor(this.color);
        producto.setPrecioUnitario(this.precioUnitario);
        producto.setCaja(this.caja);
        producto.setEstado(this.estado);

        // Asegura que las listas no sean null (opcional, porque ya se hace en el constructor)
        if (producto.getDetalleCompras() == null) {
            producto.setDetalleCompras(new ArrayList<>());
        }
        if (producto.getDetalleVentas() == null) {
            producto.setDetalleVentas(new ArrayList<>());
        }

        return producto;
    }

    @Override
    public String toString() {
        return nombre + ", tipo=" + tipo + ", categoria=" + categoria + ", color=" + color + ", precioUnitario=" + precioUnitario + ", estado=" + estado + '}';
    }

    

}
