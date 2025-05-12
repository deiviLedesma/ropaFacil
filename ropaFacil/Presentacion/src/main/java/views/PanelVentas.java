package views;

import BO.ProductoBO;
import BO.TallaBO;
import BO.VentaBO;
import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import DTO.TallaDTO;
import DTO.VentaDTO;
import Exception.NegocioException;
import Persistencia.PersistenciaException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * PanelVentas es un JPanel que permite realizar la simulación de una venta de
 * productos. Incluye selección de productos y tallas, cantidad a vender, y
 * permite registrar la venta final con un cálculo automático del total.
 *
 * @author SDavidLedesma
 */
public class PanelVentas extends JPanel {

    // Componentes visuales y lógicas de negocio
    private JComboBox<ProductoDTO> comboProducto;
    private JComboBox<TallaDTO> comboTalla;
    private JTextField txtCantidad;
    private JTable tablaVenta;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotal;

    // Objetos de negocio y modelo de datos
    private VentaBO ventaBO = new VentaBO();
    private List<DetalleVentaDTO> detallesVenta = new ArrayList<>();
    private TallaBO tallaBO = new TallaBO();
    private ProductoBO productoBO = new ProductoBO();

    /**
     * Constructor del panel que inicializa la interfaz gráfica y carga
     * productos y tallas.
     */
    public PanelVentas() {
        setLayout(null);
        setBackground(new Color(255, 140, 0)); // Fondo naranja

        // Título del panel
        JLabel lblTitulo = new JLabel("Panel de Ventas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(180, 10, 300, 30);
        add(lblTitulo);

        // Etiquetas y campos para selección de producto y talla
        JLabel lblProducto = new JLabel("Agregar producto:");
        lblProducto.setBounds(30, 60, 150, 20);
        add(lblProducto);

        comboProducto = new JComboBox<>();
        comboProducto.setBounds(30, 85, 200, 25);
        add(comboProducto);

        JLabel lblTalla = new JLabel("Talla:");
        lblTalla.setBounds(30, 120, 150, 20);
        add(lblTalla);

        comboTalla = new JComboBox<>();
        comboTalla.setBounds(30, 145, 100, 25);
        add(comboTalla);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(150, 120, 150, 20);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(150, 145, 80, 25);
        add(txtCantidad);

        // Botón para agregar productos a la venta
        JButton btnAgregar = new JButton("Agregar a venta");
        btnAgregar.setBounds(30, 180, 200, 30);
        add(btnAgregar);

        // Configura el renderer del JComboBox para mostrar correctamente el producto
        comboProducto.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof ProductoDTO) {
                    ProductoDTO producto = (ProductoDTO) value;
                    label.setText(producto.toString());
                    label.setToolTipText(producto.toString());
                }
                return label;
            }
        });

        // Tabla para mostrar productos agregados a la venta
        modeloTabla = new DefaultTableModel(new Object[]{"Producto", "Talla", "Cantidad", "Precio", "Subtotal"}, 0);
        tablaVenta = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaVenta);
        scroll.setBounds(30, 230, 500, 150);
        add(scroll);

        // Doble clic para eliminar producto de la venta
        tablaVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2 && tablaVenta.getSelectedRow() != -1) {
                    int filaSeleccionada = tablaVenta.getSelectedRow();
                    int opcion = JOptionPane.showConfirmDialog(
                            PanelVentas.this,
                            "¿Deseas eliminar este producto de la venta?",
                            "Confirmación",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (opcion == JOptionPane.YES_OPTION) {
                        modeloTabla.removeRow(filaSeleccionada);
                        detallesVenta.remove(filaSeleccionada);
                        actualizarTotal();
                    }
                }
            }
        });

        // Etiqueta para total de la venta
        JLabel lblTotalTexto = new JLabel("Total:");
        lblTotalTexto.setBounds(30, 390, 50, 25);
        add(lblTotalTexto);

        lblTotal = new JLabel("$0.00");
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTotal.setBounds(80, 390, 200, 25);
        add(lblTotal);

        // Botón para registrar la venta
        JButton btnFinalizar = new JButton("Finaliza venta");
        btnFinalizar.setBounds(400, 390, 130, 30);
        add(btnFinalizar);

        // Asociar acciones a los botones
        btnAgregar.addActionListener(e -> agregarProductoAVenta());
        btnFinalizar.addActionListener(e -> registrarVenta());

        // Cargar productos y tallas disponibles
        cargarProductosYTallas();
    }

    /**
     * Carga todos los productos y tallas disponibles desde la base de datos y
     * los agrega a los JComboBox correspondientes.
     */
    private void cargarProductosYTallas() {
        try {
            List<ProductoDTO> productos = productoBO.listarTodos();
            List<TallaDTO> tallas = tallaBO.listarTodas();

            comboProducto.removeAllItems();
            for (ProductoDTO p : productos) {
                comboProducto.addItem(p);
            }

            comboTalla.removeAllItems();
            for (TallaDTO t : tallas) {
                comboTalla.addItem(t);
            }

        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos o tallas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Agrega un producto con su talla y cantidad a la tabla y a la lista lógica
     * de detalles.
     */
    private void agregarProductoAVenta() {
        ProductoDTO producto = (ProductoDTO) comboProducto.getSelectedItem();
        TallaDTO talla = (TallaDTO) comboTalla.getSelectedItem();
        String cantidadStr = txtCantidad.getText();

        if (producto == null || talla == null || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear detalle y agregar a lista
        DetalleVentaDTO detalle = new DetalleVentaDTO();
        detalle.setProducto(producto);
        detalle.setTalla(talla);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(producto.getPrecioUnitario());
        detallesVenta.add(detalle);

        double subtotal = cantidad * producto.getPrecioUnitario();

        // Agregar fila a la tabla
        modeloTabla.addRow(new Object[]{
            producto.getNombre(),
            talla.getDescripcion(),
            cantidad,
            producto.getPrecioUnitario(),
            subtotal
        });

        actualizarTotal();
        txtCantidad.setText("");
    }

    /**
     * Calcula el total actual de la venta y lo muestra en la etiqueta
     * correspondiente.
     */
    private void actualizarTotal() {
        double total = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            total += Double.parseDouble(modeloTabla.getValueAt(i, 4).toString());
        }
        lblTotal.setText(String.format("$%.2f", total));
        // Detectar cuando el panel se vuelve visible y actualizar productos
        this.addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && this.isShowing()) {
                cargarProductosYTallas();
            }
        });

    }

    /**
     * Finaliza el proceso de venta registrando los datos en la base de datos.
     */
    private void registrarVenta() {
        if (detallesVenta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en la venta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        VentaDTO venta = new VentaDTO();
        venta.setFechaVenta(LocalDateTime.now());
        venta.setTotal(Double.parseDouble(lblTotal.getText().replace("$", "")));
        venta.setDetalleVentas(detallesVenta);

        try {
            ventaBO.registrarVenta(venta);
            JOptionPane.showMessageDialog(this, "Venta registrada exitosamente.");

            // Limpiar estado del panel
            modeloTabla.setRowCount(0);
            lblTotal.setText("$0.00");
            detallesVenta.clear();
        } catch (NegocioException | PersistenciaException e) {
            JOptionPane.showMessageDialog(this, "Error al registrar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
