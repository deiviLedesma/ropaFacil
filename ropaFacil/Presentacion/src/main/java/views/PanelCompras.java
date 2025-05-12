package views;

// Importaciones necesarias
import BO.CompraBO;
import DTO.CompraDTO;
import DTO.DetalleCompraDTO;
import DTO.ProductoDTO;
import DTO.TallaDTO;
import Enums.Categoria;
import Enums.Color;
import Enums.Estado;
import Enums.Tipo;
import Exception.NegocioException;
import Persistencia.PersistenciaException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un panel para registrar compras en la interfaz gráfica.
 * Permite capturar los datos de un producto, seleccionar tallas y registrar la
 * compra.
 *
 * @author SDavidLedesma
 */
public class PanelCompras extends JPanel {

    // Componentes de entrada de texto
    private JTextField txtNombreProducto, txtPrecioCompra, txtPrecioVenta, txtCaja;

    // Listas desplegables para seleccionar atributos del producto
    private JComboBox<Tipo> comboTipo;
    private JComboBox<Categoria> comboCategoria;
    private JComboBox<Color> comboColor;

    // Checkboxes para seleccionar múltiples tallas
    private JCheckBox[] checkTallas;

    // Botones de acción
    private JButton btnGuardar, btnLimpiar, btnHistorial;

    // Lógica de negocio para el manejo de compras
    private CompraBO compraBO;

    /**
     * Constructor del panel. Inicializa componentes gráficos y lógica de
     * negocio.
     */
    public PanelCompras() {
        setLayout(null);
        setBackground(new java.awt.Color(255, 140, 0));
        compraBO = new CompraBO();

        // Título
        JLabel lblTitulo = new JLabel("Panel de Compras");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(250, 10, 250, 30);
        add(lblTitulo);

        // Campo: Nombre del producto
        JLabel lblNombre = new JLabel("Nombre del producto:");
        lblNombre.setBounds(30, 60, 150, 20);
        add(lblNombre);
        txtNombreProducto = new JTextField();
        txtNombreProducto.setBounds(200, 60, 200, 20);
        add(txtNombreProducto);

        // Combos: Tipo, Categoría y Color
        comboTipo = new JComboBox<>(Tipo.values());
        comboCategoria = new JComboBox<>(Categoria.values());
        comboColor = new JComboBox<>(Color.values());

        comboTipo.setBounds(200, 90, 200, 20);
        comboCategoria.setBounds(200, 120, 200, 20);
        comboColor.setBounds(200, 150, 200, 20);

        add(new JLabel("Tipo de prenda:")).setBounds(30, 90, 150, 20);
        add(comboTipo);
        add(new JLabel("Categoría:")).setBounds(30, 120, 150, 20);
        add(comboCategoria);
        add(new JLabel("Color:")).setBounds(30, 150, 150, 20);
        add(comboColor);

        // Campos de precios y caja
        txtPrecioCompra = new JTextField();
        txtPrecioCompra.setBounds(200, 180, 200, 20);
        add(new JLabel("Precio Compra Unitario:")).setBounds(30, 180, 160, 20);
        add(txtPrecioCompra);

        txtPrecioVenta = new JTextField();
        txtPrecioVenta.setBounds(200, 210, 200, 20);
        add(new JLabel("Precio Venta Sugerido:")).setBounds(30, 210, 160, 20);
        add(txtPrecioVenta);

        txtCaja = new JTextField();
        txtCaja.setBounds(200, 240, 200, 20);
        add(new JLabel("Caja de Almacenamiento:")).setBounds(30, 240, 160, 20);
        add(txtCaja);

        // Selección de tallas
        JLabel lblTallas = new JLabel("Tallas:");
        lblTallas.setBounds(30, 270, 150, 20);
        add(lblTallas);

        String[] tallas = {"XS", "S", "M", "L", "XL", "2XL", "3XL"};
        checkTallas = new JCheckBox[tallas.length];

        for (int i = 0; i < tallas.length; i++) {
            checkTallas[i] = new JCheckBox(tallas[i]);
            checkTallas[i].setBounds(200 + i * 50, 270, 50, 20);
            checkTallas[i].setBackground(new java.awt.Color(255, 140, 0));
            add(checkTallas[i]);
        }

        // Botones
        btnGuardar = new JButton("Guardar Compra");
        btnGuardar.setBounds(100, 320, 150, 30);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(270, 320, 100, 30);
        add(btnLimpiar);

        btnHistorial = new JButton("Ver Historial de Compras");
        btnHistorial.setBounds(380, 320, 200, 30);
        add(btnHistorial);

        // Acción del botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCompra();
            }
        });

        // Acción del botón Limpiar
        btnLimpiar.addActionListener(e -> limpiarFormulario());
    }

    /**
     * Método que guarda una compra en el sistema. Valida los datos, construye
     * los objetos necesarios y llama a la lógica de negocio.
     */
    private void guardarCompra() {
        try {
            String nombre = txtNombreProducto.getText().trim();
            if (nombre.isEmpty()) {
                throw new NegocioException("El nombre es obligatorio");
            }

            double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
            double precioVenta = Double.parseDouble(txtPrecioVenta.getText());

            // Crear objeto Producto
            ProductoDTO producto = new ProductoDTO();
            producto.setNombre(nombre);
            producto.setTipo((Tipo) comboTipo.getSelectedItem());
            producto.setCategoria((Categoria) comboCategoria.getSelectedItem());
            producto.setColor((Color) comboColor.getSelectedItem());
            producto.setPrecioUnitario(precioVenta);
            producto.setCaja(txtCaja.getText());
            producto.setEstado(Estado.ACTIVO);

            // Crear detalles de la compra por cada talla seleccionada
            List<DetalleCompraDTO> detalles = new ArrayList<>();
            for (JCheckBox check : checkTallas) {
                if (check.isSelected()) {
                    DetalleCompraDTO det = new DetalleCompraDTO();
                    det.setProducto(producto);
                    det.setPrecioUnitario(precioCompra);
                    det.setCantidad(1); 
                    TallaDTO talla = new TallaDTO();
                    talla.setCodigo(check.getText());
                    talla.setDescripcion(check.getText());
                    det.setTalla(talla);
                    detalles.add(det);
                }
            }

            if (detalles.isEmpty()) {
                throw new NegocioException("Seleccione al menos una talla");
            }

            // Crear objeto Compra
            CompraDTO compra = new CompraDTO();
            compra.setFechaCompra(LocalDateTime.now());
            compra.setTotal(detalles.size() * precioCompra);
            compra.setDetalleCompras(detalles);

            // Registrar la compra
            compraBO.registrarCompra(compra);
            JOptionPane.showMessageDialog(this, "Compra registrada exitosamente");
            limpiarFormulario();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NegocioException | PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Limpia todos los campos del formulario para volver a empezar.
     */
    private void limpiarFormulario() {
        txtNombreProducto.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtCaja.setText("");
        for (JCheckBox check : checkTallas) {
            check.setSelected(false);
        }
    }

}
