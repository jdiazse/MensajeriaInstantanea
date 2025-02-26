package viewmodel;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Conectado al servidor.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            String mensajeServidor, mensajeCliente;
            while (true) {
                
                mensajeServidor = entrada.readLine();
                if (mensajeServidor == null || mensajeServidor.equalsIgnoreCase("salir")) {
                    System.out.println("Servidor se ha desconectado.");
                    break;
                }
                System.out.println("Servidor: " + mensajeServidor);

                
                System.out.print("Cliente: ");
                mensajeCliente = teclado.readLine();
                salida.println(mensajeCliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}