package View;

import DatabaseManager.DatabaseManager;
import Logic.QuizLogic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizGUI extends JFrame {
    private QuizLogic quizLogic;
    private JLabel preguntaLabel;
    private JTextField respuestaField;
    private JButton verificarButton;
    private JLabel resultadoLabel;

    public QuizGUI(QuizLogic quizLogic) {
        this.quizLogic = quizLogic;

        
        setTitle("Quiz de Capitales");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        preguntaLabel = new JLabel();
        respuestaField = new JTextField(20);
        verificarButton = new JButton("Verificar");
        resultadoLabel = new JLabel();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(preguntaLabel);
        panel.add(respuestaField);
        panel.add(verificarButton);
        panel.add(resultadoLabel);

        add(panel, BorderLayout.CENTER);

        
        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarRespuesta();
            }
        });

        mostrarPregunta();
    }

    
    private void mostrarPregunta() {
        if (quizLogic.hasNextQuestion()) {
            String pais = quizLogic.getCurrentCountry();
            preguntaLabel.setText("¿Cuál es la capital de " + pais + "?");
        } else {
            mostrarResumen();
            dispose(); // Cierra la ventana
        }
    }

    
    private void verificarRespuesta() {
        String userAnswer = respuestaField.getText().trim();
        boolean correcto = quizLogic.checkAnswer(userAnswer);

        // Se muestra un mensaje simple (puedes mejorarlo para indicar cuál fue la respuesta correcta)
        if (correcto) {
            resultadoLabel.setText("¡Correcto!");
        } else {
            resultadoLabel.setText("Incorrecto.");
        }
        respuestaField.setText("");
        mostrarPregunta();
    }

    
    private void mostrarResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Quiz terminado.\n");
        resumen.append("Respuestas correctas: ").append(quizLogic.getRespuestasCorrectas()).append("\n\n");
        resumen.append("Respuestas correctas:\n");
        for (String c : quizLogic.getCorrectas()) {
            resumen.append("- ").append(c).append("\n");
        }
        resumen.append("\nRespuestas incorrectas:\n");
        for (String i : quizLogic.getIncorrectas()) {
            resumen.append("- ").append(i).append("\n");
        }

        JOptionPane.showMessageDialog(this, resumen.toString(), "Resumen del Quiz", JOptionPane.INFORMATION_MESSAGE);
        
        DatabaseManager.uploadQuizData(quizLogic.getSummaryData());
    }
}
