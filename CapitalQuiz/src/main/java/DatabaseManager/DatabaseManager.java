package DatabaseManager;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.util.Map;

public class DatabaseManager {

    public static void initializeFirebase(String serviceAccountPath, String databaseUrl) {
        try {
            FileInputStream serviceAccount = new FileInputStream(serviceAccountPath);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    
                    .setServiceAccount(serviceAccount)
                    .setDatabaseUrl(databaseUrl)
                    .build();
            FirebaseApp.initializeApp(options);
            System.out.println("Firebase inicializado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void uploadQuizData(Map<String, Object> quizData) {
        try {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("quizzes").push();
            ref.setValue(quizData, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {
                    if (error != null) {
                        System.out.println("Error subiendo datos: " + error.getMessage());
                    } else {
                        System.out.println("Datos subidos a Firebase correctamente.");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
