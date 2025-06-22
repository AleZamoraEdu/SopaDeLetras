/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.igu;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.estructuras.ListaSimple;
import org.logica.Controladora;
import org.logica.ResultadoBusqueda;
import org.logica.ResultadoBusquedaIndividual;

/**
 * Clase principal de la Interfaz Gráfica de Usuario (IGU).
 * Representa la ventana principal de la aplicación Sopa de Letras
 * @author Alejandro Zamora
 * @author Roger Balan
 */
public class Panel extends javax.swing.JFrame {

    private final Controladora control;

    /**
     * Constructor de la clase Panel. Inicializa la controladora de lógica y los
     * componentes de la GUI.
     */
    public Panel() {
        control = new Controladora();
        initComponents(); // Asegúrate que tu initComponents cree el lblTiempo
        this.setTitle("Proyecto 1: Sopa de Letras");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * Dibuja el tablero 4x4 en el panel correspondiente.
     *
     * @param letras La matriz 4x4 de caracteres a mostrar.
     */
    private void mostrarTablero(char[][] letras) {
        pnlSopaDeLetras.removeAll();
        if (letras == null) {
            pnlSopaDeLetras.revalidate();
            pnlSopaDeLetras.repaint();
            return;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel lblLetra = new JLabel(String.valueOf(letras[i][j]));
                lblLetra.setFont(new java.awt.Font("Segoe UI", 1, 36));
                lblLetra.setHorizontalAlignment(SwingConstants.CENTER);
                lblLetra.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
                lblLetra.setOpaque(true);
                lblLetra.setBackground(Color.WHITE);
                pnlSopaDeLetras.add(lblLetra);
            }
        }
        pnlSopaDeLetras.revalidate();
        pnlSopaDeLetras.repaint();
    }

    /**
     * Muestra la lista de palabras del diccionario en el área de texto.
     *
     * @param palabras Una ListaSimple con las palabras del diccionario.
     */
    private void mostrarDiccionario(ListaSimple<String> palabras) {
        txtDiccionario.setText("");
        if (palabras == null || palabras.estaVacia()) {
            txtDiccionario.setText("Diccionario no cargado.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palabras.tamano(); i++) {
            sb.append(palabras.obtener(i)).append("\n");
        }
        txtDiccionario.setText(sb.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        tituloPrincipal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pnlSopaDeLetras = new javax.swing.JPanel();
        lblTiempo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnBuscarPalabras = new javax.swing.JButton();
        btnCargarArchivo = new javax.swing.JButton();
        btnBuscarPalabra = new javax.swing.JButton();
        cbmTipoBusqueda = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPalabraABuscar = new javax.swing.JTextField();
        txtDiccionario = new javax.swing.JTextArea();
        btnGuardarDiccionario = new javax.swing.JButton();

        jButton5.setText("jButton5");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(15);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 247, 250));

        jPanel1.setBackground(new java.awt.Color(245, 247, 250));

        tituloPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        tituloPrincipal.setForeground(new java.awt.Color(51, 55, 69));
        tituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloPrincipal.setText("Sopa de letras (4x4)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(tituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(tituloPrincipal)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(245, 247, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 20, 10));
        jPanel2.setForeground(new java.awt.Color(125, 133, 151));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(125, 133, 151));
        jLabel3.setText("Tiempo:");

        pnlSopaDeLetras.setBackground(new java.awt.Color(0, 102, 102));
        pnlSopaDeLetras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlSopaDeLetras.setForeground(new java.awt.Color(0, 102, 102));
        pnlSopaDeLetras.setLayout(new java.awt.GridLayout(4, 4, 5, 5));

        lblTiempo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTiempo.setForeground(new java.awt.Color(51, 55, 69));
        lblTiempo.setText("0 ns");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlSopaDeLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(pnlSopaDeLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jPanel3.setBackground(new java.awt.Color(245, 247, 250));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 20, 20));
        jPanel3.setForeground(new java.awt.Color(245, 247, 250));

