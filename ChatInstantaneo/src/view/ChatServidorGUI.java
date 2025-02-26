package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class ChatServidorGUI extends JFrame {
    private JTextArea areaChat;
    private JTextField campoMensaje;
    private PrintWriter salida;

    public ChatServidorGUI() {
        setTitle("Servidor de Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaChat = new JTextArea();
        areaChat.setEditable(false);
        add(new JScrollPane(areaChat), BorderLayout.CENTER);

        JPanel panelMensaje = new JPanel();
        panelMensaje.setLayout(new BorderLayout());

        campoMensaje = new JTextField();
        panelMensaje.add(campoMensaje, BorderLayout.CENTER);

        JButton botonEnviar = new JButton("Enviar");
        panelMensaje.add(botonEnviar, BorderLayout.EAST);

        add(panelMensaje, BorderLayout.SOUTH);

        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

        campoMensaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

        iniciarServidor();
    }

    private void iniciarServidor() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                areaChat.append("Servidor esperando conexiones...\n");
                Socket socket = serverSocket.accept();
                areaChat.append("Cliente conectado.\n");

                salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String mensajeCliente;
                while ((mensajeCliente = entrada.readLine()) != null) {
                    areaChat.append("Cliente: " + mensajeCliente + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void enviarMensaje() {
        String mensaje = campoMensaje.getText();
        if (!mensaje.isEmpty()) {
            areaChat.append("Servidor: " + mensaje + "\n");
            salida.println(mensaje);
            campoMensaje.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChatServidorGUI().setVisible(true);
        });
    }
}