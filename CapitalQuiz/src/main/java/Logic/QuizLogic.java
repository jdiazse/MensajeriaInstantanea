package Logic;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class QuizLogic {
    private TreeMap<String, String> paises;
    private List<String> paisesLista;
    private int indicePregunta;
    private int respuestasCorrectas;
    private ArrayList<String> correctas;
    private ArrayList<String> incorrectas;

    public QuizLogic() {
        
        paises = new TreeMap<>(Collections.reverseOrder());
        paises.put("España", "Madrid");
        paises.put("Argentina", "Buenos Aires");
        paises.put("Francia", "París");
        paises.put("Italia", "Roma");
        paises.put("Alemania", "Berlín");

        respuestasCorrectas = 0;
        correctas = new ArrayList<>();
        incorrectas = new ArrayList<>();

        
        paisesLista = new ArrayList<>(paises.keySet());
        Collections.shuffle(paisesLista);
        indicePregunta = 0;
    }

    
    public boolean hasNextQuestion() {
        return indicePregunta < paisesLista.size();
    }

    
    public String getCurrentCountry() {
        if (indicePregunta < paisesLista.size()) {
            return paisesLista.get(indicePregunta);
        }
        return null;
    }

    
    public String getCurrentCapital() {
        String pais = getCurrentCountry();
        return pais != null ? paises.get(pais) : null;
    }

    
    public String normalizeString(String input) {
        if (input == null) return "";
        input = Normalizer.normalize(input, Normalizer.Form.NFD);
        return input.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toLowerCase();
    }

    
    public boolean checkAnswer(String userAnswer) {
        String capitalCorrecta = getCurrentCapital();
        boolean isCorrect = normalizeString(userAnswer).equals(normalizeString(capitalCorrecta));
        String pais = getCurrentCountry();

        if (isCorrect) {
            respuestasCorrectas++;
            correctas.add(pais + " - " + capitalCorrecta);
        } else {
            incorrectas.add(pais + " - " + capitalCorrecta + " (Tu respuesta: " + userAnswer + ")");
        }
        indicePregunta++;
        return isCorrect;
    }

    public int getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    public ArrayList<String> getCorrectas() {
        return correctas;
    }

    public ArrayList<String> getIncorrectas() {
        return incorrectas;
    }

    
    public Map<String, Object> getSummaryData() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("fecha", new Date().toString());
        summary.put("respuestasCorrectas", correctas);
        summary.put("respuestasIncorrectas", incorrectas);
        return summary;
    }
}
