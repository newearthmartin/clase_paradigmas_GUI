package juego;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VentanaJuego extends JFrame {
    final JuegoPanel jp;

    public VentanaJuego() throws IOException {
        super("Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setPreferredSize(new Dimension(500, 500));
        jp = new JuegoPanel();
        cp.add(jp);
        pack();
    }

    public static void main(String[] args) throws IOException {
        VentanaJuego ventanaJuego = new VentanaJuego();
        ventanaJuego.setVisible(true);
        ventanaJuego.jp.cicloDelJuego();
    }
}
