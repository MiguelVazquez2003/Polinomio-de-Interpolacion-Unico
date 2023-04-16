package Vista;

import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import static java.lang.Math.pow;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Miguel Angel Vazquez
 */

public class Proyecto extends javax.swing.JFrame {
    
     
    int numero_puntos=0;
 public int getN() {
        return numero_puntos;
    }

    public void setN(int n) {
        this.numero_puntos = n;
    }
    /**
     * Creates new form Proyecto
     */
    public Proyecto() {
         
        initComponents();
    }
  
    // Método para obtener los términos del producto utilizado en las ecuaciones de cada punto
public static String terminosProducto(int j, int[][] puntos) {
String termino = "";
for (int k = 0; k < j; k++) {
if (k == 0) {
termino += "(x - " + puntos[k][0] + ")";
} else {
termino += "(x - " + puntos[k][0] + ")";
}
}
return termino;
}

private void resolver(double[][] matrix, JTextArea textArea) {
    
    double punto_obtenido=0;
           try {
    String valorString = punto.getText(); // Obtiene el valor del JTextField en forma de String
    punto_obtenido = Double.parseDouble(valorString); // Convierte el valorString a un número entero
    // Hacer algo con el valor entero, por ejemplo, mostrarlo en un JOptionPane
    
    JOptionPane.showMessageDialog(null, "El punto a evaluar es: " + punto_obtenido);
    /*nos traemos el valor de n */
        int n = this.getN();

        // Pivoteo parcial
        for (int i = 0; i < n; i++) {
            int max = i; //i empieza con i=0
            for (int j = i + 1; j < n; j++) {
                //compara m(1)(0) con m[0][0] en la primera iteracion 
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) { //Math.abs devuelve el valor absoluto de un numero
                    max = j;
                }
            }
            //verifica si el valor absoluto de la entrada actual es mayor que el valor absoluto 
            //de la entrada máxima encontrada hasta ese momento en la misma columna
            //si cambio max, entonces significa que la condicion se cumplio y hay que intercambiar
            if (max != i) {
                double[] temp = matrix[i]; //se declara temporal
                matrix[i] = matrix[max]; //ahora el pivote cambia por el renglon donde se encontro el maximo
                matrix[max] = temp; //lo almacenamos en una variable
                textArea.append(String.format("R%d <-> R%d\n", i + 1, max + 1));
                printMatrix(matrix);
            }
        }

        // Perform Gauss-Jordan elimination
        for (int i = 0; i < n; i++) {
            double pivot = matrix[i][i]; //el pivote esta en la posicion m[0][0] al principio
            for (int j = 0; j < n + 1; j++) {
                matrix[i][j] /= pivot; //dividimos entre el pivote
            }
            textArea.append(String.format("R%d / %f -> R%d\n", i + 1, pivot, i + 1));
            printMatrix(matrix);
            for (int j = 0; j < n; j++) {
                //elimina los coeficientes por encima y por debajo del pivote
                if (i != j) {
                    double factor = matrix[j][i]; //m[1][0]
                    for (int k = 0; k < n + 1; k++) { //para cada columna k en la fila j
                        matrix[j][k] -= factor * matrix[i][k]; //m[1][0]=m[1][0]-1*m[1][0]
                    }
                    textArea.append(String.format("R%d - %f * R%d -> R%d\n", j + 1, factor, i + 1, j + 1));
                    printMatrix(matrix);
                }
            }
        }
        double solucion=0;
        // Print solution
        for (int i = 0; i < n; i++) {
            textArea.append(String.format("a[%d] = %f\n", i, matrix[i][n]));
            JOptionPane.showMessageDialog(null, "a" + i + "= " + (matrix[i][n]));
          
        }
        textArea.append(String.format("Calculo del valor aproximado por medio del polinomio\n\n\n"));
        String resultado = ""; // Creamos una variable para almacenar el resultado del cálculo
double sumatoria = 0; // Inicializamos la variable para la sumatoria
for (int i = 0; i < n; i++) {
    double ai = matrix[i][n]; // Obtiene el valor de ai de la última columna de la fila i
    double xi = Math.pow(punto_obtenido, i); // Calcula xi como punto_obtenido elevado a la potencia i
    double multiplicacion = ai * xi; // Calcula el resultado de ai por xi
    sumatoria += multiplicacion; // Suma el resultado de ai por xi a la variable sumatoria
    resultado += "a" + i + " * " + punto_obtenido + "^" + i + " = " + ai + " * " + xi + " = " + multiplicacion + "\n"; // Concatenamos el resultado del cálculo en la variable resultado
}
resultado += "Sumatoria: "; // Agregamos el texto "Sumatoria: " al resultado
for (int i = 0; i < n; i++) {
    double ai = matrix[i][n]; // Obtiene el valor de ai de la última columna de la fila i
    double multiplicacion = ai * Math.pow(punto_obtenido, i); // Calcula el resultado de ai por punto_obtenido elevado a la potencia i
    if (i == 0) {
        resultado += multiplicacion; // Agregamos el primer término de la sumatoria sin el signo de suma
    } else {
        if (multiplicacion >= 0) {
            resultado += " + "; // Agregamos el signo de suma y el resultado del cálculo si es positivo
        } else {
            resultado += " - "; // Agregamos el signo de resta y el resultado del cálculo si es negativo
            multiplicacion = Math.abs(multiplicacion); // Obtenemos el valor absoluto del resultado para mostrarlo correctamente
        }
        resultado += multiplicacion; // Agregamos el resultado del cálculo al resultado final
    }
}
resultado += " = " + sumatoria + "\n"; // Agregamos el resultado de la sumatoria al resultado final
jTextArea.append(resultado); // Agregamos el resultado al JTextArea

        
} catch (NumberFormatException e) {
    // El valor ingresado no es un número válido
    JOptionPane.showMessageDialog(null, "Ingrese un número válido");
}
        
        
        
        
    }

