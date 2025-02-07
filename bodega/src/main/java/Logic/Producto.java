package Logic;

//Author: Jhon Díaz
//Abstract: Parcial 2

public class Producto implements Comparable<Producto> {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto() {}

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public int compareTo(Producto otro) {
        return this.nombre.compareTo(otro.getNombre());
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + ", Precio: " + precio + ", Cantidad: " + cantidad;
    }
}
