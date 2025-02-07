package Logic;

//Author: Jhon Díaz
//Abstract: Parcial 2

public class Autenticacion {
    private String usuario = "Admin";
    private String contraseña = "Jhon4155909*";

    public boolean autenticar(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contraseña);
    }
}