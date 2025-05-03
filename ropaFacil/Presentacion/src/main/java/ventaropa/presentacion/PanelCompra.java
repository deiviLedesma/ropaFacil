package ventaropa.presentacion;

import DTO.ProductoDTO;
import DTO.TallaDTO;
import Entidades.Compra;
import Entidades.DetalleCompra;
import Interfaces.IProductoDAO;
import Interfaces.ITallaDAO;
import DAO.ProductoDAO;
import DAO.TallaDAO;
import DAO.CompraDAO;
import Persistencia.PersistenciaException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public class PanelCompra extends JPanel {

    private JComboBox<ProductoDTO> cmbProducto;
    private JComboBox<TallaDTO> cmbTalla;
    private JTextField txtCantidad;
    private JTextField txtPrecioUnitario;
    private JTextField txtTotal;
    private JTable tblDetalles;
    private DefaultTableModel modeloTabla;
    private List<DetalleCompra> detalles;
    private JButton btnAgregar, btnConfirmar, btnCancelar;

    public PanelCompra() {
        setLayout(new BorderLayout());
        detalles = new ArrayList<>();

        // Panel superior
        JPanel panelDatos = new JPanel(new GridLayout(4, 2, 10, 10));
        cmbProducto = new JComboBox<>();
        cmbTalla = new JComboBox<>();
        txtCantidad = new JTextField();
        txtPrecioUnitario = new JTextField();
        txtTotal = new JTextField("0.0");
        txtTotal.setEditable(false);

        panelDatos.add(new JLabel("Producto:"));
        panelDatos.add(cmbProducto);
        panelDatos.add(new JLabel("Talla:"));
        panelDatos.add(cmbTalla);
        panelDatos.add(new JLabel("Cantidad:"));
        panelDatos.add(txtCantidad);
        panelDatos.add(new JLabel("Precio Unitario:"));
        panelDatos.add(txtPrecioUnitario);

        add(panelDatos, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Producto", "Talla", "Cantidad", "Precio"}, 0);
        tblDetalles = new JTable(modeloTabla);
        add(new JScrollPane(tblDetalles), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar Detalle");
        btnConfirmar = new JButton("Confirmar Compra");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(new JLabel("Total:"));
        panelBotones.add(txtTotal);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnConfirmar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        cargarDatos();

        btnAgregar.addActionListener(e -> agregarDetalle());
        btnConfirmar.addActionListener(e -> confirmarCompra());
        btnCancelar.addActionListener(e -> limpiarFormulario());
    }

    private void cargarDatos()  {
        try{
        IProductoDAO productoDAO = new ProductoDAO();
        ITallaDAO tallaDAO = new TallaDAO();

        productoDAO.buscarTodos().forEach(prod -> cmbProducto.addItem(new ProductoDTO(prod)));
        tallaDAO.buscarTodas().forEach(t -> cmbTalla.addItem(new TallaDTO(t)));
        }catch(PersistenciaException e){
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarDetalle() {
        ProductoDTO producto = (ProductoDTO) cmbProducto.getSelectedItem();
        TallaDTO talla = (TallaDTO) cmbTalla.getSelectedItem();
        String cantidadStr = txtCantidad.getText();
        String precioStr = txtPrecioUnitario.getText();

        if (producto == null || talla == null || cantidadStr.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            double precio = Double.parseDouble(precioStr);

            DetalleCompra detalle = new DetalleCompra();
            detalle.setProducto(producto.toEntity());
            detalle.setTalla(talla.toEntity());
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(precio);

            detalles.add(detalle);
            modeloTabla.addRow(new Object[]{
                producto.getNombre(),
                talla.getCodigo(),
                cantidad,
                precio
            });

            double total = detalles.stream().mapToDouble(d -> d.getCantidad() * d.getPrecioUnitario()).sum();
            txtTotal.setText(String.valueOf(total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad y precio deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarCompra() {
        if (detalles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay detalles para guardar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Compra compra = new Compra();
        compra.setFechaCompra(LocalDateTime.now());
        compra.setTotal(Double.parseDouble(txtTotal.getText()));
        compra.setDetalleCompras(detalles);

        for (DetalleCompra d : detalles) {
            d.setCompra(compra);
        }

        try {
            new CompraDAO().insertar(compra);
            JOptionPane.showMessageDialog(this, "Compra registrada correctamente");
            limpiarFormulario();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la compra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtCantidad.setText("");
        txtPrecioUnitario.setText("");
        txtTotal.setText("0.0");
        modeloTabla.setRowCount(0);
        detalles.clear();
    }
}
