package juego;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class JuegoPanel extends JPanel {
    List<Sprite> sprites = new ArrayList<>();

    public JuegoPanel() throws IOException {
        sprites.add(new Personaje(0, 50, 100, "images/palo_oros.png"));
        sprites.add(new Personaje(0, 150, 200, "images/palo_espadas.png"));
        sprites.add(new Personaje(0, 300, 30, "images/palo_bastos.png"));
        sprites.add(new Personaje(0, 450, 300, "images/palo_copas.png"));
    }

    public void cicloDelJuego() {
        long tiempo = System.currentTimeMillis();
        while (true) {
            //calculamos cuanto tiempo pasó desde el último cuadro
            long tiempoNuevo = System.currentTimeMillis();
            long diff = tiempoNuevo - tiempo;
            tiempo = tiempoNuevo;

            // avanzamos todos los sprites el tiempo del frame
            for (Sprite sprite: sprites) {
                sprite.avanzar(diff, this);
            }
            // mandamos a repintar la pantalla
            repaint();

            try { Thread.sleep(20); } catch (InterruptedException e) {}
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // blanqueamos la pantalla pintando un gran rectangulo azul
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // mandamos a dibujar todos los sprites
        for (Sprite sprite: sprites) {
            sprite.dibujar(g, this);
        }
    }
}
