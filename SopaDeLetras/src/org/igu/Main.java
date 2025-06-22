package org.igu;

/**
 *
 * @author Alejandro Zamora, Roger Balan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Crear la instancia del Panel (JFrame) y hacerla visible.
                new Panel().setVisible(true);
            }
        });
    }
}
