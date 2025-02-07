package Main;

//Author: Jhon Díaz
//Abstract: Parcial 2

import View.InterfazGrafica;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }
}