package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class ChatClienteGUI extends JFrame {
    private JTextArea areaChat;
    private JTextField campoMensaje;
    private PrintWriter salida;

    public ChatClienteGUI() {
        setTitle("Cliente de Chat");
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

        conectarAlServidor();
    }

    private void conectarAlServidor() {
        new Thread(() -> {
            try (Socket socket = new Socket("localhost", 12345)) {
                areaChat.append("Conectado al servidor.\n");

                salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String mensajeServidor;
                while ((mensajeServidor = entrada.readLine()) != null) {
                    areaChat.append("Servidor: " + mensajeServidor + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void enviarMensaje() {
        String mensaje = campoMensaje.getText();
        if (!mensaje.isEmpty()) {
            areaChat.append("Cliente: " + mensaje + "\n");
            salida.println(mensaje);
            campoMensaje.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChatClienteGUI().setVisible(true);
        });
    }
}