        btnBuscarPalabras.setBackground(new java.awt.Color(74, 144, 226));
        btnBuscarPalabras.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscarPalabras.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPalabras.setText("Buscar Palabras");
        btnBuscarPalabras.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnBuscarPalabras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPalabras.setFocusPainted(false);
        btnBuscarPalabras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPalabrasActionPerformed(evt);
            }
        });

        btnCargarArchivo.setBackground(new java.awt.Color(74, 144, 226));
        btnCargarArchivo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCargarArchivo.setForeground(new java.awt.Color(255, 255, 255));
        btnCargarArchivo.setText("Cargar Archivo");
        btnCargarArchivo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnCargarArchivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarArchivo.setFocusPainted(false);
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });

        btnBuscarPalabra.setBackground(new java.awt.Color(74, 144, 226));
        btnBuscarPalabra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscarPalabra.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPalabra.setText("Buscar Palabra");
        btnBuscarPalabra.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnBuscarPalabra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPalabra.setFocusPainted(false);
        btnBuscarPalabra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPalabraActionPerformed(evt);
            }
        });

        cbmTipoBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbmTipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DFS", "BFS" }));

        jLabel2.setForeground(new java.awt.Color(125, 133, 151));
        jLabel2.setText("Escribe la palabra a buscar:");

        txtPalabraABuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPalabraABuscarActionPerformed(evt);
            }
        });

        txtDiccionario.setColumns(20);
        txtDiccionario.setRows(5);

        btnGuardarDiccionario.setBackground(new java.awt.Color(74, 144, 226));
        btnGuardarDiccionario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarDiccionario.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarDiccionario.setText("Guardar Diccionario");
        btnGuardarDiccionario.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnGuardarDiccionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarDiccionario.setFocusPainted(false);
        btnGuardarDiccionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDiccionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarPalabra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbmTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargarArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(txtPalabraABuscar)
                            .addComponent(btnBuscarPalabras, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarDiccionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiccionario))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnCargarArchivo)
                .addGap(18, 18, 18)
                .addComponent(cbmTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarPalabras)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPalabraABuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarPalabra)
                .addGap(18, 18, 18)
                .addComponent(txtDiccionario, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnGuardarDiccionario)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarPalabraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPalabraActionPerformed
        String palabra = txtPalabraABuscar.getText().trim();
        if (palabra.isEmpty() || palabra.length() < 3) {
            JOptionPane.showMessageDialog(this, "Ingrese una palabra de al menos 3 letras.", "Entrada Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String metodo = (String) cbmTipoBusqueda.getSelectedItem();

        if ("BFS".equals(metodo)) {

            control.buscarYVisualizarPalabra(palabra);
        } else {
            ResultadoBusquedaIndividual resultado = control.buscarPalabraDFS(palabra);

            lblTiempo.setText(String.format("%,d ns", resultado.getTiempoTranscurridoNs()));

            if (resultado.isEncontrada()) {
                JOptionPane.showMessageDialog(this, "La palabra '" + palabra.toUpperCase() + "' SÍ fue encontrada (usando DFS).", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "La palabra '" + palabra.toUpperCase() + "' NO fue encontrada (usando DFS).", "No Encontrada", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBuscarPalabraActionPerformed

    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Texto", "txt"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                control.cargarArchivo(archivo);
                mostrarTablero(control.getLetrasTablero());
                mostrarDiccionario(control.getDiccionario());
                JOptionPane.showMessageDialog(this, "Archivo cargado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo: " + e.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void txtPalabraABuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPalabraABuscarActionPerformed
        btnBuscarPalabra.doClick();
    }//GEN-LAST:event_txtPalabraABuscarActionPerformed

    private void btnBuscarPalabrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPalabrasActionPerformed
        ResultadoBusqueda resultado = control.buscarTodasLasPalabras();

        lblTiempo.setText(String.format("%,d ns", resultado.getTiempoTranscurrido()));

        ListaSimple<String> encontradas = resultado.getPalabrasEncontradas();
        StringBuilder sb = new StringBuilder("Palabras encontradas (" + encontradas.tamano() + "):\n\n");
        for (int i = 0; i < encontradas.tamano(); i++) {
            sb.append("• ").append(encontradas.obtener(i)).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Resultados (DFS)", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnBuscarPalabrasActionPerformed

    private void btnGuardarDiccionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDiccionarioActionPerformed
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setDialogTitle("Guardar Diccionario");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo de Texto", "txt"));
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (!archivo.getName().toLowerCase().endsWith(".txt")) {
                archivo = new File(archivo.getParentFile(), archivo.getName() + ".txt");
            }
            try {
                control.guardarDiccionario(archivo);
                JOptionPane.showMessageDialog(this, "Diccionario guardado exitosamente en:\n" + archivo.getAbsolutePath(), "Guardado Exitoso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error de Escritura", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarDiccionarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarPalabra;
    private javax.swing.JButton btnBuscarPalabras;
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnGuardarDiccionario;
    private javax.swing.JComboBox<String> cbmTipoBusqueda;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JPanel pnlSopaDeLetras;
    private javax.swing.JLabel tituloPrincipal;
    private javax.swing.JTextArea txtDiccionario;
    private javax.swing.JTextField txtPalabraABuscar;
    // End of variables declaration//GEN-END:variables
}