private void printMatrix(double[][] matrix) {
        int n = this.getN();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.printf("%8.3f", matrix[i][j]);
                //jTextArea.append(Double.toString(matrix[i][j])+"\t\t");
                jTextArea.append(String.format("%.3f", matrix[i][j]) + "\t\t");
            }
            jTextArea.append("\n");
        }
        jTextArea.append("\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        text_field_num_puntos = new javax.swing.JTextField();
        jButton_generar_polinomio = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        matriz = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jButtonsistema = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        punto = new javax.swing.JTextField();
        jButtonlimpiar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Polinomio de Interpolacion Unico");

        jLabel2.setText("Ingresa el numero de puntos: ");

        text_field_num_puntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_field_num_puntosActionPerformed(evt);
            }
        });

        jButton_generar_polinomio.setText("Generar el polinomio ");
        jButton_generar_polinomio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_generar_polinomioActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "x", "y"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        matriz.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "a0"
            }
        ));
        jScrollPane3.setViewportView(matriz);

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jButtonsistema.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonsistema.setText("Encontrar la solucion aproximada");
        jButtonsistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsistemaActionPerformed(evt);
            }
        });

        jLabel3.setText("Ingresa el punto a evaluar: ");

        punto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntoActionPerformed(evt);
            }
        });

        jButtonlimpiar.setText("Limpiar");
        jButtonlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonlimpiarActionPerformed(evt);
            }
        });

        jButton1.setText("INSTRUCCIONES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButtonlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(290, 290, 290))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(text_field_num_puntos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jButton_generar_polinomio)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(punto, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonsistema, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_field_num_puntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(punto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonsistema, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_generar_polinomio)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jButtonlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void text_field_num_puntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_field_num_puntosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_field_num_puntosActionPerformed

    private void jButton_generar_polinomioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_generar_polinomioActionPerformed
        // TODO add your handling code here:
        
        int i,j ;
        

        try {
    String valorString = text_field_num_puntos.getText(); // Obtiene el valor del JTextField en forma de String
    numero_puntos = Integer.parseInt(valorString); // Convierte el valorString a un número entero
    // Hacer algo con el valor entero, por ejemplo, mostrarlo en un JOptionPane
    JOptionPane.showMessageDialog(null, "El numero de puntos es: " + numero_puntos);
     double [][] puntos = new double[numero_puntos][2];
    DefaultTableModel modelo = new DefaultTableModel(new Object[]{"x", "y"}, numero_puntos);
    jTable1.setModel(modelo);
 for (i = 0; i < numero_puntos; i++) {
    try {
        String valorStringX = JOptionPane.showInputDialog(null, "Ingrese la coordenada X del punto " + (i+1));
        double valorX = Double.parseDouble(valorStringX);
        String valorStringY = JOptionPane.showInputDialog(null, "Ingrese la coordenada Y del punto " + (i+1));
        double valorY = Double.parseDouble(valorStringY);
        puntos[i][0] = valorX;
        puntos[i][1] = valorY;
        modelo.setValueAt(valorX, i, 0);
        modelo.setValueAt(valorY, i, 1);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Ha ingresado un valor no válido. Por favor, inténtelo de nuevo.");
        i--;
    }
}
     Object col[] = new Object[numero_puntos + 1]; //el arreglo mostrara las matriz aumentada y la matriz de resultados.

            for (i = 0; i < numero_puntos + 1; i++) {
                if (i < numero_puntos) {
                    col[i] = "a" + (i); //imprimir matriz de coeficientes
                } else {
                    col[i] = "y"; //matriz de terminos independientes
                }

            }
            modelo = new DefaultTableModel(col, numero_puntos); //se dimensiona la matriz aumentada en la tablita y se hace llamada
            //a la clase con dos parametros.
           

            matriz.setModel(modelo);  //matriz hace referencia al jtable, se modifica segun las dimensiones del modelo.
     
           for (i = 0; i < numero_puntos; i++) {
    modelo.setValueAt(1, i, 0); // inicializa la primera columna con 1 en la fila i
    modelo.setValueAt(String.format("%.2f",puntos[i][0]), i, 1);
    
    for (j = 2; j < numero_puntos; j++) {
        double valorPotencia = pow(puntos[i][0], j);
        //modelo.setValueAt(valorPotencia, i, j);
        modelo.setValueAt(String.format("%.2f", valorPotencia), i, j);

    }
    
    
    // Agregar la columna de "y" de la tabla jTable1 a la columna correspondiente en la tabla matriz
    double valorY = (double) jTable1.getValueAt(i, 1);
    //modelo.setValueAt(valorY, i, modelo.getColumnCount()-1);
    modelo.setValueAt(String.format("%.2f", valorY), i, modelo.getColumnCount()-1);
    

}
            TableColumnModel columnModel = matriz.getColumnModel();
for (i = 0; i < columnModel.getColumnCount(); i++) {
    int maxWidth = 0;
    for (j = 0; j < matriz.getRowCount(); j++) {
        TableCellRenderer renderer = matriz.getCellRenderer(j, i);
        Component component = matriz.prepareRenderer(renderer, j, i);
        Object value = matriz.getValueAt(j, i);
        if (value != null) {
            FontMetrics fontMetrics = component.getFontMetrics(component.getFont());
            int cellWidth = fontMetrics.stringWidth(value.toString()) + 10;
            maxWidth = Math.max(cellWidth, maxWidth);
        }
    }
    columnModel.getColumn(i).setPreferredWidth(maxWidth);
}
    
         
} catch (NumberFormatException e) {
    // El valor ingresado no es un número válido
    JOptionPane.showMessageDialog(null, "Ingrese un número válido");
}
        
        
   
            
    }//GEN-LAST:event_jButton_generar_polinomioActionPerformed

    private void jButtonsistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsistemaActionPerformed
        // TODO add your handling code here:
           
        try {
            int n = this.getN();
            /*matriz de coeficientes*/
            double m[][] = new double[n][n];
            /*matriz de terminos independientes*/
            double r[] = new double[n];
            /*declaramos la matriz de coeficientes aumentada*/
            double[][] augmented = new double[n][n + 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    /*convertir a decimal los campos string*/
                    m[i][j] = Double.parseDouble(String.valueOf(matriz.getValueAt(i, j))); //convertir a decimal los campos string
                }
                r[i] = Double.parseDouble(String.valueOf(matriz.getValueAt(i, n)));
            }
            /*En este codigo hacemos las operaciones para tener la matriz aumentada, concatenando
            m y r*/
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    augmented[i][j] = m[i][j];
                }
                augmented[i][n] = r[i];

            }
            /*Llamada al metodo resolver*/
            this.resolver(augmented, jTextArea);

            // jTextArea.append("Determinante"+"= "+det+"\n\n");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error");
        }
    }//GEN-LAST:event_jButtonsistemaActionPerformed

    private void puntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntoActionPerformed
        // TODO add your handling code here:
     
        
        
    }//GEN-LAST:event_puntoActionPerformed

    private void jButtonlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonlimpiarActionPerformed
        // TODO add your handling code here:
        jTextArea.setText(""); //limpiar
    }//GEN-LAST:event_jButtonlimpiarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        VentanaImagenes ventana = new VentanaImagenes();
        ventana.setVisible(true);
    
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proyecto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_generar_polinomio;
    private javax.swing.JButton jButtonlimpiar;
    private javax.swing.JButton jButtonsistema;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTable matriz;
    private javax.swing.JTextField punto;
    private javax.swing.JTextField text_field_num_puntos;
    // End of variables declaration//GEN-END:variables
}


