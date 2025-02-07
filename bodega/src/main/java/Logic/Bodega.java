package Logic;

//Author: Jhon Díaz
//Abstract: Parcial 2

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Bodega {
    private DatabaseReference database;

    public Bodega(DatabaseReference database) {
        this.database = database;
    }

    public void agregarProducto(Producto producto) {
        if (producto == null) return;
        database.child("productos").child(producto.getNombre())
                .setValue(producto, (error, ref) -> {
                    if (error != null) {
                        System.err.println("Error al agregar producto: " + error.getMessage());
                    } else {
                        System.out.println("Producto agregado correctamente");
                    }
                });
    }

    public String listarProductos(DataSnapshot dataSnapshot) {
        StringBuilder lista = new StringBuilder();
        if (dataSnapshot.exists()) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Producto producto = snapshot.getValue(Producto.class);
                if (producto != null) {
                    lista.append(producto.toString()).append("\n");
                }
            }
        }
        if (lista.length() == 0) {
            lista.append("No hay productos");
        }
        return lista.toString();
    }

    public void modificarProducto(String nombre, double nuevoPrecio, int nuevaCantidad) {
        database.child("productos").child(nombre).child("precio")
                .setValue(nuevoPrecio, (error, ref) -> {
                    if (error != null) {
                        System.err.println("Error al modificar precio: " + error.getMessage());
                    } else {
                        System.out.println("Precio modificado");
                    }
                });

        database.child("productos").child(nombre).child("cantidad")
                .setValue(nuevaCantidad, (error, ref) -> {
                    if (error != null) {
                        System.err.println("Error al modificar cantidad: " + error.getMessage());
                    } else {
                        System.out.println("Cantidad modificada");
                    }
                });
    }

    public String ordenarProductos(String criterio, DataSnapshot dataSnapshot) {
        List<Producto> productos = new ArrayList<>();
        if (dataSnapshot.exists()) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Producto producto = snapshot.getValue(Producto.class);
                if (producto != null) {
                    productos.add(producto);
                }
            }
        }

        if (criterio.equals("nombre")) {
            Collections.sort(productos);
        } else if (criterio.equals("precio")) {
            productos.sort(Comparator.comparingDouble(Producto::getPrecio));
        } else if (criterio.equals("cantidad")) {
            productos.sort(Comparator.comparingInt(Producto::getCantidad));
        }

        StringBuilder lista = new StringBuilder();
        for (Producto p : productos) {
            lista.append(p.toString()).append("\n");
        }
        if (lista.length() == 0) {
            lista.append("No hay productos");
        }
        return lista.toString();
    }
    
    public void eliminarProducto(String nombre, DatabaseReference.CompletionListener listener) {
        database.child("productos").child(nombre).removeValue(listener);
    }
}
