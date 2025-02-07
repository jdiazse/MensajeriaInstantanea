package Database;

//Author: Jhon Díaz
//Abstract: Parcial 2

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseManager {
    private DatabaseReference database;

    public DatabaseManager() {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\\\Users\\\\jdsjh\\\\OneDrive\\\\Fotos\\\\Documentos\\\\Path\\\\testpoo-78fac-firebase-adminsdk-fbsvc-845449b724.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://testpoo-78fac-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            database = FirebaseDatabase.getInstance().getReference();
            System.out.println("Firebase inicializado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al inicializar Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public DatabaseReference getDatabase() {
        return database;
    }
}


