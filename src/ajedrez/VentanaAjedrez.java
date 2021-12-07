package ajedrez;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VentanaAjedrez  extends JFrame {

    public VentanaAjedrez() throws IOException  {
        super("Ajedrez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        TableroPanel tablero = new TableroPanel();
        cp.add(tablero);
        pack();
        setResizable(false);
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("sun.java2d.opengl", "true");
        VentanaAjedrez ventana = new VentanaAjedrez();
        TableroPanel tablero = new TableroPanel();
        ventana.add(tablero);
        ventana.setVisible(true);
    }
}
