package View;

//Author: Jhon Díaz
//Abstract: Parcial 2

import Database.DatabaseManager;
import Logic.Autenticacion;
import Logic.Bodega;
import Logic.Producto;
import com.google.firebase.database.*;

import javax.swing.*;
import java.awt.*;

public class InterfazGrafica extends JFrame {
    private Bodega bodega;
    private Autenticacion autenticacion;
    private DatabaseReference database;

    public InterfazGrafica() {
        DatabaseManager databaseManager = new DatabaseManager();
        database = databaseManager.getDatabase();
        bodega = new Bodega(database);
        autenticacion = new Autenticacion();
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Bodega Aurrera");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelBotones = new JPanel(new GridLayout(7, 1));

        JButton btnListar = new JButton("Listar productos");
        JButton btnAgregar = new JButton("Agregar producto");
        JButton btnModificar = new JButton("Editar producto");
        JButton btnOrdenarNombre = new JButton("Ordenar por nombre");
        JButton btnOrdenarPrecio = new JButton("Ordenar por precio");
        JButton btnOrdenarCantidad = new JButton("Ordenar por cantidad");
        JButton btnEliminar = new JButton("Eliminar producto");

        btnListar.addActionListener(e -> {
            System.out.println("Listar Productos presionado");
            database.child("productos").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("Datos recibidos de Firebase: " + dataSnapshot);
                    String listaProductos = bodega.listarProductos(dataSnapshot);
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(InterfazGrafica.this, listaProductos, "Lista de Productos", JOptionPane.INFORMATION_MESSAGE)
                    );
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.err.println("Error en listar productos: " + databaseError.getMessage());
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(InterfazGrafica.this, "Error al cargar los productos.", "Error", JOptionPane.ERROR_MESSAGE)
                    );
                }
            });
        });

        btnAgregar.addActionListener(e -> {
            System.out.println("Agregar Producto presionado");
            String nombre = JOptionPane.showInputDialog(InterfazGrafica.this, "Nombre del producto:");
            if (nombre == null || nombre.trim().isEmpty()) return;
            try {
                double precio = Double.parseDouble(JOptionPane.showInputDialog(InterfazGrafica.this, "Precio del producto:"));
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(InterfazGrafica.this, "Cantidad del producto:"));
                bodega.agregarProducto(new Producto(nombre, precio, cantidad));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(InterfazGrafica.this, "Numero no valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            System.out.println("Editar producto presionado");
            String nombre = JOptionPane.showInputDialog(InterfazGrafica.this, "Producto a editar:");
            if (nombre == null || nombre.trim().isEmpty()) return;
            try {
                double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog(InterfazGrafica.this, "Nuevo precio:"));
                int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog(InterfazGrafica.this, "Nueva cantidad:"));
                bodega.modificarProducto(nombre, nuevoPrecio, nuevaCantidad);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(InterfazGrafica.this, "Numero no valido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnOrdenarNombre.addActionListener(e -> {
            System.out.println("Ordenar por Nombre presionado");
            database.child("productos").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("Datos recibidos de Firebase en Ordenar por Nombre: " + dataSnapshot);
                    String listaOrdenada = bodega.ordenarProductos("nombre", dataSnapshot);
                    SwingUtilities.invokeLater(() -> {
                        JTextArea textArea = new JTextArea(listaOrdenada);
                        textArea.setEditable(false);
                        JOptionPane.showMessageDialog(InterfazGrafica.this, new JScrollPane(textArea), "Productos Ordenados por Nombre", JOptionPane.INFORMATION_MESSAGE);
                    });
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.err.println("Error en Ordenar por Nombre: " + databaseError.getMessage());
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(InterfazGrafica.this, "Error al ordenar los productos.", "Error", JOptionPane.ERROR_MESSAGE)
                    );
                }
            });
        });

        btnOrdenarPrecio.addActionListener(e -> {
            System.out.println("Ordenar por Precio presionado");
            database.child("productos").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("Datos recibidos de Firebase en Ordenar por Precio: " + dataSnapshot);
                    String listaOrdenada = bodega.ordenarProductos("precio", dataSnapshot);
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(InterfazGrafica.this, listaOrdenada, "Productos ordenados por precio", JOptionPane.INFORMATION_MESSAGE)
                    );
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.err.println("Error en Ordenar por Precio: " + databaseError.getMessage());
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(InterfazGrafica.this, "Error al ordenar los productos.", "Error", JOptionPane.ERROR_MESSAGE)
                    );
                }
            });
        });

        btnOrdenarCantidad.addActionListener(e -> {
            System.out.println("Ordenar por cantidad presionado");
            database.child("productos").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("Datos recibidos de Firebase en Ordenar por Cantidad: " + dataSnapshot);
                    String listaOrdenada = bodega.ordenarProductos("cantidad", dataSnapshot);
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(InterfazGrafica.this, listaOrdenada, "Productos ordenados por cantidad", JOptionPane.INFORMATION_MESSAGE)
                    );
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.err.println("Error en Ordenar por Cantidad: " + databaseError.getMessage());
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(InterfazGrafica.this, "Error al ordenar los productos.", "Error", JOptionPane.ERROR_MESSAGE)
                    );
                }
            });
        });

        btnEliminar.addActionListener(e -> {
            System.out.println("Eliminar producto presionado");
            String nombre = JOptionPane.showInputDialog(InterfazGrafica.this, "Nombre del producto a eliminar:");
            if (nombre == null || nombre.trim().isEmpty()) return;
            bodega.eliminarProducto(nombre, (error, ref) -> {
                SwingUtilities.invokeLater(() -> {
                    if (error != null) {
                        System.err.println("Error al eliminar el producto: " + error.getMessage());
                        JOptionPane.showMessageDialog(InterfazGrafica.this, "Error al eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println("Producto eliminado correctamente.");
                        JOptionPane.showMessageDialog(InterfazGrafica.this, "Producto eliminado correctamente.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            });
        });

        panelBotones.add(btnListar);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnOrdenarNombre);
        panelBotones.add(btnOrdenarPrecio);
        panelBotones.add(btnOrdenarCantidad);
        panelBotones.add(btnEliminar);
        panel.add(panelBotones, BorderLayout.CENTER);
        add(panel);

        String usuario = JOptionPane.showInputDialog(this, "Usuario:");
        String contraseña = JOptionPane.showInputDialog(this, "Contraseña:");
        if (!autenticacion.autenticar(usuario, contraseña)) {
            JOptionPane.showMessageDialog(this, "Autenticacion fallida. Saliendo...");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica().setVisible(true));
    }
}
