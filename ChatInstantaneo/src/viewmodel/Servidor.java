package viewmodel;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor esperando conexiones...");
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            String mensajeCliente, mensajeServidor;
            while (true) {
                
                mensajeCliente = entrada.readLine();
                if (mensajeCliente == null || mensajeCliente.equalsIgnoreCase("salir")) {
                    System.out.println("Cliente se ha desconectado.");
                    break;
                }
                System.out.println("Cliente: " + mensajeCliente);

                
                System.out.print("Servidor: ");
                mensajeServidor = teclado.readLine();
                salida.println(mensajeServidor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}