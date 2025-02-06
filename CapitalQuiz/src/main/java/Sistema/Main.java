package Sistema;

import javax.swing.SwingUtilities;
import DatabaseManager.DatabaseManager;
import Logic.QuizLogic;
import View.QuizGUI;

public class Main {
    public static void main(String[] args) {
        
        DatabaseManager.initializeFirebase("C:\\\\Users\\\\jdsjh\\\\OneDrive\\\\Fotos\\\\Documentos\\\\Path\\\\testpoo-78fac-firebase-adminsdk-fbsvc-845449b724.json",
                "https://testpoo-78fac-default-rtdb.firebaseio.com");

        
        QuizLogic quizLogic = new QuizLogic();

        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                QuizGUI gui = new QuizGUI(quizLogic);
                gui.setVisible(true);
            }
        });
    }
}

