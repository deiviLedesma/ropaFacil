package views;

import DAO.ProductoDAO;
import Entidades.DetalleCompra;
import Entidades.Producto;
import Entidades.Talla;
import Enums.Categoria;
import Enums.Estado;
import Persistencia.PersistenciaException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PanelInventario es un panel visual de tipo JPanel utilizado para mostrar y
 * gestionar el inventario de productos. Permite buscar productos por nombre,
 * categoría y estado, modificar cantidades, cambiar estados y exportar los
 * datos.
 *
 *
 * @author SDavidLedesma
 */
public class PanelInventario extends JPanel {

    private JTextField txtBuscar;
    private JComboBox<String> cbCategoria;
    private JComboBox<String> cbEstado;
    private JTable tblInventario;
    private DefaultTableModel modeloTabla;

    private final ProductoDAO productoDAO = ProductoDAO.getInstanceDAO();

    /**
     * Constructor del panel. Inicializa el layout, componentes y carga la
     * tabla.
     */
    public PanelInventario() {
        setPreferredSize(new Dimension(900, 600));
        setLayout(new BorderLayout());
        setBackground(new Color(255, 140, 0)); // fondo naranja

        initComponents();
        cargarDatosTabla(null, null, null);
    }

    /**
     * Inicializa todos los componentes gráficos y sus acciones
     * correspondientes.
     */
    private void initComponents() {
        JPanel pnlSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSuperior.setBackground(new Color(255, 140, 0));

        txtBuscar = new JTextField(20);
        cbCategoria = new JComboBox<>(new String[]{"", "CABALLERO", "DAMA"});
        cbEstado = new JComboBox<>(new String[]{"", "ACTIVO", "INACTIVO"});

        JButton btnBuscar = new JButton("Buscar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnExportar = new JButton("Exportar PDF");
        JButton btnCambiarEstado = new JButton("Cambiar Estado");
        JButton btnAumentar = new JButton("+ Cantidad");
        JButton btnDisminuir = new JButton("- Cantidad");

        Font fuenteBoton = new Font("Tahoma", Font.BOLD, 12);
        Color colorFondoBoton = Color.BLACK;
        Color colorTextoBoton = Color.WHITE;

        for (JButton btn : new JButton[]{btnBuscar, btnLimpiar, btnExportar, btnCambiarEstado, btnAumentar, btnDisminuir}) {
            btn.setFont(fuenteBoton);
            btn.setBackground(colorFondoBoton);
            btn.setForeground(colorTextoBoton);
        }

        pnlSuperior.add(new JLabel("Buscar Nombre: ")).setFont(fuenteBoton);
        pnlSuperior.add(txtBuscar);
        pnlSuperior.add(new JLabel("Categoría: ")).setFont(fuenteBoton);
        pnlSuperior.add(cbCategoria);
        pnlSuperior.add(new JLabel("Estado: ")).setFont(fuenteBoton);
        pnlSuperior.add(cbEstado);
        pnlSuperior.add(btnBuscar);
        pnlSuperior.add(btnLimpiar);
        pnlSuperior.add(btnExportar);
        pnlSuperior.add(btnCambiarEstado);
        pnlSuperior.add(btnAumentar);
        pnlSuperior.add(btnDisminuir);

        add(pnlSuperior, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Tipo", "Categoría", "Color", "Talla", "Cantidad", "Precio", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tblInventario = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tblInventario);
        add(scrollPane, BorderLayout.CENTER);

        // Acciones
        btnBuscar.addActionListener(e -> buscar());
        btnLimpiar.addActionListener(e -> limpiar());
        btnCambiarEstado.addActionListener(e -> {
            try {
                cambiarEstado();
            } catch (PersistenciaException ex) {
                Logger.getLogger(PanelInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnAumentar.addActionListener(e -> {
            try {
                modificarCantidad(1);
            } catch (PersistenciaException ex) {
                Logger.getLogger(PanelInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnDisminuir.addActionListener(e -> {
            try {
                modificarCantidad(-1);
            } catch (PersistenciaException ex) {
                Logger.getLogger(PanelInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * Carga los datos en la tabla de inventario con los filtros especificados.
     *
     * @param nombre Nombre del producto a filtrar (puede ser null).
     * @param categoria Categoría a filtrar (puede ser null).
     * @param estado Estado a filtrar (puede ser null).
     */
    private void cargarDatosTabla(String nombre, Categoria categoria, Estado estado) {
        modeloTabla.setRowCount(0);
        try {
            List<Producto> productos = productoDAO.buscarTodos();

            productos.stream()
                    .filter(p -> nombre == null || p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                    .filter(p -> categoria == null || p.getCategoria() == categoria)
                    .filter(p -> estado == null || p.getEstado() == estado)
                    .forEach(p -> {
                        for (DetalleCompra dc : p.getDetalleCompras()) {
                            Talla t = dc.getTalla();
                            modeloTabla.addRow(new Object[]{
                                p.getId(),
                                p.getNombre(),
                                p.getTipo(),
                                p.getCategoria(),
                                p.getColor(),
                                t != null ? t.getCodigo() : "-",
                                dc.getCantidad(),
                                p.getPrecioUnitario(),
                                p.getEstado()
                            });
                        }
                    });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar inventario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Realiza la búsqueda de productos con los filtros ingresados en los
     * campos.
     */
    private void buscar() {
        String nombre = txtBuscar.getText().trim();
        Categoria categoria = cbCategoria.getSelectedIndex() > 0 ? Categoria.valueOf((String) cbCategoria.getSelectedItem()) : null;
        Estado estado = cbEstado.getSelectedIndex() > 0 ? Estado.valueOf((String) cbEstado.getSelectedItem()) : null;
        cargarDatosTabla(nombre.isEmpty() ? null : nombre, categoria, estado);
    }

    /**
     * Limpia los campos de búsqueda y recarga la tabla completa.
     */
    private void limpiar() {
        txtBuscar.setText("");
        cbCategoria.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
        cargarDatosTabla(null, null, null);
    }

    /**
     * Cambia el estado del producto seleccionado (de ACTIVO a INACTIVO o
     * viceversa).
     *
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos.
     */
    private void cambiarEstado() throws PersistenciaException {
        int fila = tblInventario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para cambiar su estado.");
            return;
        }

        Long idProducto = (Long) tblInventario.getValueAt(fila, 0);
        Producto producto = productoDAO.buscarPorId(idProducto);
        if (producto == null) {
            return;
        }

        Estado nuevoEstado = producto.getEstado() == Estado.ACTIVO ? Estado.INACTIVO : Estado.ACTIVO;
        producto.setEstado(nuevoEstado);

        try {
            productoDAO.actualizar(producto);
            cargarDatosTabla(null, null, null);
            JOptionPane.showMessageDialog(this, "Estado actualizado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cambiar estado: " + ex.getMessage());
        }
    }

    /**
     * Modifica la cantidad del producto y talla seleccionados.
     *
     * @param delta Valor a sumar o restar a la cantidad actual (1 o -1).
     * @throws PersistenciaException si ocurre un error al actualizar la
     * cantidad.
     */
    private void modificarCantidad(int delta) throws PersistenciaException {
        int fila = tblInventario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para modificar cantidad.");
            return;
        }

        Long idProducto = (Long) tblInventario.getValueAt(fila, 0);
        String codTalla = (String) tblInventario.getValueAt(fila, 5);
        Producto producto = productoDAO.buscarPorId(idProducto);
        if (producto == null) {
            return;
        }

        for (DetalleCompra dc : producto.getDetalleCompras()) {
            if (dc.getTalla() != null && Objects.equals(dc.getTalla().getCodigo(), codTalla)) {
                int nuevaCantidad = dc.getCantidad() + delta;
                if (nuevaCantidad < 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.");
                    return;
                }
                dc.setCantidad(nuevaCantidad);
                break;
            }
        }

        try {
            productoDAO.actualizar(producto);
            cargarDatosTabla(null, null, null);
            JOptionPane.showMessageDialog(this, "Cantidad actualizada correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar cantidad: " + ex.getMessage());
        }
    }
}
