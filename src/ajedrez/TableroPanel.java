package ajedrez;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableroPanel extends JPanel {
    private final Image backgroundImage;

    private int tamCasilleroX;
    private int tamCasilleroY;

    private List<PiezaLabel> piezaLabels = new ArrayList<>();

    TableroPanel() throws IOException {
        backgroundImage = ImageIO.read(new File("images/tablero_ajedrez.png"));
        setLayout(null);
        setPreferredSize(new Dimension(640, 640));

        addMouseListener(new MouseListener() {
            @Override public void mousePressed(MouseEvent e) { mouseClick(e.getX(), e.getY()); }
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });

        Pieza[] piezas = new Pieza[] {
            new Pieza(Pieza.Tipo.REY, false, 0, 4),
            new Pieza(Pieza.Tipo.REY, true, 7, 4),
            new Pieza(Pieza.Tipo.REINA, false, 0, 3),
            new Pieza(Pieza.Tipo.REINA, true, 7, 3)
        };

        for (Pieza pieza: piezas) {
            PiezaLabel piezaLabel = new PiezaLabel(pieza);
            piezaLabels.add(piezaLabel);
            add(piezaLabel);
        };
    }

    @Override
    public void paintComponent(Graphics g) {
        // recalculamos el tamaño de los casilleros por si cambia el tamaño de la ventana
        tamCasilleroX = getWidth() / 8;
        tamCasilleroY = getHeight() / 8;
        piezaLabels.forEach(piezaLabel -> {
            // ajustar la posicion de los labels de acuerdo a la posicion de las piezas
            Pieza pieza = piezaLabel.pieza;
            piezaLabel.setBounds(pieza.col * tamCasilleroX, pieza.fila * tamCasilleroY, tamCasilleroX, tamCasilleroY);
        });
        super.paintComponent(g);
        pintarTablero(g);
    }

    private void pintarTablero(Graphics g) {
        // pinta la grilla del tablero como rectangulos
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Color color = (fila + col) % 2 == 0 ?  Color.white : Color.PINK;
                g.setColor(color);
                g.fillRect(col * tamCasilleroX, fila * tamCasilleroY, tamCasilleroX, tamCasilleroY);
            }
        }

    }

    private void mouseClick(int x, int y) { // click en la posicion x,y del panel
        int casX = x / tamCasilleroX; // calculamos a que casillero corresponde
        int casY = y / tamCasilleroY;
        System.out.println("casillero clickeado: fila=" + casY + ", col=" + casX);
        piezaLabels.forEach(piezaLabel -> { // buscamos si hay una pieza en ese casillero
            Pieza pieza = piezaLabel.pieza;
            if (pieza.fila == casY && pieza.col == casX) {
                System.out.println("Pieza clickeada: " + pieza);
                if (pieza.color == true && pieza.fila > 0) pieza.fila--;
                if (pieza.color == false && pieza.fila < 7) pieza.fila++;
                repaint();
            }
        });
    }
}
