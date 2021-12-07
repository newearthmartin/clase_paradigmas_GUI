package juego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Personaje extends Sprite {
    private final Image personajeImage;

    int velocidad;

    public Personaje(int x, int y, int velocidad, String imagen) throws IOException {
        super(x, y);
        this.velocidad = velocidad;
        personajeImage = ImageIO.read(new File(imagen))
                .getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    }

    @Override
    void avanzar(long millis, JuegoPanel juegoPanel) {
        x += velocidad * millis / 1000.0;
        x = x % juegoPanel.getWidth();
    }

    @Override
    void dibujar(Graphics g, JuegoPanel juegoPanel) {
        g.drawImage(personajeImage, (int) x, (int) y, null);
    }
}
