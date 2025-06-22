package org.igu;

/**
 * La clase `Main` es el punto de entrada principal para la aplicación de Sopa de Letras.
 * Se encarga de inicializar y mostrar la interfaz gráfica de usuario (GUI)
 * al inicio del programa.
 *
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class Main {

    /**
     * El método `main` es el punto de inicio de la aplicación Java.
     * Crea y hace visible la ventana principal de la interfaz gráfica de usuario (`Panel`).
     * Se utiliza `EventQueue.invokeLater` para asegurar que la creación y actualización
     * de la GUI se realice en el Event Dispatch Thread (EDT), que es el hilo seguro para Swing.
     *
     * @param args Los argumentos de línea de comandos pasados a la aplicación (no utilizados en este caso).
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            /**
             * El método `run` es ejecutado por el Event Dispatch Thread (EDT).
             * Dentro de este método, se crea una nueva instancia de la clase {@link Panel}
             * y se hace visible, lo que inicia la interfaz gráfica de la sopa de letras.
             */
            @Override
            public void run() {
                // Crear la instancia del Panel (JFrame) y hacerla visible.
                new Panel().setVisible(true);
            }
        });
    }
}