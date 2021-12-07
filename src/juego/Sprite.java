package juego;

import java.awt.*;

abstract public class Sprite {
    //los guardamos como double para tener mas precision
    protected double x;
    protected double y;

    Sprite(int x, int y) {
        this.x = (double) x;
        this.y = (double) y;
    }

    abstract void avanzar(long millis, JuegoPanel juegoPanel);
    abstract void dibujar(Graphics g, JuegoPanel juegoPanel);
}
