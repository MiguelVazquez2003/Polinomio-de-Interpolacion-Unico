package Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaImagenes extends JFrame implements MouseListener {
  private JLabel etiquetaImagen;
    private int contador = 0;
    private String[] imagenes = {"fondo.jpg","fondo4.jpg","fondo5.jpg",
        "fondo6.jpg","fondo7.jpg","fondo8.jpg","fondo9.jpg","fondo10.jpg","fondo11.jpg","fondo12.jpg",
        "fondo13.jpg","fondo14.jpg","fondo15.jpg","fondo16.jpg","fondo17.jpg","fondo18.jpg","fondo19.jpg",
    "fondo20.jpg","fondo21.jpg"};

    public VentanaImagenes() {
        super("Ventana de imágenes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        etiquetaImagen = new JLabel();
        add(etiquetaImagen);
        
        JOptionPane.showMessageDialog(null, "Bienvenido! Estos son los pasos seguir:  ");
        
        cargarImagen();
        // Agregar el MouseListener a la etiqueta de la imagen
    etiquetaImagen.addMouseListener(this);
        pack();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        contador++;
        if (contador < imagenes.length) {
            cargarImagen();
        } else {
            JOptionPane.showMessageDialog(this, "Fin de los pasos!! Espero te ayude mucho");
            dispose();
        }
    }

    private void cargarImagen() {
        ImageIcon imagen = new ImageIcon("src/main/java/imagenes/" + imagenes[contador]);
        etiquetaImagen.setIcon(imagen);
    }
    //Implementaciones vacías de los métodos de MouseListener
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

