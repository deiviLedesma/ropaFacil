package views;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JPanel {

    private CardLayout cardLayout;
    private JPanel panelContenedor;

    public MenuPrincipal() {
        setLayout(new BorderLayout());

        // Panel superior con título
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color (255, 140, 0));
        JLabel lblTitulo = new JLabel("ROPA FÁCIL - Menú Principal");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        panelSuperior.add(lblTitulo);

        // Panel lateral con botones
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBackground(new Color(255, 140, 0));

        JButton btnCompras = crearBoton("Compras");
        JButton btnVentas = crearBoton("Ventas");
        JButton btnInventario = crearBoton("Inventario");
        JButton btnSalir = crearBoton("Salir");

        panelBotones.add(btnCompras);
        panelBotones.add(btnVentas);
        panelBotones.add(btnInventario);
        panelBotones.add(btnSalir);

        // Panel central con CardLayout para cambiar vistas
        panelContenedor = new JPanel();
        cardLayout = new CardLayout();
        panelContenedor.setLayout(cardLayout);

        panelContenedor.add(new PanelComprasForm(), "compras");
        panelContenedor.add(new PanelVentasForm(), "ventas");
        panelContenedor.add(new PanelInventario(), "inventario");

        // Agregar componentes al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.WEST);
        add(panelContenedor, BorderLayout.CENTER);

        // Acciones de los botones
        btnCompras.addActionListener(e -> cardLayout.show(panelContenedor, "compras"));
        btnVentas.addActionListener(e -> cardLayout.show(panelContenedor, "ventas"));
        btnInventario.addActionListener(e -> cardLayout.show(panelContenedor, "inventario"));
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(160, 40));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Menú Principal - RopaFácil");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new MenuPrincipal());
            frame.setVisible(true);
        });
    }
}
